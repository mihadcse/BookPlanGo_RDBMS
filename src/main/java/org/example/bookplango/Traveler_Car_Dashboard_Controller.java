package org.example.bookplango;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Traveler_Car_Dashboard_Controller {
    private Stage stage;
    private Scene scene;
    UserWelcomeDashboard user_dash;

    Integer nid;

    String s = "";

    @FXML
    private Label username_label_dashboard;

    @FXML
    private TableView<Traveler_Car_Dashboard> travelerCarDashboardTableView;
    @FXML
    private TableColumn<Traveler_Car_Dashboard,String> travelerCarLisenceTableColumn;
    @FXML
    private TableColumn<Traveler_Car_Dashboard,String>travelerCarstartTableColumn;
    @FXML
    private TableColumn<Traveler_Car_Dashboard,String>travelerCarendTableColumn;
    @FXML
    private TableColumn<Traveler_Car_Dashboard, Date>travelerCarDateTableColumn;
    @FXML
    private TableColumn<Traveler_Car_Dashboard,Integer>travelerCarIDTableColumn;

    public void setWelcome(String name)
    {
        username_label_dashboard.setText(name);
        System.out.println(name);
        s = name;
    }

    public void switchtouserSigninScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("signin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void switchtouserBookingScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user_Booking.fxml"));
        Parent root = fxmlLoader.load();
        UserWelcomeDashboard u_name = fxmlLoader.getController();
        u_name.setWelcome(s);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        initialize();
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
        us.initialize();
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

    ObservableList<Traveler_Car_Dashboard> traveler_cardashboardObservableList = FXCollections.observableArrayList();

    public void initialize () {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        //String query_name = "Select NID from sys.userinfo where Username = '"+username_label_dashboard.getText()+"'";

        try{
            /*Statement statement = connectDB.createStatement();
            ResultSet res = statement.executeQuery(query_name);
            if(res.next()) {
                nid = res.getInt("NID");
            }*/
            Statement statement = connectDB.createStatement();
            String travelerTableViewquery = "Select * from carbookdetails where Username = '"+username_label_dashboard.getText()+"'";

            ResultSet queryOutput = statement.executeQuery (travelerTableViewquery);

            while (queryOutput.next()) {
                Integer queryID = queryOutput.getInt("CarID");
                Date queryDate = queryOutput.getDate("BookingDate");
                String queryStart = queryOutput.getString("Start");
                String queryEnd = queryOutput.getString("End");
                String  queryLisence = queryOutput.getString("CarLicsence");

                traveler_cardashboardObservableList.add(new Traveler_Car_Dashboard(queryID,queryLisence,queryStart,queryEnd,queryDate));
            }

            travelerCarIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("carid"));
            travelerCarLisenceTableColumn.setCellValueFactory (new PropertyValueFactory<>("car_lisence"));
            travelerCarstartTableColumn.setCellValueFactory (new PropertyValueFactory<>("start_d"));
            travelerCarendTableColumn.setCellValueFactory (new PropertyValueFactory<>("end_d"));
            travelerCarDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

            travelerCarDashboardTableView.setItems (traveler_cardashboardObservableList);

        }
        catch(SQLException e) {
            Logger.getLogger (Traveler_Car_Dashboard.class.getName()).log (Level. SEVERE,null, e);
            e.printStackTrace();
        }
    }
}
