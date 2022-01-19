package com.springbootdemo.service;

import com.springbootdemo.entity.Article;

public interface ArticleService {
    /**
     *
     *  创建并返回文章实体
     */
    public Article createArticle(Article article);

    public Article findArticleById(String articleId);
}
