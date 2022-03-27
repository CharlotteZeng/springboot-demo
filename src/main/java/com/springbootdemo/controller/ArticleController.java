package com.springbootdemo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.springbootdemo.annotation.LogAnnotation;
import com.springbootdemo.entity.Article;
import com.springbootdemo.service.ArticleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @LogAnnotation(operateType = "日志注解")
    @RequestMapping(value = "/create",method = {RequestMethod.POST})
    public String create(@RequestBody Article article){

        article = articleService.createArticle(article);
        String articleJsonString = JSON.toJSONString(article);
        System.out.println(articleJsonString);
        return articleJsonString;
    }
    @RequestMapping(value = "/findArticleById",method = {RequestMethod.GET})
    public String findArticleById(String articleId){
        Article article = articleService.findArticleById(articleId);
        return JSON.toJSONString(article);
    }

    /**
     * 查询未逻辑删除的文章列表
     * @return
     */
    @RequestMapping(value = "/findArticleList",method = {RequestMethod.POST})
    public String findArticleList(@Param("isCache")String isCache){
        //1或null表示用redis缓存 0代表不用
        boolean isCacheb = null == isCache||isCache.equals("1")?true:false;
        List<Article> articleList = articleService.findArticleList(isCacheb);
        return JSON.toJSONString(articleList);
    }

}
