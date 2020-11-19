package com.devonfw.app.java.order.orderservice.logic.impl.usecase;

import java.util.List;
import java.util.Set;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.devonfw.app.java.order.orderservice.dataaccess.api.ItemEntity;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemSearchCriteriaTo;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcFindItem;
import com.devonfw.app.java.order.orderservice.logic.base.usecase.AbstractItemUc;

@Named
@Validated
@Transactional
public class UcFindItemImpl extends AbstractItemUc implements UcFindItem {

  /**
   * Logger instance.
   */
  private static final Logger LOG = LoggerFactory.getLogger(UcFindItemImpl.class);

  @Override
  public ItemEto findItem(long id) {

    LOG.debug("Get Item with id {} from database.", id);
    ItemEntity foundItemEntity = getItemRepository().getOne(id);

    return getBeanMapper().map(foundItemEntity, ItemEto.class);
  }

  @Override
  public Page<ItemEto> findItems(ItemSearchCriteriaTo criteria) {

    Page<ItemEntity> foundItems = getItemRepository().findByCriteria(criteria);
    return mapPaginatedEntityList(foundItems, ItemEto.class);
  }

  @Override
  public Set<ItemEto> findItemsByName(String name) {

    return getBeanMapper().mapSet(getItemRepository().findByName(name), ItemEto.class);
  }

  @Override
  public List<ItemEto> findAllItems() {

    return getBeanMapper().mapList(getItemRepository().findAll(), ItemEto.class);
  }

}
