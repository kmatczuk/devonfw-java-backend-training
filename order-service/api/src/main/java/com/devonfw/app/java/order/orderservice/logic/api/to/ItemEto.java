package com.devonfw.app.java.order.orderservice.logic.api.to;

import com.devonfw.app.java.order.orderservice.common.api.Item;
import com.devonfw.module.basic.common.api.to.AbstractEto;

public class ItemEto extends AbstractEto implements Item {

  private String name;

  private String description;

  private Double price;

  @Override
  public String getName() {

    return this.name;
  }

  @Override
  public void setName(String name) {

    this.name = name;
  }

  @Override
  public String getDescription() {

    return this.description;
  }

  @Override
  public void setDescription(String description) {

    this.description = description;
  }

  @Override
  public Double getPrice() {

    return this.price;
  }

  @Override
  public void setPrice(Double price) {

    this.price = price;
  }

}
