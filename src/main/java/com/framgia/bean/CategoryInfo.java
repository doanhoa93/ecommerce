package com.framgia.bean;

public class CategoryInfo {
  private Integer id;
  private Integer parentId;
  private String name;

  public CategoryInfo() {
    super();
  }

  public CategoryInfo(Integer id, Integer parentId, String name) {
    super();
    this.id = id;
    this.parentId = parentId;
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getParentId() {
    return parentId;
  }

  public void setParentId(Integer parentId) {
    this.parentId = parentId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
