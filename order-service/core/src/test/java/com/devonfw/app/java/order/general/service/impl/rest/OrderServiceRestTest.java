package com.devonfw.app.java.order.general.service.impl.rest;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.devonfw.app.java.order.SpringBootApp;
import com.devonfw.app.java.order.general.service.base.test.RestServiceTest;
import com.devonfw.app.java.order.orderservice.logic.api.Orderservice;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemEto;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { SpringBootApp.class })
public class OrderServiceRestTest extends RestServiceTest {

  /** Logger instance. */
  private static final Logger LOG = LoggerFactory.getLogger(OrderServiceRestTest.class);

  @Inject
  private Orderservice orderservice;

  @Override
  public void doSetUp() {

    super.doSetUp();
  }

  @Override
  public void doTearDown() {

    super.doTearDown();
  }

  @Test
  public void getItemById() {

    // given
    ItemEto item = new ItemEto();
    item.setName("chocolate");
    item.setPrice(250.0);
    ItemEto savedItem = this.orderservice.saveItem(item);

    // when
    ItemEto foundItem = this.orderservice.findItem(savedItem.getId());

    // then
    assertThat(foundItem).isNotNull();
    assertThat(foundItem.getId()).isEqualTo(savedItem.getId());
  }
}
