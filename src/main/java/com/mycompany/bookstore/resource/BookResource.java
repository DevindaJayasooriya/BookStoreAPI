/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.resource;

import com.mycompany.bookstore.exception.AuthorNotFoundException;
import com.mycompany.bookstore.exception.BookNotFoundException;
import com.mycompany.bookstore.exception.InvalidInputException;
import com.mycompany.bookstore.model.Book;
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
@Path("/api/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class BookResource {
    
    private InMemoryStore store = new InMemoryStore();
    
    @GET 
    public List<Book> getAllBooks(){
        return store.getAllBooks();
    }
    
    @POST
    public Response addBook(Book book) {
        try {
            store.addBook(book);
            URI location = UriBuilder.fromPath("/api/books/{id}").resolveTemplate("id", book.getId()).build();
            return Response.created(location).build();
            
        } catch (InvalidInputException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
            
        } catch (AuthorNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
    
    @GET
    @Path("{/id}")
    
    public Response updateBook(@PathParam("id") int id, Book book){
        
        if(book.getId() != id){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Book ID in path (" + id + ") does not match Book ID in body (" + book.getId() + ")")
                    .build();
        }
        try {
            store.updateBook(id, book);
            return Response.noContent().build();
            
        } catch (BookNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
            
        } catch (InvalidInputException | AuthorNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    
    @DELETE
    @Path("/{id}")
    
    public Response deleteBook(@PathParam("id") int id) {
        
        try {
            store.deleteBook(id);
            return Response.noContent().build();
            
        } catch (BookNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
  }

