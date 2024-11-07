package com.example.swiggyclone.model;

public class RestaurantDetails
{
    private String name;
    private String rating;
    private String address;
    private String cuisine;
    private String thumbnail;

    public RestaurantDetails(String name, String rating, String cuisine, String address, String thumbnail) {
        this.name = name;
        this.rating = rating;
        this.address = address;
        this.cuisine = cuisine;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
