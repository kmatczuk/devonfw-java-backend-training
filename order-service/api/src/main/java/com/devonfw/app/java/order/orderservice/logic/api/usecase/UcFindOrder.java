package com.devonfw.app.java.order.orderservice.logic.api.usecase;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.devonfw.app.java.order.orderservice.common.api.OrderStatus;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderSearchCriteriaTo;

public interface UcFindOrder {

  /**
   * Returns a Order by its id 'id'.
   *
   * @param id The id 'id' of the Order.
   * @return The {@link OrderEto} with id 'id'
   */
  OrderEto findOrder(long id);

  /**
   * Returns a paginated list of Orders matching the search criteria.
   *
   * @param criteria the {@link OrderSearchCriteriaTo}.
   * @return the {@link List} of matching {@link OrderEto}s.
   */
  Page<OrderEto> findOrders(OrderSearchCriteriaTo criteria);

  /**
   * Returns a set of {@link OrderEto} with given date and {@link OrderStatus}
   *
   * @param date expected creation date of {@link OrderEto}
   * @param status expected status of {@link OrderEto}
   * @return
   */
  Set<OrderEto> findByGivenDateAndStatus(LocalDate date, OrderStatus status);

}
