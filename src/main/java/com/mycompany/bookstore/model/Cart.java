/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.model;

import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author devinda
 */
public class Cart {
    private int customerId;
    private Map<Integer, Integer> items; // Map of bookId to quantity

    // Constructor
    public Cart() {
        this.items = new HashMap<>();
    }

    public Cart(int customerId) {
        this.customerId = customerId;
        this.items = new HashMap<>();
    }

    // Getters and Setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Map<Integer, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Integer, Integer> items) {
        this.items = items;
    }

    // Methods to manage cart items
    public void addItem(int bookId, int quantity) {
        items.put(bookId, items.getOrDefault(bookId, 0) + quantity);
    }

    public void updateItem(int bookId, int quantity) {
        if (items.containsKey(bookId)) {
            if (quantity <= 0) {
                items.remove(bookId);
            } else {
                items.put(bookId, quantity);
            }
        }
    }

    public void removeItem(int bookId) {
        items.remove(bookId);
    }

    public void clearCart() {
        items.clear();
    }
}