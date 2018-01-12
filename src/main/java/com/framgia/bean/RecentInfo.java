package com.framgia.bean;

public class RecentInfo {
  private Integer id;
  private Integer productId;
  private int viewed;

  public RecentInfo() {
    super();
  }

  public RecentInfo(Integer id, Integer productId, int viewed) {
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

  public int getViewed() {
    return viewed;
  }

  public void setViewed(int viewed) {
    this.viewed = viewed;
  }
}
