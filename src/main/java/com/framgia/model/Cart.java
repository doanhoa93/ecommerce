package com.framgia.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Cart implements Serializable {
  private Integer id;
  private Integer userId;

  public Cart() {
    super();
  }

  public Cart(Integer id, Integer userId) {
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
