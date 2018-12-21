package com.xuyh.model;

import com.xuyh.annotation.MyAnnotation;
import java.lang.Override;
import java.lang.String;

@MyAnnotation("poetModel")
public class PoetModel {
  private String name = "poetTest";

  private String description = "poet 生成代码测试类";

  private boolean isEntity = false;

  @Override
  public String toString() {
    if( name != null ) {
      return this.name +"poetTest";
    } else {
      return "";
    }
  }

  public String getName() {
    return this.name;
  }

  public String getDescription() {
    return this.description;
  }

  public boolean isEntity() {
    return this.isEntity;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setIsEntity(boolean isEntity) {
    this.isEntity = isEntity;
  }
}
