package com.devonfw.app.java.order.orderservice.dataaccess.api.repo;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Page;

import com.devonfw.app.java.order.orderservice.dataaccess.api.ItemEntity;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemSearchCriteriaTo;
import com.devonfw.module.test.common.base.ComponentTest;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class ItemRepositoryTest extends ComponentTest {

  @Inject
  private ItemRepository itemRepository;

  @Override
  protected void doTearDown() {

    super.doTearDown();
    this.itemRepository.deleteAll();
  }

  @Test
  public void shouldFindAllItems() {

    // given
    setUpTestdata();

    // when
    List<ItemEntity> foundItems = this.itemRepository.findAll();

    // then
    assertThat(foundItems).hasSize(1);
  }

  @Test
  public void shouldOneItemByGivenCriteria() {

    // given
    setUpTestdata();
    ItemSearchCriteriaTo criteria = new ItemSearchCriteriaTo();
    criteria.setName("spaghetti bolognese");
    criteria.setDescription("Italy");
    criteria.setPrice(250.0);

    // when
    Page<ItemEntity> foundItems = this.itemRepository.findByCriteria(criteria);
    ItemEntity foundItem = foundItems.getContent().get(0);

    // then
    assertThat(foundItem).isNotNull();
    assertThat(foundItems.getContent()).hasSize(1);
    assertThat(foundItem.getName()).isEqualTo(criteria.getName());
    assertThat(foundItem.getDescription()).isEqualTo(criteria.getDescription());
    assertThat(foundItem.getPrice()).isEqualTo(criteria.getPrice());
  }

  @Test
  public void shouldFindItemsByGivenName() {

    // given
    setUpTestdata();
    ItemSearchCriteriaTo criteria = new ItemSearchCriteriaTo();
    criteria.setName("olo");

    // when
    Page<ItemEntity> foundItems = this.itemRepository.findItemsByNameWithCaseInsesitive(criteria);

    // then
    ItemEntity foundItem = foundItems.getContent().get(0);

    // then
    assertThat(foundItem).isNotNull();
    assertThat(foundItems.getContent()).hasSize(1);
    assertThat(foundItem.getName()).isEqualToIgnoringCase("spaghetti bolognese");
  }

  @Test
  public void shouldUpdateItemsPriceByGivenName() {

    // given
    setUpTestdata();
    ItemEntity secondItem = new ItemEntity();
    secondItem.setName("pizza");
    secondItem.setPrice(150.0);
    secondItem.setDescription("Also Italy");
    this.itemRepository.save(secondItem);

    // when
    Set<ItemEntity> itemsToBeUpdated = this.itemRepository.findByName("spaghetti bolognese");
    itemsToBeUpdated.forEach(item -> item.setPrice(item.getPrice() + 100.0));
    this.itemRepository.saveAll(itemsToBeUpdated);

    // then
    Set<ItemEntity> changedItems = this.itemRepository.findByName("spaghetti bolognese");
    assertThat(changedItems).extracting("price").containsOnly(350.0);

    Set<ItemEntity> notChangedItems = this.itemRepository.findByName(secondItem.getName());
    assertThat(notChangedItems).extracting("price").containsOnly(150.0);
  }

  private void setUpTestdata() {

    ItemEntity item = new ItemEntity();
    item.setDescription("Italy");
    item.setName("spaghetti bolognese");
    item.setPrice(250.0);

    this.itemRepository.save(item);
  }

}