/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.storage;

import com.mycompany.bookstore.exception.BookNotFoundException;
import com.mycompany.bookstore.exception.InvalidInputException;
import com.mycompany.bookstore.model.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author devinda
 */
public class InMemoryStore {
    
    private Map<Integer, Book> books;

    public InMemoryStore() {
        this.books = books;
    }
    
    //add books
    public void addBook(Book book)throws InvalidInputException {
        if(book == null){
            throw new InvalidInputException("book can not be null");
        }
        if(books.containsKey(book.getId())){
            throw new InvalidInputException("Book with ID " + book.getId() + " already exists");
        }
        if (book.getPublicationYear() > 2025) {
            throw new InvalidInputException("Publication year cannot be in the future: " + book.getPublicationYear());
        }
        books.put(book.getId(), book);
    }
    
    //get books
    public Book getBookById(int bookId) throws BookNotFoundException {
    Book book = books.get(bookId);
        if (book == null) {
            throw new BookNotFoundException("Book with ID " + bookId + " not found");
        }
        return book;
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }
    
    //update books
    public void updateBook (int bookId, Book updatedBook) throws BookNotFoundException , InvalidInputException{
        if(updatedBook == null){
            throw new InvalidInputException("Updated book cannot be null");            
        }
        if (!books.containsKey(bookId)) {
            throw new BookNotFoundException("Book with ID " + bookId + " not found");
        }
        if (bookId != updatedBook.getId()) {
            throw new InvalidInputException("Book ID in path (" + bookId + ") does not match book ID in body (" + updatedBook.getId() + ")");
        }
        if (updatedBook.getPublicationYear() > 2025) {
        throw new InvalidInputException("Publication year cannot be in the future: " + updatedBook.getPublicationYear());
        }
    books.put(bookId, updatedBook);
    }
    
    //delete books 
    public void deleteBook(int bookId) throws BookNotFoundException {
        if (!books.containsKey(bookId)) {
            throw new BookNotFoundException("Book with ID " + bookId + " not found");
        }
    books.remove(bookId);
    }
}
