package com.devonfw.app.java.order.orderservice.dataaccess.api.repo;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.devonfw.app.java.order.orderservice.common.api.OrderStatus;
import com.devonfw.app.java.order.orderservice.dataaccess.api.CustomerEntity;
import com.devonfw.app.java.order.orderservice.dataaccess.api.ItemEntity;
import com.devonfw.app.java.order.orderservice.dataaccess.api.OrderEntity;
import com.devonfw.module.test.common.base.ComponentTest;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class OrderRepositoryTest extends ComponentTest {

  @Inject
  private OrderRepository orderRepository;

  @Inject
  private CustomerRepository customerRepository;

  @Inject
  private ItemRepository itemRepository;

  @Override
  protected void doTearDown() {

    super.doTearDown();
    this.orderRepository.deleteAll();
    this.customerRepository.deleteAll();
    this.itemRepository.deleteAll();
  }

  @Test
  public void shouldFindOrdersWithGivenDateAndStatus() {

    // given
    CustomerEntity owner = new CustomerEntity();
    this.customerRepository.save(owner);

    OrderEntity order = new OrderEntity();
    order.setCreationDate(LocalDate.of(2019, 3, 15));
    order.setStatus(OrderStatus.SERVED);
    order.setOwner(owner);

    this.orderRepository.save(order);

    LocalDate date = LocalDate.of(2019, 3, 15);
    OrderStatus status = OrderStatus.SERVED;

    // when
    Set<OrderEntity> foundOrders = this.orderRepository.findByGivenDayAndStatus(date, status);

    // then
    assertThat(foundOrders).hasSize(1);

    OrderEntity foundOrder = foundOrders.iterator().next();
    assertThat(foundOrder.getCreationDate()).isEqualTo(date);
    assertThat(foundOrder.getStatus()).isEqualTo(status);
  }

  @Test
  public void shouldCreateOrderWithTwoPositionsAndOwnerSet() {

    // given
    CustomerEntity owner = new CustomerEntity();
    owner.setFirstname("Frank");
    owner.setLastname("Underwood");

    ItemEntity firstItem = new ItemEntity();
    firstItem.setName("pizza");
    firstItem.setPrice(150.0);

    ItemEntity secondItem = new ItemEntity();
    secondItem.setName("lasagne");
    secondItem.setPrice(355.0);

    Set<ItemEntity> orderPositions = new HashSet<>();
    orderPositions.add(firstItem);
    orderPositions.add(secondItem);

    OrderEntity order = new OrderEntity();
    order.setOwner(owner);
    order.setCreationDate(LocalDate.of(2019, 3, 15));
    order.setStatus(OrderStatus.SERVED);
    order.setOrderPositions(orderPositions);

    this.customerRepository.save(owner);
    this.itemRepository.saveAll(orderPositions);

    // when
    OrderEntity savedOrder = this.orderRepository.save(order);

    // then
    assertThat(savedOrder).isNotNull();
    assertThat(savedOrder.getOrderPositions()).hasSize(2);
    assertThat(savedOrder.getOwner()).isNotNull();
    assertThat(savedOrder.getOwner().getFirstname()).isEqualTo(owner.getFirstname());
    assertThat(savedOrder.getOwner().getLastname()).isEqualTo(owner.getLastname());
  }

}
