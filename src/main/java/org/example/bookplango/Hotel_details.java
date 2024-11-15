package org.example.bookplango;

public class Hotel_details {

    Integer Room_number, price;
    String bedding, ac;

    public Hotel_details(Integer room_number, Integer price, String bedding, String ac) {
        Room_number = room_number;
        this.price = price;
        this.bedding = bedding;
        this.ac = ac;
    }

    public Integer getRoom_number() {
        return Room_number;
    }

    public void setRoom_number(Integer room_number) {
        Room_number = room_number;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getBedding() {
        return bedding;
    }

    public void setBedding(String bedding) {
        this.bedding = bedding;
    }

    public String getAc() {
        return ac;
    }

    public void setAc(String ac) {
        this.ac = ac;
    }
}
