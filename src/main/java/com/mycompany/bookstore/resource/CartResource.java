/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.resource;

import com.mycompany.bookstore.exception.BookNotFoundException;
import com.mycompany.bookstore.exception.CustomerNotFoundException;
import com.mycompany.bookstore.exception.InvalidInputException;
import com.mycompany.bookstore.exception.OutOfStockException;
import com.mycompany.bookstore.model.Cart;
import com.mycompany.bookstore.storage.InMemoryStore;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Map;

/**
 *
 * @author devinda
 */
@Path("/api/carts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {
    
    private InMemoryStore store = new InMemoryStore();

    @GET
    @Path("/{customerId}")
    public Response getCartByCustomerId(@PathParam("customerId") int customerId) {
        try {
            Cart cart = store.getCartByCustomerId(customerId);
            return Response.ok(cart).build();
        } catch (CustomerNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
    
    @POST
    @Path("/{customerId}/items")
    public Response addItemToCart(@PathParam("customerId") int customerId, Map<String, Integer> itemRequest) throws BookNotFoundException, InvalidInputException {
        try {
            int bookId = itemRequest.get("bookId");
            int quantity = itemRequest.get("quantity");
            store.addItemToCart(customerId, bookId, quantity);
            return Response.status(Response.Status.CREATED).build();
        } catch (CustomerNotFoundException | BookNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (InvalidInputException | OutOfStockException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    
    @PUT
    @Path("/{customerId}/items/{bookId}")
    public Response updateCartItem(@PathParam("customerId") int customerId, @PathParam("bookId") int bookId, Map<String, Integer> quantityRequest) {
        try {
            int quantity = quantityRequest.get("quantity");
            store.updateCartItem(customerId, bookId, quantity);
            return Response.noContent().build();
        } catch (CustomerNotFoundException | BookNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (InvalidInputException | OutOfStockException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    
    @DELETE
    @Path("/{customerId}/items/{bookId}")
    public Response removeItemFromCart(@PathParam("customerId") int customerId, @PathParam("bookId") int bookId) {
        try {
            store.removeItemFromCart(customerId, bookId);
            return Response.noContent().build();
        } catch (CustomerNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (InvalidInputException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

}
