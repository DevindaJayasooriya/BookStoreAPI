/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author devinda
 */
public class Book {
    private int id;
    private String title;
    private int authorId;
    private String isbn;
    private int publicationYear;
    private double price;
    private int stock;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Book() {
    }
    
    public Book (int id, String title, int authorId, String isbn, int publicationYear, double price, int stock){
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.price = price;
        this.stock = stock;
    }
//    public static void main (String[] args){
//        System.out.print("Test the book class ");
//        Book b1 = new Book(1,"Lord of the Ring",12,"12-14-567",2021,150.40,100);
//        
//        System.out.println("Book ID: "+ b1.getId()+ " Expected 1");
//        System.out.println("Book Title: "+ b1.getTitle()+ " Expected: Lord of the Ring");
//        System.out.println("Author ID: "+ b1.getAuthorId() + " Expected 12");
//        System.out.println("ISBN: "+ b1.getIsbn()+ " Expected 12-14-56");
//        System.out.println("Publication Year: "+ b1.getPublicationYear()+ " Expected 2021");
//        System.out.println("Price: "+ b1.getPrice()+ " Expected 150.40");
//        System.out.println("Stock: "+ b1.getStock()+ " Expected 100");
//        
//        
//        System.out.println("\nTest 2: Updating attributes using setters");
//        
//        b1.setId(2);
//        b1.setTitle("Atomic Habits");
//        b1.setAuthorId(1223);
//        b1.setIsbn("33-456-89");
//        b1.setPublicationYear(2019);
//        b1.setPrice(134.50);
//        b1.setStock(434);
//        
//        System.out.println("\nTest 3: Checking attributes after set");
//
//        
//        System.out.println("Book ID: "+ b1.getId()+ " Expected 2");
//        System.out.println("Book Title: "+ b1.getTitle()+ " Expected: Atomic Habits");
//        System.out.println("Author ID: "+ b1.getAuthorId() + " Expected 1223");
//        System.out.println("ISBN: "+ b1.getIsbn()+ " Expected 33-456-89");
//        System.out.println("Publication Year: "+ b1.getPublicationYear()+ " Expected 2019");
//        System.out.println("Price: "+ b1.getPrice()+ " Expected 134.50");
//        System.out.println("Stock: "+ b1.getStock()+ " Expected 434");
//        
//        // Test 3: Test JSON serialization (requires Jackson dependency)
//        System.out.println("\nTest 3: JSON Serialization");
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            String json = mapper.writeValueAsString(b1);
//            System.out.println("JSON Representation: " + json);
//            
//            // Deserialize back to verify
//            Book deserializedBook = mapper.readValue(json, Book.class);
//            System.out.println("Deserialized Book Title: " + deserializedBook.getTitle() + " (Expected: The Hobbit)");
//        } catch (Exception e) {
//            System.out.println("JSON Serialization failed: " + e.getMessage());
//        }
//    }
   
}
