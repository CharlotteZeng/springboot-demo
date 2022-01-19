package com.springbootdemo.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.ibatis.type.Alias;

public class Article {

  private String title;
  private String context;
  private java.sql.Timestamp publishDate;
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


  public java.sql.Timestamp getPublishDate() {
    return publishDate;
  }

  public void setPublishDate(java.sql.Timestamp publishDate) {
    this.publishDate = publishDate;
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


  @Override
  public String toString() {
    return new ToStringBuilder(this)
            .append("title", title)
            .append("context", context)
            .append("publishDate", publishDate)
            .append("author", author)
            .append("subtitle", subtitle)
            .append("articleId", articleId)
            .append("isDelete", isDelete)
            .append("address", address)
            .append("titlePage", titlePage)
            .toString();
  }
}
