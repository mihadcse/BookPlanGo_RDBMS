package org.example.bookplango;

import javafx.scene.control.Button;

public class Hotel_Dashboard {
    Integer price;
    String room_num,bedding,ac_non_ac,status;
    private Button select;

    public Hotel_Dashboard(String room_num,Integer price, String room_type, String ac_non_ac, String status) {
        this.room_num=room_num;
        this.price = price;
        this.bedding = room_type;
        this.ac_non_ac = ac_non_ac;
        this.status = status;
        this.select=new Button("SELECT");
    }
    public String getRoom_num() {
        return room_num;
    }

    public void setRoom_num(String room_num) {
        this.room_num = room_num;
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

    public String getAc_non_ac() {
        return ac_non_ac;
    }

    public void setAc_non_ac(String ac_non_ac) {
        this.ac_non_ac = ac_non_ac;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Button getSelect() {
        return select;
    }

    public void setSelect(Button button) {
        this.select = button;
    }
}
