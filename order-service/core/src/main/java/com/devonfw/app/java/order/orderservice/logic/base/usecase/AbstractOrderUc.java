package com.devonfw.app.java.order.orderservice.logic.base.usecase;

import javax.inject.Inject;

import com.devonfw.app.java.order.general.logic.base.AbstractUc;
import com.devonfw.app.java.order.orderservice.dataaccess.api.repo.CustomerRepository;
import com.devonfw.app.java.order.orderservice.dataaccess.api.repo.ItemRepository;
import com.devonfw.app.java.order.orderservice.dataaccess.api.repo.OrderRepository;

/**
 * Abstract use case for Orders, which provides access to the commonly necessary data access objects.
 */
public class AbstractOrderUc extends AbstractUc {

  /** @see #getOrderRepository() */
  @Inject
  private OrderRepository orderRepository;

  /** @see #getCustomerRepository() */
  @Inject
  private CustomerRepository customerRepository;

  /** @see #getItemRepository() */
  @Inject
  private ItemRepository itemRepository;

  /**
   * Returns the field 'orderRepository'.
   *
   * @return the {@link OrderRepository} instance.
   */
  public OrderRepository getOrderRepository() {

    return this.orderRepository;
  }

  /**
   * Returns the field 'customerRepository'.
   *
   * @return the {@link CustomerRepository} instance.
   */
  public CustomerRepository getCustomerRepository() {

    return this.customerRepository;
  }

  /**
   * Returns the field 'itemRepository'.
   *
   * @return the {@link ItemRepository} instance.
   */
  public ItemRepository getItemRepository() {

    return this.itemRepository;
  }

}
