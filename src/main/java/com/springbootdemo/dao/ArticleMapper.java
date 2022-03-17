package com.springbootdemo.dao;

import com.springbootdemo.entity.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface ArticleMapper {
    @Select("select article_id,title,context,publish_date,author,subtitle,is_delete,address from article where article_id = #{articleId} and is_delete is not Null and is_delete != '1'")
    @Results(id = "articleMap",value = {
            @Result(property = "articleId",column = "article_id"),
            @Result(property = "isDelete",column = "is_delete"),
            @Result(property = "publishDate",column = "publish_date"),
            @Result(property = "titlePage",column = "title_page")
    })
    public Article getOne(String articleId);
    public List<Article> getList();
    //物理删除
    @Delete("")
    public int del(String articleId);
    public int update();
    /**
     * keyProperty: 表示将select返回值设置到该属性中
     * resultType: 返回类型
     * before: 是否在insert之前执行
     * statement: 自定义子查询
     * @param userBase
     */
    @SelectKey(keyProperty = "article.articleId",before = true,statement = "select MD5(replace(uuid(), '-', ''))", resultType = String.class)
    @Options(useGeneratedKeys=true, keyProperty="article.articleId")
    @Insert("insert into article(article_id,title,context,author,address) " +
            "values(#{article.articleId,jdbcType=VARCHAR},#{article.title,jdbcType=VARCHAR}" +
            ",#{article.context,jdbcType=VARCHAR},#{article.author,jdbcType=VARCHAR}," +
            "#{article.address,jdbcType=VARCHAR})")
    public int insert(@Param("article")Article article);


    @Select("select * from article where is_delete is not Null and is_delete != '1'")
    /**
     * 引用 @Results的id 可以直接使用它的配置
     */
    @ResultMap(value = "articleMap")
    public List<Article> findList();

}
