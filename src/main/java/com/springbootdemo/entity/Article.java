package com.springbootdemo.entity;


import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Article {

  private String title;
  private String context;
  @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")//页面写入数据库时格式化
  @JSONField(format="yyyy-MM-dd hh:mm:ss")//数据库导出页面时json格式化
  private Date publishDate;
  private String author;
  private String subtitle;
  private String articleId;
  private String isDelete;
  private String address;
  private String titlePage;

  public String getTitlePage() {
    return titlePage;
  }

  public void setTitlePage(String titlePage) {
    this.titlePage = titlePage;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }


  public String getSubtitle() {
    return subtitle;
  }

  public void setSubtitle(String subtitle) {
    this.subtitle = subtitle;
  }


  public String getArticleId() {
    return articleId;
  }

  public void setArticleId(String articleId) {
    this.articleId = articleId;
  }


  public String getIsDelete() {
    return isDelete;
  }

  public void setIsDelete(String isDelete) {
    this.isDelete = isDelete;
  }


  public Date getPublishDate() {
    return publishDate;
  }

  public void setPublishDate(Date publishDate) {
    this.publishDate = publishDate;
  }
}
