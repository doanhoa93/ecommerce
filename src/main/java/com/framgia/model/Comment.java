package com.framgia.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Comment implements Serializable {
  private Integer id;
  private Integer userId;
  private Integer productId;
  private String content;

  public Comment() {
    super();
  }

  public Comment(Integer id, Integer userId, Integer productId, String content) {
    super();
    this.id = id;
    this.userId = userId;
    this.productId = productId;
    this.content = content;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getProductId() {
    return productId;
  }

  public void setProductId(Integer productId) {
    this.productId = productId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
