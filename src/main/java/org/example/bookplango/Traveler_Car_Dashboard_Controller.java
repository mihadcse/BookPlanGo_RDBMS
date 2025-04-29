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
import java.sql.*;
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
    private TableColumn<Traveler_Car_Dashboard, String> travelerCarLisenceTableColumn;
    @FXML
    private TableColumn<Traveler_Car_Dashboard, String> travelerCarstartTableColumn;
    @FXML
    private TableColumn<Traveler_Car_Dashboard, String> travelerCarendTableColumn;
    @FXML
    private TableColumn<Traveler_Car_Dashboard, Date> travelerCarDateTableColumn;
    @FXML
    private TableColumn<Traveler_Car_Dashboard, Integer> travelerCarIDTableColumn;

    @FXML
    private Button cancelBookingButton;

    ObservableList<Traveler_Car_Dashboard> traveler_cardashboardObservableList = FXCollections.observableArrayList();

    public void setWelcome(String name) {
        username_label_dashboard.setText(name);
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

    public void switchtouserBookingScene(ActionEvent event) throws IOException, SQLException, InterruptedException {
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

    public void switchtouserDashboardScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user_dashboard.fxml"));
        Parent root = fxmlLoader.load();
        Traveler_Dashboard_Controller us = fxmlLoader.getController();
        us.setWelcome(s);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        us.initialize();
    }

    public void initialize() {
        traveler_cardashboardObservableList.clear();
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        try {
            Statement statement = connectDB.createStatement();
            String travelerTableViewquery = "SELECT * FROM carbookdetails WHERE Username = '" + s + "'";
            ResultSet queryOutput = statement.executeQuery(travelerTableViewquery);

            while (queryOutput.next()) {
                Integer queryTravelID = queryOutput.getInt("travelID");
                Date queryDate = queryOutput.getDate("BookingDate");
                String queryStart = queryOutput.getString("Start");
                String queryEnd = queryOutput.getString("End");
                String queryLisence = queryOutput.getString("CarLicsence");

                traveler_cardashboardObservableList.add(new Traveler_Car_Dashboard(queryTravelID, queryLisence, queryStart, queryEnd, queryDate));
            }

            travelerCarIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("travelID"));
            travelerCarLisenceTableColumn.setCellValueFactory(new PropertyValueFactory<>("car_lisence"));
            travelerCarstartTableColumn.setCellValueFactory(new PropertyValueFactory<>("start_d"));
            travelerCarendTableColumn.setCellValueFactory(new PropertyValueFactory<>("end_d"));
            travelerCarDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

            travelerCarDashboardTableView.setItems(traveler_cardashboardObservableList);

        } catch (SQLException e) {
            Logger.getLogger(Traveler_Car_Dashboard.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    public void cancelCarBooking(ActionEvent event) {
        Traveler_Car_Dashboard selectedBooking = travelerCarDashboardTableView.getSelectionModel().getSelectedItem();
        if (selectedBooking == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a booking to cancel.");
            return;
        }

        try {
            Date bookingdate = selectedBooking.getDate();
            Date today = new Date();
            System.out.println("Start date raw string: " + bookingdate);

            if (!bookingdate.after(today)) {
                showAlert(Alert.AlertType.WARNING, "Invalid Operation", "You cannot cancel a past or ongoing booking.");
                return;
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Date Error", "Could not parse the booking start date.");
            Logger.getLogger(Traveler_Car_Dashboard_Controller.class.getName()).log(Level.SEVERE, null, e);
            return;
        }

        if (deleteBookingFromDatabase(selectedBooking)) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Booking cancelled successfully.");
            initialize(); // Refresh the table view
        } else {
            showAlert(Alert.AlertType.ERROR, "Failed", "Failed to cancel booking.");
        }
    }

    private boolean deleteBookingFromDatabase(Traveler_Car_Dashboard selectedBooking) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String deleteQuery = "DELETE FROM carbookdetails WHERE travelID = ? AND Username = ?";
        try (PreparedStatement preparedStatement = connectDB.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, selectedBooking.getTravelID());
            preparedStatement.setString(2, s);

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
            Logger.getLogger(Traveler_Car_Dashboard.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}