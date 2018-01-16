package com.framgia.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Recent implements Serializable {
  private Integer id;
  private Product product;
  private int viewed;

  public Recent() {
    super();
  }

  public Recent(Integer id, Product product, int viewed) {
    super();
    this.id = id;
    this.product = product;
    this.viewed = viewed;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public int getViewed() {
    return viewed;
  }

  public void setViewed(int viewed) {
    this.viewed = viewed;
  }
}
