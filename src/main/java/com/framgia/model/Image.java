package com.framgia.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Image implements Serializable {
  private Integer id;
  private Integer productId;
  private String image;

  public Image() {
    super();
  }

  public Image(Integer id, Integer productId, String image) {
    super();
    this.id = id;
    this.productId = productId;
    this.image = image;
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

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }
}
