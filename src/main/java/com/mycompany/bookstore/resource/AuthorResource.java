/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.resource;

import com.mycompany.bookstore.exception.AuthorNotFoundException;
import com.mycompany.bookstore.exception.InvalidInputException;
import com.mycompany.bookstore.model.Author;
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
@Path("/api/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {
    
        private InMemoryStore store = new InMemoryStore();

        @GET
        public List<Author> getAllAuthors() {
            return store.getAllAuthors();
        }
        
        @POST
        public Response addAuthor(Author author){
            try{
                store.addAuthor(author);
                URI location = UriBuilder.fromPath("/api/authors/{id}").resolveTemplate("id", author.getId()).build();
                return Response.created(location).build();
                
            } catch(InvalidInputException e){
                return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
            }
        }
        
        @GET
        @Path("/{id}")
        public Response getAuthorById(@PathParam("id") int id) {
            try {
                Author author = store.getAuthorById(id);
                return Response.ok(author).build();
                
            } catch (AuthorNotFoundException e) {
                return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
            }
    }
        
        @PUT
        @Path("/{id}")
        public Response updateAuthor(@PathParam("id") int id, Author author) {
            if (author.getId() != id) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Author ID in path (" + id + ") does not match Author ID in body (" + author.getId() + ")")
                        .build();
            }
            try {
                store.updateAuthor(id, author);
                return Response.noContent().build();
            } catch (AuthorNotFoundException e) {
                return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
            } catch (InvalidInputException e) {
                return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
            }
        }
        
         @DELETE
        @Path("/{id}")
        public Response deleteAuthor(@PathParam("id") int id) {
            try {
                store.deleteAuthor(id);
                return Response.noContent().build();
            } catch (AuthorNotFoundException e) {
                return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
            }
        }
}
