package com.framgia.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Recent implements Serializable {
  private Integer id;
  private Integer productId;
  private Integer viewed;

  public Recent() {
    super();
  }

  public Recent(Integer id, Integer productId, Integer viewed) {
    super();
    this.id = id;
    this.productId = productId;
    this.viewed = viewed;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getProductId() {
    return productId;
  }

  public void setProductId(Integer productId) {
    this.productId = productId;
  }

  public Integer getViewed() {
    return viewed;
  }

  public void setViewed(Integer viewed) {
    this.viewed = viewed;
  }
}
