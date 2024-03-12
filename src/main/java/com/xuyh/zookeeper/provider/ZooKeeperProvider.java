package com.xuyh.zookeeper.provider;

import com.xuyh.zookeeper.config.AdapterZKConfig;
import com.xuyh.zookeeper.watcher.NodeWatcher;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * <p>className: ZooKeeperProvider.java </p>
 * <p>Copyright: Copyright (C) 2006 </p>
 * <p>Company: 青牛技术股份有限公司 </p>
 * <p>
 * Description: zk相关服务
 * </p>
 * <p>Author: cleve </p>
 * <p>CreateDate: 2024/3/12 10:53 </p>
 */
@Slf4j
@Component(value = "zooKeeperProvider")
public class ZooKeeperProvider implements InitializingBean {
    @Value("${adapter.zookeeper.url}")
    private String url;

    @Value("${adapter.zookeeper.session-timeout}")
    private int sessionTimeout;

    @Resource(name = "nodeWatcher")
    private NodeWatcher nodeWatcher;

    @Resource
    private AdapterZKConfig adapterZKConfig;

    private ZooKeeper zooKeeper = null;

    public ZooKeeper zooKeeper() throws IOException {
        return zooKeeper;
    }

    @SneakyThrows
    public void register(){
        String serverNode = adapterZKConfig.getPlatformId() + "_" + adapterZKConfig.getPlatformSubId();
        //创建一个节点
        zooKeeper.create(adapterZKConfig.getRoot() + "/" + adapterZKConfig.getPlatformId() + "/" + serverNode, serverNode.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        log.info("应用" + serverNode + "的服务器已经上线");
    }

    @SneakyThrows
    public boolean serverEmpty(){
        List<String> children = zooKeeper.getChildren(adapterZKConfig.getRoot()+"/"+adapterZKConfig.getPlatformId(), true);
        return children.isEmpty();
    }

    @SneakyThrows
    public List<String> getServerList(){
        return zooKeeper.getChildren(adapterZKConfig.getRoot()+"/"+adapterZKConfig.getPlatformId(), true);
    }

    /**
     * Invoked by the containing {@code BeanFactory} after it has set all bean properties
     * and satisfied {@link BeanFactoryAware}, {@code ApplicationContextAware} etc.
     * <p>This method allows the bean instance to perform validation of its overall
     * configuration and final initialization when all bean properties have been set.
     *
     */
    @Override
    public void afterPropertiesSet() throws Exception{
        zooKeeper = new ZooKeeper(url, sessionTimeout, nodeWatcher);
    }
}
