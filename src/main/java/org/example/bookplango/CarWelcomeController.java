package org.example.bookplango;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class CarWelcomeController {
    @FXML
    public Label ID;
    @FXML
    public Label Total;
    @FXML
    public Label Booked;
    @FXML
    public Label Available;
    @FXML
    public Label Unavailable;
    String S_ID;
    private Stage stage;
    private Scene scene;

    @FXML
    public void setData(String s) throws SQLException {
        String date = String.valueOf(LocalDate.now());
        System.out.println(date);
        S_ID = s;
        ID.setText(s);
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        Statement statement0 = connectDB.createStatement();
        statement0.executeUpdate("UPDATE car_details SET carStatus = 'Available' WHERE LiscenceNum  in (select CarLicsence from carbookdetails where BookingDate!='" + date + "');");
        Statement statement1 = connectDB.createStatement();
        statement1.executeUpdate("UPDATE car_details SET carStatus = 'Booked' WHERE LiscenceNum  in (select CarLicsence from carbookdetails where BookingDate='" + date + "');");
        Statement statement2 = connectDB.createStatement();
        statement2.executeUpdate("DELETE FROM carbookdetails WHERE BookingDate<'" + date + "';");
        int total = -1, booked = -1, available = -1, unavailable = -1;
        Statement statement = connectDB.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT count(*) as total from car_details where carID=" + S_ID + ";");
        while (resultSet.next()) {
            total = resultSet.getInt("total");
        }
        resultSet = statement.executeQuery("SELECT count(*) as available from car_details where carID=" + S_ID + " and carStatus='Available';");
        while (resultSet.next()) {
            ;
            available = resultSet.getInt("available");
        }
        resultSet = statement.executeQuery("SELECT count(*) as booked from car_details where carID=" + S_ID + " and carStatus='Booked';");
        while (resultSet.next()) {
            ;
            booked = resultSet.getInt("booked");
        }
        resultSet = statement.executeQuery("SELECT count(*) as unavailable from car_details where carID=" + S_ID + " and carStatus='Unavailable';");
        while (resultSet.next()) {
            ;
            unavailable = resultSet.getInt("unavailable");
        }
        Total.setText(String.valueOf(total));
        Available.setText(String.valueOf(available));
        Booked.setText(String.valueOf(booked));
        Unavailable.setText(String.valueOf(unavailable));
    }

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
        FXMLLoader fxmlLoader = new FXMLLoader(BookPlanGo_Main.class.getResource("service_edit_profileCar.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("service_edit_profile.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setResizable(false);
        ServiceEditProfileCar serviceEditProfileCar = fxmlLoader.getController();
        serviceEditProfileCar.setdata(S_ID);
        stage.show();
    }

    @FXML
    public void AddVehicle(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(BookPlanGo_Main.class.getResource("carAddVehicle.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("service_edit_profile.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setResizable(false);
        CarAddVehicle carAddVehicle = fxmlLoader.getController();
        carAddVehicle.setData(S_ID);
        stage.show();
    }

    @FXML
    public void carDashboard(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(BookPlanGo_Main.class.getResource("car_dashboard.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("service_edit_profile.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setResizable(false);
        CarDashboard carDashboard = fxmlLoader.getController();
        carDashboard.setData(S_ID);
        stage.show();
    }

    @FXML
    public void Booking(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(BookPlanGo_Main.class.getResource("car_manager_see_booking.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("service_edit_profile.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setResizable(false);
        CarManagerSeeBooking carManagerSeeBooking = fxmlLoader.getController();
        carManagerSeeBooking.setData(S_ID);
        stage.show();
    }

    @FXML
    public void switchtoCarmessageScene(ActionEvent event) throws IOException, SQLException {
        //Parent root = FXMLLoader.load(getClass().getResource("user_dashboard.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("car_message.fxml"));
        Parent root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        Car_Message_Controller car_name = fxmlLoader.getController();
        car_name.setWelcome(S_ID);
        car_name.initialize();

    }

}