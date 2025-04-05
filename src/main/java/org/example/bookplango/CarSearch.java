package org.example.bookplango;

import javafx.scene.control.Button;

public class CarSearch {
    Integer price;
    String vehicle_type,seat,rating;
    private Button select;


    public CarSearch(String vehicle_type, String seat,Integer price,String rating) {
        this.vehicle_type=vehicle_type;
        this.price = price;
        this.seat = seat;
        this.rating=rating;
        this.select=new Button("SELECT");
    }
    public String getVType() {
        return vehicle_type;
    }

    public void setVType(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getSNum() {
        return seat;
    }

    public void setSNum(String seat) {
        this.seat = seat;
    }
    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating= rating;
    }

    public Button getSelect() {
        return select;
    }

    public void setSelect(Button button) {
        this.select = button;
    }
}
