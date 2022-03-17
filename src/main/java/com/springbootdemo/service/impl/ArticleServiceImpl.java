package com.springbootdemo.service.impl;

import com.springbootdemo.dao.ArticleMapper;
import com.springbootdemo.entity.Article;
import com.springbootdemo.redis.RedisUtils;
import com.springbootdemo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private ArticleMapper articleMapper;

    private final  String ARTICLELISTKEY="articleList";

    @Transactional
    @Override
    public Article createArticle(Article article) {
        articleMapper.insert(article);
        String articleId = article.getArticleId();
        System.out.println("插入的对象ID为："+articleId);
        Article newArt = articleMapper.getOne(articleId);
        System.out.println("service getOne得到的实体:"+newArt);

        System.out.println("传入service的实体:"+article.toString());
        //当有新的数据插入说明原有的缓存已失效 这里进行缓存失效处理
        redisUtils.del(ARTICLELISTKEY);
        return article;
    }

    @Override
    public Article findArticleById(String articleId) {
        Article newArt = articleMapper.getOne(articleId);
        return newArt;
    }

    @Override
    public List<Article> findArticleList() {
        Object articleList = redisUtils.get(ARTICLELISTKEY);
        //判断缓存是否存在 不存在就直接查库 否则取缓存
        if (null==articleList) {
            List<Article> list = articleMapper.findList();
            redisUtils.set(ARTICLELISTKEY,list);
            return list;
        }else{

            return (List)articleList;
        }

    }
}
