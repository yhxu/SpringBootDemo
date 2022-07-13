package com.xuyh.mongo.repository;

import com.xuyh.model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ArticleRepository extends MongoRepository<Article, Long> {
    /**
     * 根据标题模糊查询
     *
     * @param title 标题
     * @return 满足条件的文章列表
     */
    List<Article> findByTitleLike(String title);
}
