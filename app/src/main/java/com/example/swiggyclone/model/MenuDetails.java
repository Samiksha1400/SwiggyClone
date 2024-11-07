package com.example.swiggyclone.model;

public class MenuDetails
{
    private String dish_name;
    private String rating;
    private String price;
    private String description;
    private String image;

    public MenuDetails(String dish_name, String rating, String price, String description, String image) {
        this.dish_name = dish_name;
        this.rating = rating;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
