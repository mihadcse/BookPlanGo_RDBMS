package org.example.bookplango;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserWelcomeDashboard {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public Label username_label_dashboard;
    String s = "";
    @FXML
    public Label BookingCondirm;
    @FXML
    public Button logout;
    @FXML
    public Button dash;
    @FXML
    public Button book;
    @FXML
    public Button gal;
    @FXML
    public Button msg;
    @FXML
    public Button skip;
    @FXML
    public Button submit;
    @FXML
    public RadioButton rate1;
    @FXML
    public RadioButton rate2;
    @FXML
    public RadioButton rate3;
    @FXML
    public RadioButton rate4;
    @FXML
    public RadioButton rate5;
    @FXML
    public Pane getRating;
    @FXML
    public Label whatrate;
    @FXML
    public Label howrate;
    boolean skipped,rated;
    int rating;
    int index;
    String str;
    String carLicsence = "";
    String roomName = "",hotelName="";

    ObservableList<Integer> hotel = FXCollections.observableArrayList();
    ObservableList<Integer> car = FXCollections.observableArrayList();
    @FXML
    void setSkip() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        Statement statement = connectDB.createStatement();
        String query="";
        if(str.equals("hotel")) {
            query = "Update tourdetails set Rated='Y' where travelID = " + String.valueOf(hotel.get(index)) + ";";
        }else{
            query = "Update carbookdetails set Rated='Y' where travelID = " + String.valueOf(car.get(index)) + ";";
        }statement.executeUpdate (query);
        skipped=true;
    }
    @FXML
    void setRated() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        Statement statement = connectDB.createStatement();
        Statement s = connectDB.createStatement();
        String query="",q="";
        if(str.equals("hotel")) {
            query = "Update tourdetails set Rated='Y' where travelID = " + String.valueOf(hotel.get(index)) + ";";
            q="UPDATE h_roomdetails\n" +
                    "SET\n" +
                    "Rating = Rating+"+String.valueOf(rating)+",\n" +
                    "RatingNum = RatingNum+1\n" +
                    "WHERE Hotel_name = '"+hotelName+"' and\n" +
                    "room_num = '"+roomName+"';";
        }else{
            query = "Update carbookdetails set Rated='Y' where travelID = " + String.valueOf(car.get(index)) + ";";
            q="UPDATE car_details SET Rating = Rating+"+String.valueOf(rating)+",RatingNum = RatingNum+1 WHERE LiscenceNum = '"+carLicsence+"';";
        }
        statement.executeUpdate (query);
        s.executeUpdate(q);
        rated=true;
    }
    @FXML
    void setRate1(){
        submit.setDisable(false);
        rate1.setSelected(true);
        rate2.setSelected(false);
        rate3.setSelected(false);
        rate4.setSelected(false);
        rate5.setSelected(false);
        howrate.setText("1 OUT of 5!");
        rating=1;
    }
    @FXML
    void setRate2(){
        submit.setDisable(false);
        rate1.setSelected(true);
        rate2.setSelected(true);
        rate3.setSelected(false);
        rate4.setSelected(false);
        rate5.setSelected(false);
        howrate.setText("2 OUT of 5!!");
        rating=2;
    }
    @FXML
    void setRate3(){
        submit.setDisable(false);
        rate1.setSelected(true);
        rate2.setSelected(true);
        rate3.setSelected(true);
        rate4.setSelected(false);
        rate5.setSelected(false);
        howrate.setText("3 OUT of 5!!!");
        rating=3;
    }
    @FXML
    void setRate4(){
        submit.setDisable(false);
        rate1.setSelected(true);
        rate2.setSelected(true);
        rate3.setSelected(true);
        rate4.setSelected(true);
        rate5.setSelected(false);
        howrate.setText("4 OUT of 5!!!!");
        rating=4;
    }
    @FXML
    void setRate5(){
        submit.setDisable(false);
        rate1.setSelected(true);
        rate2.setSelected(true);
        rate3.setSelected(true);
        rate4.setSelected(true);
        rate5.setSelected(true);
        howrate.setText("5 OUT of 5!!!!!");
        rating=5;
    }

    @FXML
    public void setRating() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        str = "hotel";

        // Disable UI elements safely before starting the task
        Platform.runLater(() -> {
            msg.setDisable(true);
            gal.setDisable(true);
            dash.setDisable(true);
            book.setDisable(true);
            getRating.setVisible(true);
        });

        Task<Void> ratingTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 0; i < hotel.size(); i++) {
                    index = i;
                    skipped = false;
                    rated = false;

                    Platform.runLater(() -> {
                        submit.setDisable(true);
                        howrate.setText("");
                        whatrate.setText("");
                        rate1.setSelected(false);
                        rate2.setSelected(false);
                        rate3.setSelected(false);
                        rate4.setSelected(false);
                        rate5.setSelected(false);
                    });

                    Statement statement = connectDB.createStatement();
                    String query = "Select room_name, hotel_name from tourdetails where travelID = " + hotel.get(i) + ";";
                    ResultSet resultSet = statement.executeQuery(query);


                    while (resultSet.next()) {
                        roomName= resultSet.getString("room_name");
                        hotelName = resultSet.getString("hotel_name");
                    }

                    String finalCl = "at "+roomName+", "+hotelName;
                    Platform.runLater(() -> whatrate.setText(finalCl));

                    while (!skipped && !rated) {
                        Thread.sleep(100);
                    }
                }

                str = "car";
                for (int i = 0; i < car.size(); i++) {
                    index = i;
                    skipped = false;
                    rated = false;

                    Platform.runLater(() -> {
                        submit.setDisable(true);
                        howrate.setText("");
                        whatrate.setText("");
                        rate1.setSelected(false);
                        rate2.setSelected(false);
                        rate3.setSelected(false);
                        rate4.setSelected(false);
                        rate5.setSelected(false);
                    });

                    Statement statement = connectDB.createStatement();
                    String query = "Select CarLicsence from carbookdetails where travelID = " + car.get(i) + ";";
                    ResultSet resultSet = statement.executeQuery(query);

                    while (resultSet.next()) {
                        carLicsence = resultSet.getString("CarLicsence");
                    }

                    String finalCl = carLicsence;
                    Platform.runLater(() -> whatrate.setText("With " + finalCl));

                    while (!skipped && !rated) {
                        Thread.sleep(100);
                    }
                }

                // Hide rating UI after processing
                Platform.runLater(() -> {
                    getRating.setVisible(false);

                    msg.setDisable(false);
                    gal.setDisable(false);
                    dash.setDisable(false);
                    book.setDisable(false);
                });

                return null;
            }
        };

        new Thread(ratingTask).start();
    }



    @FXML
    public void askForRating() throws SQLException, InterruptedException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        Statement statement0=connectDB.createStatement();
        ResultSet resultSet0=statement0.executeQuery("Select t.travelID from tourdetails t inner join userinfo u on t.traveler_nid=u.NID where u.Username='"+s+"' and t.Rated='N' and t.EndDate<NOW();");

        while(resultSet0.next()){
            hotel.add(resultSet0.getInt("travelID"));
        }
        Statement statement=connectDB.createStatement();
        ResultSet resultSet=statement.executeQuery("select travelID from carbookdetails where Username='"+s+"' and Rated='N' and BookingDate<CURDATE()");
        while(resultSet.next()){
            car.add(resultSet.getInt("travelID"));
        }if(car.size()+hotel.size()>0){
            setRating();
        }

    }

    public void setWelcome(String name) throws SQLException, InterruptedException {
        username_label_dashboard.setText(name);
        System.out.println(name);
        s = name;
        hotel.clear();
        car.clear();
        askForRating();
    }

    public void switchtouserSigninScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("signin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void switchtouserdashboardScene(ActionEvent event) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("user_dashboard.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user_dashboard.fxml"));
        Parent root = fxmlLoader.load();
        Traveler_Dashboard_Controller user_name_dashboard_control = fxmlLoader.getController();
        user_name_dashboard_control.setWelcome(s);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        Traveler_Dashboard_Controller traveler_Control = fxmlLoader.getController();
        traveler_Control.initialize();
    }

    public void switchtouserBookingScene(ActionEvent event) throws IOException, SQLException, InterruptedException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user_Booking.fxml"));
        Parent root = fxmlLoader.load();
        UserWelcomeDashboard us = fxmlLoader.getController();
        us.setWelcome(s);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void switchtouserBooking_HotelScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user_Booking_hotel.fxml"));
        Parent root = fxmlLoader.load();
        Traveler_Booking_Controller user_name_dashboard_control = fxmlLoader.getController();
        user_name_dashboard_control.setWelcome(s);
        //setWelcome(s);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        user_name_dashboard_control.initialize_hotel();
    }

    public void switchtouserBooking_CarScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user_Booking_car.fxml"));
        Parent root = fxmlLoader.load();
        //setWelcome(s);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        Traveler_Car_Searching travelerCarSearching=fxmlLoader.getController();
        travelerCarSearching.setData(s);
        stage.show();
    }

    public void switchtouserPlanningScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("traveler_planning.fxml"));
        Parent root = fxmlLoader.load();
        Traveler_Planning_Controller us = fxmlLoader.getController();
        us.setWelcome(s);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void switchtouserMessageScene(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("message.fxml"));
        Parent root = fxmlLoader.load();
        Traveler_Message_Controller us = fxmlLoader.getController();
        us.setWelcome(s);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}