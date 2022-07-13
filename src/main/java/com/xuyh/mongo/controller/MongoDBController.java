package com.xuyh.mongo.controller;

import com.xuyh.model.Article;
import com.xuyh.mongo.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Slf4j
@RestController("/mongoDB")
public class MongoDBController {
    @Autowired
    private ArticleRepository articleRepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 新增
     */
    @PostMapping("/save")
    public String save() {
        Article article = new Article(1L, "save", "save", new Date(), new Date(), 0L, 0L);
        articleRepo.save(article);
        // 跟具ID属性进行新增或更新
        mongoTemplate.save(article);
        return "SUCCESS";
    }

    /**
     * 测试批量新增列表
     */
    @PostMapping("/saveAll")
    public String saveList() {
        List<Article> articles = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            articles.add(new Article(i+1000000, "saveAll", "saveAll", new Date(), new Date(), 0L, 0L));
        }
        articleRepo.saveAll(articles);
        return "SUCCESS";
    }

    /**
     * 测试更新
     */
    @PostMapping("/update")
    public String update() {
        articleRepo.findById(1L).ifPresent(article -> {
            article.setTitle(article.getTitle() + "更新之后的标题");
            article.setUpdateTime(new Date());
            articleRepo.save(article);
        });
        return "SUCCESS";
    }

    /**
     * 测试删除
     */
    @PostMapping("/delete")
    public String delete(@RequestParam("id")Long id) {
        // 根据主键删除
        articleRepo.deleteById(id);

        // 全部删除
        articleRepo.deleteAll();
        return "SUCCESS";
    }

    /**
     * 测试分页排序查询
     */
    @PostMapping("/pageQuery")
    public Page<Article> pageQuery() {
        Sort sort = Sort.by("thumbUp", "updateTime").descending();
        PageRequest pageRequest = PageRequest.of(0, 5, sort);
        Page<Article> all = articleRepo.findAll(pageRequest);
        return all;
    }

    /**
     * 查询,条件匹配/排序/分页, 基于继承MongoRepository实现
     */
    @PostMapping("/queryByRepository")
    public Page<Article> queryByRepository() {
        /**
         * 匹配条件构造
         */
        Article article = Article.builder()
                .title("save")
                .content("save")
                .build();
        // 指定字段匹配类型
        ExampleMatcher withMatcher = ExampleMatcher.matching()
                // 忽略大小写
                .withIgnoreCase()
                // 指定"title"为精确匹配
                .withMatcher("title", ExampleMatcher.GenericPropertyMatcher::exact)
                // 指定"content"为模糊匹配
                .withMatcher("content", ExampleMatcher.GenericPropertyMatcher::contains);
        Example<Article> example = Example.of(article,withMatcher);

        /**
         * 排序规则
         */
        Sort sort = Sort.by("updateTime").descending();

        /**
         * 分页
         */
        PageRequest pageRequest = PageRequest.of(0, 5, sort);

        /**
         * 分页查询
         */
        Page<Article> articleRepoAll = articleRepo.findAll(example, pageRequest);
        return articleRepoAll;
    }

    /**
     * 查询,条件匹配/排序/分页, 基于MongoTemplate实现
     */
    @PostMapping("queryByMongoTemplate")
    public PageImpl<Article> queryByMongoTemplate() {
        /**
         * 查询条件
         */
        Criteria criteria = Criteria
                // 精确匹配
                .where("title").is("save")
                // 模糊匹配, 用正则: .*[xxx].*
                .and("content").regex(".*save.*")
                // 匹配明细里的字段
                .and("ids").elemMatch(Criteria.where("id").is(1))
                // 匹配多个并行或
                .andOperator(
                        new Criteria().orOperator(
                                Criteria.where("visits").exists(false),
                                Criteria.where("visits").is(1)
                        ),
                        new Criteria().orOperator(
                                Criteria.where("thumbUp").exists(false),
                                Criteria.where("thumbUp").is(1)
                        )

                );
        ;

        /**
         * 排序规则
         */
        Sort sort = Sort.by("updateTime").descending();


        /**
         * 分页
         */
        PageRequest pageRequest = PageRequest.of(1, 5, sort);

        Query query = Query.query(criteria).with(sort).with(pageRequest);

        List<Article> articles = mongoTemplate.find(query, Article.class);
        PageImpl<Article> page = (PageImpl<Article>) PageableExecutionUtils.getPage(articles, pageRequest, () -> mongoTemplate.count(Query.query(criteria),Article.class));

        return page;
    }

    /**
     * 高级用法展示，待验证
     * @return
     */
    public Page<Article> pageInfo(){

        /**
         * 联表查询
         * 参数1: 从表表名
         * 参数2: 主表关联字段
         * 参数3: 从表关联字段
         * 参数4: 查出从表数据集合的别名 例如主表数据{"name":"wpr","age":18} , 关联从表后结果{"name":"wpr","age":18,"userInfo":[]}, 从表没数据则为[]
         */
        LookupOperation lookup = Aggregation.lookup("user", "userId", "userId", "userInfo");

        // 子集合不能为空
        Criteria criteria = Criteria.where("userInfo").not().size(0);
        // 子集合条件
        criteria.and("userInfo.six").is(1);
        // 主表条件
        criteria.and("title").is("hello_world");
        // 条件类型转换
        MatchOperation matchOperation = Aggregation.match(criteria);

        /**
         * 查询总数
         */
        CountOperation countOperation = Aggregation.count().as("total");
        // project: 表示结果只查询字段:total
        ProjectionOperation project = Aggregation.project("total");
        // 条件一定要排好先后顺序
        Aggregation aggregation = Aggregation.newAggregation(lookup, matchOperation, countOperation, project);
        AggregationResults<Map> aggregate = mongoTemplate.aggregate(aggregation, "article", Map.class);
        List<Map> aggregateMappedResults = aggregate.getMappedResults();
        // 总数
        Integer total = CollectionUtils.isEmpty(aggregateMappedResults) ? 0 : (int)aggregateMappedResults.get(0).get("total");

        if(Objects.equals(total,0)){
            return new PageImpl<Article>(new ArrayList<>());
        }

        /**
         * 分页查询
         */
        // 排序条件
        SortOperation sortOperation = Aggregation.sort(Sort.by("updateTime").descending());
        // 过滤前n条数据
        SkipOperation skipOperation = Aggregation.skip(0L);
        // 查询n条数据
        LimitOperation limitOperation = Aggregation.limit(10);
        Aggregation pageAggregation = Aggregation.newAggregation(lookup, matchOperation, sortOperation,skipOperation,limitOperation);
        AggregationResults<Article> result = mongoTemplate.aggregate(pageAggregation,"article", Article.class);
        List<Article> articles = result.getMappedResults();
        Page<Article> page = new PageImpl<Article>(articles);
        return page;
    }
}
