package com.framgia.bean;

import java.util.Date;

public class PromotionInfo {
  private Integer id;
  private Date startDate;
  private Date endDate;

  public PromotionInfo() {
  }

  public PromotionInfo(Integer id, Date startDate, Date endDate) {
    this.id = id;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

}
