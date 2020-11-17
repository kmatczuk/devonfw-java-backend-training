package com.devonfw.app.java.order.orderservice.dataaccess.api.repo;

import static com.querydsl.core.alias.Alias.$;

import java.util.Iterator;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devonfw.app.java.order.general.logic.api.to.ItemSearchCriteriaTo;
import com.devonfw.app.java.order.orderservice.dataaccess.api.ItemEntity;
import com.devonfw.module.basic.common.api.query.StringSearchConfigTo;
import com.devonfw.module.jpa.dataaccess.api.QueryUtil;
import com.devonfw.module.jpa.dataaccess.api.data.DefaultRepository;
import com.querydsl.jpa.impl.JPAQuery;

public interface ItemRepository extends DefaultRepository<ItemEntity> {

  /**
   * @param criteria the {@link ItemSearchCriteriaTo} with the criteria to search.
   * @return the {@link Page} of the {@link ItemEntity} objects that matched the search. If no pageable is set, it will
   *         return a unique page with all the objects that matched the search.
   */
  default Page<ItemEntity> findByCriteria(ItemSearchCriteriaTo criteria) {

    ItemEntity alias = newDslAlias();
    JPAQuery<ItemEntity> query = newDslQuery(alias);

    String name = criteria.getName();
    if (name != null && !name.isEmpty()) {
      QueryUtil.get().whereString(query, $(alias.getName()), name, criteria.getNameOption());
    }
    String description = criteria.getDescription();
    if (description != null && !description.isEmpty()) {
      QueryUtil.get().whereString(query, $(alias.getDescription()), description, criteria.getDescriptionOption());
    }
    Double price = criteria.getPrice();
    if (price != null) {
      query.where($(alias.getPrice()).eq(price));
    }
    if (criteria.getPageable() == null) {
      Sort sort = Sort.by("name");
      Pageable pageable = PageRequest.of(0, 20, sort);
      criteria.setPageable(pageable);
    } else {
      addOrderBy(query, alias, criteria.getPageable().getSort());
    }

    return QueryUtil.get().findPaginated(criteria.getPageable(), query, true);
  }

  default Page<ItemEntity> findItemsByNameWithCaseInsesitive(ItemSearchCriteriaTo criteria) {

    ItemEntity alias = newDslAlias();
    JPAQuery<ItemEntity> query = newDslQuery(alias);

    String name = criteria.getName();

    StringSearchConfigTo ignoreCaseStringSearchConfig = new StringSearchConfigTo();
    ignoreCaseStringSearchConfig.setIgnoreCase(true);

    criteria.setNameOption(ignoreCaseStringSearchConfig);

    if (name != null && !name.isEmpty()) {
      QueryUtil.get().whereString(query, $(alias.getName()), name, criteria.getNameOption());
    }

    Sort sort = Sort.by("name");
    Pageable pageable = PageRequest.of(0, 20, sort);

    addOrderBy(query, alias, sort);

    return QueryUtil.get().findPaginated(pageable, query, true);
  }

  @Query(value = "SELECT i FROM Item AS i WHERE i.name = :name")
  Set<ItemEntity> findByName(@Param("name") String name);

  /**
   * Add sorting to the given query on the given alias
   *
   * @param query to add sorting to
   * @param alias to retrieve columns from for sorting
   * @param sort specification of sorting
   */
  public default void addOrderBy(JPAQuery<ItemEntity> query, ItemEntity alias, Sort sort) {

    if (sort != null && sort.isSorted()) {
      Iterator<Order> it = sort.iterator();
      while (it.hasNext()) {
        Order next = it.next();
        switch (next.getProperty()) {
          case "name":
            if (next.isAscending()) {
              query.orderBy($(alias.getName()).asc());
            } else {
              query.orderBy($(alias.getName()).desc());
            }
            break;
          case "description":
            if (next.isAscending()) {
              query.orderBy($(alias.getDescription()).asc());
            } else {
              query.orderBy($(alias.getDescription()).desc());
            }
            break;
          case "price":
            if (next.isAscending()) {
              query.orderBy($(alias.getPrice()).asc());
            } else {
              query.orderBy($(alias.getPrice()).desc());
            }
            break;
          default:
            throw new IllegalArgumentException("Sorted by the unknown property '" + next.getProperty() + "'");
        }
      }
    }
  }

}
