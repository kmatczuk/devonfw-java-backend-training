package com.devonfw.app.java.order.orderservice.logic.api.usecase;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.devonfw.app.java.order.orderservice.logic.api.to.ItemEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemSearchCriteriaTo;

public interface UcFindItem {

  /**
   * Returns a Item by its id 'id'.
   *
   * @param id The id 'id' of the Item.
   * @return The {@link ItemEto} with id 'id'
   */
  ItemEto findItem(long id);

  /**
   * Returns a list with all {@link ItemEto}.
   *
   * @return List wtih all {@link ItemEto}
   */
  List<ItemEto> findAllItems();

  /**
   * Returns a paginated list of Item matching the search criteria.
   *
   * @param criteria the {@link ItemSearchCriteriaTo}.
   * @return the {@link List} of matching {@link ItemEto}s.
   */
  Page<ItemEto> findItems(ItemSearchCriteriaTo criteria);

  /**
   * Returns a set of items with given name.
   *
   * @param name {@link ItemEto}'s name
   * @return set of {@link ItemEto} with given name
   */
  Set<ItemEto> findItemsByName(String name);

}
