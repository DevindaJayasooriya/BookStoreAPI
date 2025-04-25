/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.resource;

import com.mycompany.bookstore.exception.BookNotFoundException;
import com.mycompany.bookstore.exception.CustomerNotFoundException;
import com.mycompany.bookstore.exception.InvalidInputException;
import com.mycompany.bookstore.exception.OutOfStockException;
import com.mycompany.bookstore.model.Order;
import com.mycompany.bookstore.storage.InMemoryStore;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 *
 * @author devinda
 */
@Path("/api/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {
    
    private InMemoryStore store = new InMemoryStore();
    
    @POST
    public Response createOrder(Map<String, Integer> orderRequest) {
        try {
            int orderId = orderRequest.get("orderId");
            int customerId = orderRequest.get("customerId");
            store.createOrder(orderId, customerId);
            URI location = UriBuilder.fromPath("/api/orders/{customerId}/{orderId}")
                    .resolveTemplate("customerId", customerId)
                    .resolveTemplate("orderId", orderId)
                    .build();
            return Response.created(location).build();
        } catch (CustomerNotFoundException | BookNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (InvalidInputException | OutOfStockException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{customerId}/{orderId}")
    public Response getOrderById(@PathParam("customerId") int customerId, @PathParam("orderId") int orderId) {
        try {
            Order order = store.getOrderById(customerId, orderId);
            return Response.ok(order).build();
        } catch (CustomerNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (InvalidInputException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{customerId}")
    public Response getOrdersByCustomerId(@PathParam("customerId") int customerId) {
        try {
            List<Order> orders = store.getOrdersByCustomerId(customerId);
            return Response.ok(orders).build();
        } catch (CustomerNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
