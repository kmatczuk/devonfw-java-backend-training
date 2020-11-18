package com.devonfw.app.java.order.orderservice.logic.impl.usecase;

import java.util.Objects;
import java.util.Set;

import javax.inject.Named;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;

import com.devonfw.app.java.order.orderservice.dataaccess.api.ItemEntity;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemEto;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcManageItem;
import com.devonfw.app.java.order.orderservice.logic.base.usecase.AbstractItemUc;

@Named
@Validated
@Transactional
public class UcManageItemImpl extends AbstractItemUc implements UcManageItem {

  private static final Logger LOG = LoggerFactory.getLogger(UcManageItemImpl.class);

  @Override
  public boolean deleteItem(long itemId) {

    ItemEntity itemToDelete = getItemRepository().find(itemId);
    getItemRepository().delete(itemToDelete);

    LOG.debug("The item with id {} has been deleted from database.", itemId);

    return true;
  }

  @Override
  public ItemEto saveItem(ItemEto item) {

    Objects.requireNonNull(item, "item");

    ItemEntity itemToSave = getBeanMapper().map(item, ItemEntity.class);
    ItemEntity savedEntity = getItemRepository().save(itemToSave);

    LOG.debug("The item with id {} has been added to database.", savedEntity.getId());

    return getBeanMapper().map(savedEntity, ItemEto.class);
  }

  @Override
  public void raisePrice(String name, Double value) {

    Set<ItemEto> foundItems = getBeanMapper().mapSet(getItemRepository().findByName(name), ItemEto.class);
    foundItems.forEach(item -> item.setPrice(item.getPrice() + value));
    foundItems.forEach(item -> saveItem(item));
  }

}
