package com.devonfw.app.java.order.general.service.impl.rest;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import com.devonfw.app.java.order.orderservice.logic.api.Orderservice;
import com.devonfw.app.java.order.orderservice.logic.api.to.CustomerEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderEto;
import com.devonfw.app.java.order.orderservice.service.api.rest.OrderserviceRestService;

@Named("OrderserivceRestService")
public class OrderserviceRestServiceImpl implements OrderserviceRestService {

  @Inject
  private Orderservice orderservice;

  @Override
  public Set<ItemEto> findItemsByName(String name) {

    return this.orderservice.findItemsByName(name);
  }

  @Override
  public ItemEto findItemById(Long id) {

    return this.orderservice.findItem(id);
  }

  @Override
  public ItemEto saveItem(ItemEto item) {

    return this.orderservice.saveItem(item);
  }

  @Override
  public boolean deleteItem(Long id) {

    return this.orderservice.deleteItem(id);
  }

  @Override
  public CustomerEto findCustomerById(Long id) {

    return this.orderservice.findCustomer(id);
  }

  @Override
  public CustomerEto saveCustomer(CustomerEto customer) {

    return this.orderservice.saveCustomer(customer);
  }

  @Override
  public boolean deleteCustomer(Long id) {

    return this.orderservice.deleteCustomer(id);
  }

  @Override
  public OrderEto findOrderById(Long id) {

    return this.orderservice.findOrder(id);
  }

  @Override
  public OrderEto saveOrder(OrderEto order) {

    return this.orderservice.saveOrder(order);
  }

  @Override
  public boolean deleteOrder(Long id) {

    return this.orderservice.deleteOrder(id);
  }

}
