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
public class Order {
    private int orderId;
    private int customerId;
    private Map<Integer, Integer> items; 
    private double totalPrice;

     // Constructor
    public Order() {
        this.items = new HashMap<>();
    }

    public Order(int orderId, int customerId, Map<Integer, Integer> items, double totalPrice) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.items = new HashMap<>(items); 
        this.totalPrice = totalPrice;
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}