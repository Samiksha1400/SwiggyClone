package com.example.swiggyclone.model;

public class CartItem {
    private String dishName;
    private double price;
    private String image;
    private int quantity;

    public CartItem(String dishName, double price, String image, int quantity) {
        this.dishName = dishName;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
    }

    // Getters and setters
    public String getDishName() { return dishName; }
    public double getPrice() { return price; }
    public String getImage() { return image; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
