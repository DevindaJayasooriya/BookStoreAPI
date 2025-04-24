/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.resource;

import com.mycompany.bookstore.exception.CustomerNotFoundException;
import com.mycompany.bookstore.exception.InvalidInputException;
import com.mycompany.bookstore.model.Customer;
import com.mycompany.bookstore.storage.InMemoryStore;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

/**
 *
 * @author devinda
 */
@Path("/api/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {
    
    private InMemoryStore store = new InMemoryStore();
    
    @GET
    public List<Customer> getAllCustomers() {
        return store.getAllCustomers();
    }
    
    @POST
    public Response addCustomer(Customer customer) {
        try {
            store.addCustomer(customer);
            URI location = UriBuilder.fromPath("/api/customers/{id}").resolveTemplate("id", customer.getId()).build();
            return Response.created(location).build();
        } catch (InvalidInputException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    
    @GET
    @Path("/{id}")
    public Response getCustomerById(@PathParam("id") int id) {
        try {
            Customer customer = store.getCustomerById(id);
            return Response.ok(customer).build();
        } catch (CustomerNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
    
    @PUT
    @Path("/{id}")
    public Response updateCustomer(@PathParam("id") int id, Customer customer) {
        if (customer.getId() != id) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Customer ID in path (" + id + ") does not match Customer ID in body (" + customer.getId() + ")")
                    .build();
        }
        try {
            store.updateCustomer(id, customer);
            return Response.noContent().build();
        } catch (CustomerNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (InvalidInputException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") int id) {
        try {
            store.deleteCustomer(id);
            return Response.noContent().build();
        } catch (CustomerNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

}
