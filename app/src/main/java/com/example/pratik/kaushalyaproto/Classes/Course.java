package com.example.pratik.kaushalyaproto.Classes;

import java.io.Serializable;

public class Course implements Serializable {

    String id;
    String name, category, rating;
    String imageurl;

    public Course(String id, String name, String category, String rating, String imageurl) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.rating = rating;
        this.imageurl = imageurl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
