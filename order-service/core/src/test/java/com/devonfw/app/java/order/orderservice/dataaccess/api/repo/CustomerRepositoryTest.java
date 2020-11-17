package com.devonfw.app.java.order.orderservice.dataaccess.api.repo;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.devonfw.app.java.order.orderservice.dataaccess.api.CustomerEntity;
import com.devonfw.module.test.common.base.ComponentTest;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class CustomerRepositoryTest extends ComponentTest {

  @Inject
  private CustomerRepository customerRepository;

  @Override
  protected void doTearDown() {

    super.doTearDown();
    this.customerRepository.deleteAll();
  }

  @Test
  public void shouldDeleteCustomerById() {

    // given
    CustomerEntity owner = new CustomerEntity();
    this.customerRepository.save(owner);

    // when
    this.customerRepository.deleteById(owner.getId());

    // then
    assertThat(this.customerRepository.findAll()).hasSize(0);
  }
}
