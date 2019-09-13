/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author tayad
 */
public class Books {
    
    private SimpleStringProperty title,authour,year,rating;
    private SimpleFloatProperty price;
    private SimpleIntegerProperty status,id;

    public Books() {
    }

    public Books(int id,String title, String authour, String year, String rating, float price, int status) {
        this.title = new SimpleStringProperty(title);
        this.authour = new SimpleStringProperty(authour);
        this.year = new SimpleStringProperty(year);
        this.rating = new SimpleStringProperty(rating);
        this.price = new SimpleFloatProperty(price);
        this.status = new SimpleIntegerProperty(status);
        this.id = new SimpleIntegerProperty(id);
    }

    public int getId() {
        return id.getValue();
    }

    public void setId(SimpleIntegerProperty id) {
        this.id = id;
    }
    
    

    public String getTitle() {
        return title.getValue();
    }

    public void setTitle(SimpleStringProperty title) {
        this.title = title;
    }

    public String getAuthour() {
        return authour.getValue();
    }

    public void setAuthour(SimpleStringProperty authour) {
        this.authour = authour;
    }

    public String getYear() {
        return year.getValue();
    }

    public void setYear(SimpleStringProperty year) {
        this.year = year;
    }

    public String getRating() {
        return rating.getValue();
    }

    public void setRating(SimpleStringProperty rating) {
        this.rating = rating;
    }

    public float getPrice() {
        return price.getValue();
    }

    public void setPrice(SimpleFloatProperty price) {
        this.price = price;
    }

    public int getStatus() {
        return status.getValue();
    }

    public void setStatus(SimpleIntegerProperty status) {
        this.status = status;
    }
       
}
