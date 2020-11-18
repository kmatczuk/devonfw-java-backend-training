package com.devonfw.app.java.order.orderservice.logic.impl;

import javax.inject.Inject;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.transaction.annotation.Transactional;

import com.devonfw.app.java.order.orderservice.logic.api.Orderservice;
import com.devonfw.module.test.common.base.ComponentTest;

@Transactional
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class OrderserviceImplTest extends ComponentTest {

  @Inject
  private Orderservice orderservice;

}
