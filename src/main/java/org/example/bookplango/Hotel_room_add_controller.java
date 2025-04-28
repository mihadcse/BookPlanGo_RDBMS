package org.example.bookplango;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hotel_room_add_controller {
    private Stage stage;
    private Scene scene;
    @FXML
    public Label Welcome;
    String S_ID,hotelName;

    @FXML
    public void switchtoserviceSigninScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("service_signin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    public void switchtoserviceUpdateProfile(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader=new FXMLLoader(BookPlanGo_Main.class.getResource("service_edit_profile.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("service_edit_profile.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setResizable(false);
        Service_Edit_Profile_Controller serviceEditProfileController=fxmlLoader.getController();
        serviceEditProfileController.setdata(S_ID);
        stage.show();
    }
    @FXML
    private Label notification;
    @FXML
    private ComboBox<String> SelectBedding;
    @FXML
    private ComboBox<String> SelectACType;
    @FXML
    private ComboBox<String> SelectFloor;
    @FXML
    private TextField PriceText;
    @FXML
    private TextField RoomNumText;
    @FXML
    private Button AddRoom;
    String bedding,ac,price,floor,rn;
    @FXML
    public void setdata(String s) throws SQLException{
        S_ID=s;
        Welcome.setText(s);
        PriceText.setText("");
        RoomNumText.setText("");
        DatabaseConnection databaseConnection=new DatabaseConnection();
        Connection connection= databaseConnection.getConnection();
        ObservableList<String> observableListFloor=FXCollections.observableArrayList();
        SelectBedding.setItems(FXCollections.observableArrayList("Single","Double","triple"));
        SelectACType.setItems(FXCollections.observableArrayList("AC","Non AC"));
        try{
            Statement statement0=connection.createStatement();
            ResultSet resultSet0=statement0.executeQuery("select service_name from serviceprovider_info where service_id="+s+";");
            while (resultSet0.next()){
                hotelName=resultSet0.getString("service_name");
            }
            int x = 0;
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select Floor from serviceprovider_info where service_id="+s+"");
            while(resultSet.next()){
                x=resultSet.getInt("Floor");
            }for(int i=1;i<=x;i++){
                observableListFloor.add(String.valueOf(i));
            }SelectFloor.setItems(observableListFloor);
        } catch (SQLException e) {
            notification.setText("Something went wrong");
        }
    }
    @FXML
    protected void setAddRoom() throws SQLException {
        rn=RoomNumText.getText();
        price=PriceText.getText();
        floor=SelectFloor.getValue();
        bedding=SelectBedding.getValue();
        ac=SelectACType.getValue();
        if(ac==null || bedding==null || floor==null || price==null || rn==null){
            notification.setText("Information Missing");
        }else{
            DatabaseConnection databaseConnection=new DatabaseConnection();
            Connection connection= databaseConnection.getConnection();
            try {
                Statement statement=connection.createStatement();
                statement.executeUpdate("INSERT INTO h_roomdetails (Hotel_ID, Hotel_name, room_num, bedding, room_status, room_ac, room_floor, room_price,Rating,RatingNum) VALUES ("+Integer.parseInt(S_ID)+",'"+hotelName+"','"+rn+"', '"+bedding+"','Available' , '"+ac+"', "+Integer.parseInt(floor)+","+Integer.parseInt(price)+",0,0);");
                SelectBedding.setItems(null);
                SelectACType.setItems(null);
                SelectFloor.setItems(null);
                notification.setText("Room Added");
                setdata(S_ID);
            } catch (SQLException e) {
                notification.setText("Room Exist with same Room number");
            }
        }
    }
    @FXML
    public void switchtoHoteldashboardScene(ActionEvent event) throws IOException, SQLException {
        //Parent root = FXMLLoader.load(getClass().getResource("user_dashboard.fxml"));
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
    public void switchtoHotelmessageScene(ActionEvent event) throws IOException, SQLException {
        //Parent root = FXMLLoader.load(getClass().getResource("user_dashboard.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("service_message.fxml"));
        Parent root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        Service_Message_Controller hotel_name = fxmlLoader.getController();
        hotel_name.setWelcome(S_ID);
        hotel_name.initialize();
        stage.show();
    }

    @FXML
    public void back(ActionEvent event) throws SQLException, IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Hotel_welcomepage.fxml"));
        Parent root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        HotelWelcomeDashboard hotelWelcomeDashboard = fxmlLoader.getController();
        hotelWelcomeDashboard.setWelcome(S_ID);
        stage.show();
    }

}
