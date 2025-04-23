/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.storage;

import com.mycompany.bookstore.exception.AuthorNotFoundException;
import com.mycompany.bookstore.exception.BookNotFoundException;
import com.mycompany.bookstore.exception.CustomerNotFoundException;
import com.mycompany.bookstore.exception.InvalidInputException;
import com.mycompany.bookstore.model.Author;
import com.mycompany.bookstore.model.Book;
import com.mycompany.bookstore.model.Cart;
import com.mycompany.bookstore.model.Customer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author devinda
 */
public class InMemoryStore {
    
    private Map<Integer, Book> books;
    private Map<Integer, Author> authors;
    private Map<Integer, Customer> customers;
    private Map<Integer, Cart> carts;

    public InMemoryStore() {
        this.books = new HashMap<>();
        this.authors = new HashMap<>();    
        this.customers = new HashMap<>();    
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

    //get all books
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
    
    
    
    
    
    //add author
    public void addAuthor (Author author)throws InvalidInputException {
        if(author == null){
            throw new InvalidInputException("Author cannot be null");
        }
        if(authors.containsKey(author.getId())){
            throw new InvalidInputException("Author with ID " + author.getId() + " already exists");
        }
       authors.put(author.getId(), author);

    }
    
    //get author by ID
    public Author getAuthorById(int authorId) throws AuthorNotFoundException {
        Author author = authors.get(authorId);
        if(author == null){
            throw new AuthorNotFoundException ("Author with ID " + authorId + " not found");
        }
        return author;
    }
    
    //get all authors 
    public List<Author> getAllAuthors(){
        return new ArrayList<>(authors.values());
    }
    
    //update author 
    public void updateAuthor(int authorId, Author updatedAuthor) throws AuthorNotFoundException, InvalidInputException {
        if(updatedAuthor == null){
            throw new InvalidInputException ("Updated author can not be null");
        }
        if(!authors.containsKey(authorId)){
            throw new AuthorNotFoundException("Author with ID " + authorId + " not found");        
        } 
        if(authorId != updatedAuthor.getId()){
            throw new InvalidInputException("Author ID in path (" + authorId + ") does not match author ID in body (" + updatedAuthor.getId() + ")");
        }
        authors.put(authorId, updatedAuthor);
    }
    
    //delete author
    public void deleteAuthor(int authorId) throws AuthorNotFoundException {
        if(!authors.containsKey(authorId)){
            throw new AuthorNotFoundException("Author with ID " + authorId + " not found"); 
        }
        authors.remove(authorId);
    }
    
    
    
    //add customer
    public void addCustomer(Customer customer) throws InvalidInputException{
        if(customer == null){
            throw new InvalidInputException("Customer can not be null");
        }
        if(customers.containsKey(customer.getId())){
            throw new InvalidInputException("Customer with ID " + customer.getId() + " already exists");
        }
        customers.put(customer.getId(), customer);

    }
    
    //get customer by Id
    public Customer getCustomerById(int customerId) throws CustomerNotFoundException{
        Customer customer = customers.get(customerId);
        if(customer == null){
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found");
        }
        return customer;
    }
    
    //get all customers 
    public List<Customer> getAllCustomers(){
        return new ArrayList<>(customers.values());
    }
    
    //update customer
    public void updateCustomer (int customerId, Customer updatedCustomer) throws CustomerNotFoundException, InvalidInputException{
        if(updatedCustomer == null){
            throw new InvalidInputException ("Updated customer can not be null");
        }
        if(!customers.containsKey(customerId)){
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found");        
        } 
        if(customerId != updatedCustomer.getId()){
            throw new InvalidInputException("Customer ID in path (" + customerId + ") does not match author ID in body (" + updatedCustomer.getId() + ")");
        }
        customers.put(customerId, updatedCustomer);
    }
    
    //delete customer
    public void deleteCustomer(int customerId) throws CustomerNotFoundException {
        if(!customers.containsKey(customerId)){
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found"); 
        }
        customers.remove(customerId);
    }
    
    
    //add items to cart
    public void addItemToCart(int customerId, int bookId, int quantity ) throws CustomerNotFoundException , BookNotFoundException, InvalidInputException {
        if(!customers.containsKey(customerId)){
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found");
        }
        if(!books.containsKey(bookId)){
            throw new BookNotFoundException ("Book with ID " + bookId + " not found");
        }
        if(quantity<=0){
            throw new InvalidInputException("Quantity must be greater than zero");
        }
        
        Book book = books.get(bookId);
        Cart cart = carts.get(customerId);
        
        int totalQuantity = quantity;
        if(cart != null && cart.getItems().containsKey(bookId) ){
            totalQuantity += cart.getItems().get(bookId);
        }
        if (totalQuantity > book.getStock()) {
            throw new OutOfStockException("Not enough stock for book ID " + bookId + ". Requested: " + totalQuantity + ", Available: " + book.getStock());
        }
        
        //no cart exists, create a new one
        if (cart == null) {
        cart = new Cart(customerId);
        }
        cart.getItems().put(bookId, totalQuantity);
        carts.put(customerId, cart);
    }
    
    
}




















