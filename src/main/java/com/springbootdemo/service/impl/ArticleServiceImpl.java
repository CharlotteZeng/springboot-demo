package com.springbootdemo.service.impl;

import com.springbootdemo.dao.ArticleMapper;
import com.springbootdemo.entity.Article;
import com.springbootdemo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Transactional
    @Override
    public Article createArticle(Article article) {
        articleMapper.insert(article);
        String articleId = article.getArticleId();
        System.out.println("插入的对象ID为："+articleId);
        Article newArt = articleMapper.getOne(articleId);
        System.out.println("service getOne得到的实体:"+newArt);

        System.out.println("传入service的实体:"+article.toString());
        return article;
    }

    @Override
    public Article findArticleById(String articleId) {
        Article newArt = articleMapper.getOne(articleId);
        return newArt;
    }
}
