package com.devonfw.app.java.order.orderservice.logic.api.usecase;

import com.devonfw.app.java.order.orderservice.logic.api.to.ItemEto;

public interface UcManageItem {

  /**
   * Deletes a item from the database by its id 'itemId'.
   *
   * @param itemId Id of the item to delete
   * @return boolean <code>true</code> if the item can be deleted, <code>false</code> otherwise
   */
  boolean deleteItem(long itemId);

  /**
   * Saves a item and store it in the database.
   *
   * @param item the {@link ItemEto} to create.
   * @return the new {@link ItemEto} that has been saved with ID and version.
   */
  ItemEto saveItem(ItemEto item);

  /**
   * Raises a price for item by given name.
   *
   * @param name name of a {@link ItemEto} which price will be raised
   * @param value value of the price increase of a {@link ItemEto}
   */
  void raisePrice(String name, Double value);

}