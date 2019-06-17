package com.xuyh.rabbit.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: cleve
 * @Description: RabbitMQ配置类
 * @Date: 2019/6/17
 * @Version:
 */
@Configuration
public class RabbitConfig {

    @Value("${spring.rabbitmq.queues.key.hello}")
    private String helloQueue;
    @Value("${spring.rabbitmq.queues.key.user}")
    private String userQueue;

    @Bean
    public Queue helloQueue() {
        return new Queue(helloQueue);
    }

    @Bean
    public Queue userQueue() {
        return new Queue(userQueue);
    }

    /**
     * @Author: xuyh
     * @Description: 解决 org.springframework.amqp.AmqpException: No method found for class java.util.ArrayList
     * @Date: 15:19 2019/6/17
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }

    //===============验证topic Exchange的队列 begin ==========
    @Value("${spring.rabbitmq.queues.type.topic.exchange}")
    private String topicExchange;
    @Value("${spring.rabbitmq.queues.key.topic.message}")
    private String topicMessage;
    @Value("${spring.rabbitmq.queues.key.topic.messages}")
    private String topicMessages;

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchange);
    }
    /**
     * 将队列topic.message与exchange绑定，binding_key为topic.message,就是完全匹配
     * @param queueMessage
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with(topicMessage);
    }

    /**
     * 将队列topic.messages与exchange绑定，binding_key为topic.#,模糊匹配
     * @param queueMessages
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }

    @Bean
    public Queue queueMessage() {
        return new Queue(topicMessage);
    }

    @Bean
    public Queue queueMessages() {
        return new Queue(topicMessages);
    }
    //===============验证topic Exchange的队列 end==========


    //===============验证Fanout Exchange的队列 begin==========
    @Value("${spring.rabbitmq.queues.type.fanout.exchange}")
    private String fanoutExchange;
    @Value("${spring.rabbitmq.queues.key.fanout.A}")
    private String fanout_A;
    @Value("${spring.rabbitmq.queues.key.fanout.B}")
    private String fanout_B;
    @Value("${spring.rabbitmq.queues.key.fanout.C}")
    private String fanout_C;

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeA(Queue AMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(CMessage).to(fanoutExchange);
    }

    @Bean
    public Queue AMessage() {
        return new Queue(fanout_A);
    }

    @Bean
    public Queue BMessage() {
        return new Queue(fanout_B);
    }

    @Bean
    public Queue CMessage() {
        return new Queue(fanout_C);
    }
    //===============验证Fanout Exchange的队列 end==========

}
