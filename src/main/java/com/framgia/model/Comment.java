package com.framgia.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Comment implements Serializable {
  private Integer id;
  private User user;
  private Product product;
  private String content;

  public Comment() {
    super();
  }

  public Comment(Integer id, User user, Product product, String content) {
    super();
    this.id = id;
    this.user = user;
    this.product = product;
    this.content = content;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
