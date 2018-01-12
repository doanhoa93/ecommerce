package com.framgia.bean;

public class CartInfo {
  private Integer id;
  private Integer userId;

  public CartInfo() {
    super();
  }

  public CartInfo(Integer id, Integer userId) {
    super();
    this.id = id;
    this.userId = userId;
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
}
