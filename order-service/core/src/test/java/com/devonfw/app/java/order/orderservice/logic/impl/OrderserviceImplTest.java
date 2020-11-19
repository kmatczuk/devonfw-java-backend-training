package com.devonfw.app.java.order.orderservice.logic.impl;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.transaction.annotation.Transactional;

import com.devonfw.app.java.order.orderservice.common.api.OrderStatus;
import com.devonfw.app.java.order.orderservice.logic.api.Orderservice;
import com.devonfw.app.java.order.orderservice.logic.api.to.CustomerEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderCto;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderEto;
import com.devonfw.module.test.common.base.ComponentTest;

@Transactional
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class OrderserviceImplTest extends ComponentTest {

  @Inject
  private Orderservice orderservice;

  @Test
  public void shouldChangePriceOfItem() {

    // given
    String name = "sushi";
    Double price = 550.0;

    ItemEto item = new ItemEto();
    item.setName(name);
    item.setDescription("Japan");
    item.setPrice(price);
    this.orderservice.saveItem(item);

    // when
    this.orderservice.raisePrice(name, price);

    // then
    Set<ItemEto> foundItems = this.orderservice.findItemsByName(name);
    assertThat(foundItems.iterator().next().getPrice()).isEqualTo(2 * price);
  }

  @Test
  public void shouldCreateOrderWithSpecifiedOwnerAndTwoOrderPositions() {

    CustomerEto owner = new CustomerEto();
    owner.setFirstname("Hugh");
    owner.setLastname("Laurie");
    CustomerEto savedOwner = this.orderservice.saveCustomer(owner);

    ItemEto item1 = new ItemEto();
    item1.setName("sushi");
    item1.setDescription("Japan");
    item1.setPrice(300.0);

    ItemEto item2 = new ItemEto();
    item2.setName("bigos");
    item2.setDescription("Poland");
    item2.setPrice(350.0);

    Set<ItemEto> orderPositions = new HashSet<>();
    orderPositions.add(item1);
    orderPositions.add(item2);

    OrderEto order = new OrderEto();
    order.setStatus(OrderStatus.PREPARED);
    order.setCreationDate(LocalDate.of(2020, 05, 01));
    order.setOwnerId(owner.getId());

    OrderCto orderCto = new OrderCto();
    orderCto.setOwner(savedOwner);
    orderCto.setOrderPositions(orderPositions);
    orderCto.setOrder(order);

    // when
    OrderCto savedOrderCto = this.orderservice.saveOrder(orderCto);

    // then
    assertThat(savedOrderCto).isNotNull();
    assertThat(savedOrderCto.getOrderPositions()).hasSize(2);
    assertThat(savedOrderCto.getOwner()).isNotNull();
  }
}
