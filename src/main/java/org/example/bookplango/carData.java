package org.example.bookplango;

import javafx.scene.control.Button;

public class carData {
    Integer price;
    String licsence,type,ac_non_ac,status;
    private Button select;

    public carData(String licsence,Integer price, String type, String ac_non_ac, String status) {
        this.licsence=licsence;
        this.price = price;
        this.type = type;
        this.ac_non_ac = ac_non_ac;
        this.status = status;
        this.select=new Button("SELECT");
    }
    public String getLicsence() {
        return licsence;
    }

    public void setLicsence(String licsence) {
        this.licsence = licsence;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
