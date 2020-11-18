package com.devonfw.app.java.order.orderservice.service.api.rest;

import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.devonfw.app.java.order.orderservice.logic.api.to.CustomerEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderEto;
import com.devonfw.module.rest.common.api.RestService;

@Path("/orderservice/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface OrderserviceRestService extends RestService {

  @GET
  @Path("/items/{name}")
  public Set<ItemEto> findItemsByName(@PathParam("name") String name);

  @GET
  @Path("/items/{id}/")
  public ItemEto findItemById(@PathParam("id") Long id);

  @POST
  @Path("/items")
  public ItemEto saveItem(ItemEto item);

  @DELETE
  @Path("/items/{id}")
  public boolean deleteItem(@PathParam("id") Long id);

  @GET
  @Path("/customers/{id}")
  public CustomerEto findCustomerById(@PathParam("id") Long id);

  @POST
  @Path("/customers")
  public CustomerEto saveCustomer(CustomerEto customer);

  @DELETE
  @Path("/customers/{id}")
  public boolean deleteCustomer(@PathParam("id") Long id);

  @GET
  @Path("/orders/{id}")
  public OrderEto findOrderById(@PathParam("id") Long id);

  @POST
  @Path("/orders")
  public OrderEto saveOrder(OrderEto order);

  @DELETE
  @Path("/orders/{id}")
  public boolean deleteOrder(@PathParam("id") Long id);
}
