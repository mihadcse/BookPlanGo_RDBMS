package org.example.bookplango;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HotelEditRoom {
    private Stage stage;
    private Scene scene;
    String S_ID,room_num;
    @FXML
    public TextField price;
    public Label rn;
    @FXML
    public ComboBox<String>Bedding;
    @FXML
    public ComboBox<String> AC;
    String cAC,cBedding;
    int cPrice;
    @FXML
    public Label message;

    public void setData(String name, String r_n) throws SQLException {
        S_ID=name;
        this.room_num =r_n;
        Bedding.setItems(FXCollections.observableArrayList("Single","Double","triple"));
        AC.setItems(FXCollections.observableArrayList("AC","Non AC"));
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        Statement statement=connectDB.createStatement();
        ResultSet resultSet=statement.executeQuery("SELECT bedding, room_ac,room_price \n" +
                "FROM h_roomdetails\n" +
                "where Hotel_ID="+Integer.parseInt(S_ID)+" and room_num='"+ this.room_num +"';");
        while(resultSet.next()){
            cPrice=resultSet.getInt("room_price");
            cAC=resultSet.getString("room_ac");
            cBedding=resultSet.getString("bedding");
        }price.setText(String.valueOf(cPrice));
        AC.setValue(cAC);
        Bedding.setValue(cBedding);
        rn.setText(this.room_num);
    }
    @FXML
    public void back(ActionEvent event) throws SQLException, IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Hotel_dashboard.fxml"));
        Parent root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        Hotel_Dashboard_Controller hotel_name = fxmlLoader.getController();
        hotel_name.setWelcome(S_ID);
        stage.show();
    }
    @FXML
    public void setDone(ActionEvent event) throws SQLException, IOException {
        if(price.getText().equals("")){
            message.setText("Fill Up Everything");
        }else{
            String price1,ac1,bedding1;
            ac1=AC.getValue();
            bedding1=Bedding.getValue();
            price1=price.getText();
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();
            Statement statement=connectDB.createStatement();
            statement.executeUpdate("UPDATE h_roomdetails\nSET bedding = '"+bedding1+"',room_ac= '"+ac1+"',room_price ="+Integer.parseInt(price1)+" WHERE Hotel_ID="+Integer.parseInt(S_ID)+" AND room_num = '"+room_num+"'");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Hotel_dashboard.fxml"));
            Parent root = fxmlLoader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            Hotel_Dashboard_Controller hotel_name = fxmlLoader.getController();
            hotel_name.setWelcome(S_ID);
            hotel_name.setMessage();
            stage.show();
        }
    }
}
