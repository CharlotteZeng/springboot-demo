package com.springbootdemo.service;

import com.springbootdemo.entity.Article;

import java.util.List;

public interface ArticleService {
    /**
     *
     *  创建并返回文章实体
     */
    public Article createArticle(Article article);

    public Article findArticleById(String articleId);

    /**
     * 查询未逻辑删除的所有文章实体
     * @param isCache true 用缓存 false 不用缓存
     * @return
     */
    public List findArticleList(boolean isCache);
}
