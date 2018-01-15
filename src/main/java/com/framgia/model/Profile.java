package com.framgia.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Profile implements Serializable {
  private Integer id;
  private Integer userId;
  private GenderEnum gender;
  private Date birthday;
  private String address;

  public Profile() {
    super();
  }

  public Profile(Integer id, Integer userId) {
    super();
    this.id = id;
    this.userId = userId;
  }

  public Profile(Integer id, Integer userId, GenderEnum gender, Date birthday, String address) {
    super();
    this.id = id;
    this.userId = userId;
    this.gender = gender;
    this.birthday = birthday;
    this.address = address;
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

  public GenderEnum getGender() {
    return gender;
  }

  public void setGender(GenderEnum gender) {
    this.gender = gender;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
