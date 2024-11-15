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
import java.util.logging.Level;
import java.util.logging.Logger;

public class HotelWelcomeDashboard {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public Label service_label_dashboard;
    @FXML
    private Label total_rooms,available_rooms,booked_rooms;
    @FXML
    private Label unavailable_rooms;
    String s = "";


    public void setWelcome(String name)
    {
        service_label_dashboard.setText(name);
        System.out.println(name);
        s = name;
        Showing_Numbers();
    }

    public void switchtoserviceSigninScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("service_signin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void switchtoHoteldashboardScene(ActionEvent event) throws IOException, SQLException {
        //Parent root = FXMLLoader.load(getClass().getResource("user_dashboard.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Hotel_dashboard.fxml"));
        Parent root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        Hotel_Dashboard_Controller hotel_name = fxmlLoader.getController();
        hotel_name.setWelcome(s);
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
        hotel_name.setWelcome(s);
        hotel_name.initialize();
        stage.show();
    }

    public void Showing_Numbers() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        int totalRooms = 0;
        int totalAvailable = 0;
        int totalBooked = 0;
        int unavailable=0;

        try {
            Statement statement = connectDB.createStatement();

            // Get total users
            ResultSet totalRoomResult = statement.executeQuery("Select count(*) as total from h_roomdetails where Hotel_ID = "+s+";");
            while(totalRoomResult.next()){
                totalRooms=totalRoomResult.getInt("total");
            }

            // Get total service providers
            ResultSet availableRoomResult = statement.executeQuery("Select count(*) as available from h_roomdetails where Hotel_ID = "+s+" And room_status = 'Available'");
            while(availableRoomResult.next()){
                totalAvailable=availableRoomResult.getInt("available");
            }

            ResultSet bookedRoomResult = statement.executeQuery("Select count(*) as booked from h_roomdetails where Hotel_ID = "+s+" And room_status = 'Booked'");
            while(bookedRoomResult.next()){
                totalBooked=bookedRoomResult.getInt("booked");
            }
            ResultSet unavailableRoomResult = statement.executeQuery("Select count(*) as unavailable from h_roomdetails where Hotel_ID = "+s+" And room_status = 'Unavailable'");
            while(unavailableRoomResult.next()){
                unavailable=unavailableRoomResult.getInt("unavailable");
            }
            // Update UI elements with the count (assuming you have labels like total_user and total_service_provider)
            total_rooms.setText(String.valueOf(totalRooms));
            //total_service_provider.setText(String.valueOf(totalServiceProviders));
            available_rooms.setText(String.valueOf(totalAvailable));

            booked_rooms.setText(String.valueOf(totalBooked));

            unavailable_rooms.setText(String.valueOf(unavailable));

            // Debugging statements
            System.out.println("Total Rooms: " + totalRooms);
            System.out.println("Available Rooms: " + totalAvailable);
            System.out.println("Booked Rooms: " + totalBooked);

        } catch (SQLException e) {
            Logger.getLogger(HotelWelcomeDashboard.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }
    @FXML
    public void switchtoAddRoomScene(ActionEvent event) throws IOException, SQLException {
        //Parent root = FXMLLoader.load(getClass().getResource("user_dashboard.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hotel_room-add_controller.fxml"));
        Parent root = fxmlLoader.load();
        Hotel_room_add_controller hotelRoomAddController = fxmlLoader.getController();
        hotelRoomAddController.setdata(s);
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
        serviceEditProfileController.setdata(s);
        stage.show();
    }
}
