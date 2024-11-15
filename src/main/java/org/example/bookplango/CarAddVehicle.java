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
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CarAddVehicle {
    @FXML
    private ComboBox<String>VehicleType;
    @FXML
    private ComboBox<String>AC;
    @FXML
    private TextField licsence;
    @FXML
    private TextField seat;
    @FXML
    private TextField imagePath;
    @FXML
    private TextField price;
    @FXML
    private RadioButton Dha;
    @FXML
    private RadioButton Mym;
    @FXML
    private RadioButton Syl;
    @FXML
    private RadioButton Cha;
    @FXML
    private RadioButton Ran;
    @FXML
    private RadioButton Bar;
    @FXML
    private RadioButton Raj;
    @FXML
    private RadioButton Khu;
    @FXML
    private Button Add;
    @FXML
    private Label ID;
    @FXML
    private Label message;
    String S_ID;
    String Vehicle[][]={{"Bus","30"},{"Mini Bus","15"},{"Car","4"},{"CarXL","7"},{"Micro","13"}};
    String dha,mym,syl,cha,ran,bar,raj,khu,loc;
    private Stage stage;
    private Scene scene;

    @FXML
    public void setData(String s) throws SQLException {
        DatabaseConnection databaseConnection=new DatabaseConnection();
        Connection connection= databaseConnection.getConnection();
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("Select service_location from serviceprovider_info where service_id="+s+" and service_type='Car'");
        while(resultSet.next()){
            loc=resultSet.getString("service_location");
        }
        licsence.setText("");
        seat.setText("");
        price.setText("");
        dha="no";
        mym="no";
        syl="no";
        cha="no";
        ran="no";
        bar="no";
        raj="no";
        khu="no";
        Dha.setSelected(false);
        Ran.setSelected(false);
        Syl.setSelected(false);
        Mym.setSelected(false);
        Raj.setSelected(false);
        Khu.setSelected(false);
        Bar.setSelected(false);
        Cha.setSelected(false);
        S_ID=s;
        ID.setText(s);
        AC.setItems(FXCollections.observableArrayList("AC","Non AC"));
        ObservableList<String>observableList=FXCollections.observableArrayList();
        for(int i=0;i<5;i++){
            observableList.add(Vehicle[i][0]);
        }VehicleType.setItems(observableList);
    }@FXML
    public void setDha(){
        if(Dha.isSelected()){
            dha="Yes";
        }else{
            dha="No";
        }
    }@FXML
    public void setMym(){
        if(Mym.isSelected()){
            mym="Yes";
        }else{
            mym="No";
        }
    }@FXML
    public void setCha(){
        if(Cha.isSelected()){
            cha="Yes";
        }else{
            cha="No";
        }
    }@FXML
    public void setBar(){
        if(Bar.isSelected()){
            bar="Yes";
        }else{
            bar="No";
        }
    }@FXML
    public void setKhu(){
        if(Khu.isSelected()){
            khu="Yes";
        }else{
            khu="No";
        }
    }@FXML
    public void setRan(){
        if(Ran.isSelected()){
            ran="Yes";
        }else{
            ran="No";
        }
    }@FXML
    public void setRaj(){
        if(Raj.isSelected()){
            raj="Yes";
        }else{
            raj="No";
        }
    }@FXML
    public void setSyl(){
        if(Syl.isSelected()){
            syl="Yes";
        }else{
            syl="No";
        }
    }
    @FXML
    public void setSeat(){
        for(int i=0;i<5;i++){
            if(Vehicle[i][0]==VehicleType.getValue()){
                seat.setText(Vehicle[i][1]);
                break;
            }
        }
    }
    @FXML
    public void setAdd() {
        String vt = VehicleType.getValue(), ln = licsence.getText(), sn = seat.getText(), ac = AC.getValue(), pr = price.getText(),ip=imagePath.getText();
        if (vt == null || ln.equals("") || sn.equals("") || ac == null || pr.equals("") || ip.equals("")) {
            message.setText("Fill up all information");
        } else {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getConnection();
            String q = "INSERT INTO car_details\n" +
                    "(CarID,\n" +
                    "LiscenceNum,\n" +
                    "VehicleType,\n" +
                    "Seat,\n" +
                    "Location,\n" +
                    "carStatus,\n" +
                    "price,\n" +
                    "Dhaka,\n" +
                    "Mymensingh,\n" +
                    "Barishal,\n" +
                    "Khulna,\n" +
                    "Chattogram,\n" +
                    "Rajshahi,\n" +
                    "Sylhet,\n" +
                    "Rangpur,\n" +
                    "AC, ImagePath) VALUES\n" +
                    "(" + Integer.parseInt(S_ID) + ",'" + ln + "','" + vt + "','" + sn + "','" + loc + "','Available','" + Integer.parseInt(pr) + "','" + dha + "','" + mym + "','" + bar + "','" + khu + "','" + cha + "','"+raj+"','" + syl + "','" + ran + "','" + ac + "','"+ip+"');";
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(q);
                AC.setItems(null);
                VehicleType.setItems(null);
                message.setText("Vehicle Added");
                setData(S_ID);
            } catch (SQLException e) {
                message.setText("Vehicle Exist with same Licsence number");
            }
        }
    }
    @FXML
    public void switchtoserviceSigninScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("service_signin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }@FXML
    public void back(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("car_welcome.fxml"));
        Parent root1 = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root1);
        stage.setScene(scene);
        stage.setResizable(false);
        CarWelcomeController carWelcomeController=fxmlLoader.getController();
        carWelcomeController.setData(S_ID);
        stage.show();
    }
    @FXML
    public void switchtoserviceUpdateProfile(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader=new FXMLLoader(BookPlanGo_Main.class.getResource("service_edit_profileCar.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("service_edit_profile.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setResizable(false);
        ServiceEditProfileCar serviceEditProfileCar=fxmlLoader.getController();
        serviceEditProfileCar.setdata(S_ID);
        stage.show();
    }
    public void carDashboard(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader=new FXMLLoader(BookPlanGo_Main.class.getResource("car_dashboard.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("service_edit_profile.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setResizable(false);
        CarDashboard carDashboard=fxmlLoader.getController();
        carDashboard.setData(S_ID);
        stage.show();
    }
    @FXML
    public void Booking(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader=new FXMLLoader(BookPlanGo_Main.class.getResource("car_manager_see_booking.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("service_edit_profile.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setResizable(false);
        CarManagerSeeBooking carManagerSeeBooking=fxmlLoader.getController();
        carManagerSeeBooking.setData(S_ID);
        stage.show();
    }

}
