package org.example.bookplango;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Traveler_Dashboard_Controller {

    private Stage stage;
    private Scene scene;
    private Integer nid;
    private String s = "";

    @FXML
    private Label username_label_dashboard;

    @FXML
    private TableView<Traveler_Dashboard> travelerDashboardTableView;
    @FXML
    private TableColumn<Traveler_Dashboard, String> travelerHotelTableColumn;
    @FXML
    private TableColumn<Traveler_Dashboard, String> travelerDestinationTableColumn;
    @FXML
    private TableColumn<Traveler_Dashboard, Integer> travelerExpensesTableColumn;
    @FXML
    private TableColumn<Traveler_Dashboard, Date> travelerStartTableColumn;
    @FXML
    private TableColumn<Traveler_Dashboard, Date> travelerEndTableColumn;

    ObservableList<Traveler_Dashboard> traveler_dashboardObservableList = FXCollections.observableArrayList();

    public void setWelcome(String name) {
        username_label_dashboard.setText(name);
        s = name;
    }

    public void initialize() {
        traveler_dashboardObservableList.clear();
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String queryNid = "SELECT NID FROM bookplango.userinfo WHERE Username = ?";
        String travelerTableQuery = "SELECT hotel_name, StartDate, EndDate, Destination, Total_Expenses " +
                "FROM bookplango.tourdetails WHERE traveler_nid = ?";

        try {
            PreparedStatement nidStmt = connectDB.prepareStatement(queryNid);
            nidStmt.setString(1, username_label_dashboard.getText());
            ResultSet res = nidStmt.executeQuery();
            if (res.next()) {
                nid = res.getInt("NID");

                PreparedStatement bookingStmt = connectDB.prepareStatement(travelerTableQuery);
                bookingStmt.setInt(1, nid);
                ResultSet bookings = bookingStmt.executeQuery();

                while (bookings.next()) {
                    traveler_dashboardObservableList.add(new Traveler_Dashboard(
                            bookings.getInt("Total_Expenses"),
                            bookings.getString("Destination"),
                            bookings.getString("hotel_name"),
                            bookings.getDate("StartDate"),
                            bookings.getDate("EndDate")
                    ));
                }
            }

            travelerHotelTableColumn.setCellValueFactory(new PropertyValueFactory<>("hotel_name"));
            travelerDestinationTableColumn.setCellValueFactory(new PropertyValueFactory<>("Destination"));
            travelerStartTableColumn.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
            travelerEndTableColumn.setCellValueFactory(new PropertyValueFactory<>("EndDate"));
            travelerExpensesTableColumn.setCellValueFactory(new PropertyValueFactory<>("Total_Expenses"));

            travelerDashboardTableView.setItems(traveler_dashboardObservableList);

        } catch (SQLException e) {
            Logger.getLogger(Traveler_Dashboard_Controller.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }

    @FXML
    private void cancelHotelBooking(ActionEvent event) {
        Traveler_Dashboard selectedBooking = travelerDashboardTableView.getSelectionModel().getSelectedItem();

        if (selectedBooking == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a booking to cancel.");
            alert.showAndWait();
            return;
        }

        Date today = new Date();
        Date bookingStartDate = selectedBooking.getStartDate();
        
        if (!bookingStartDate.after(today)) {
            Alert showalert = new Alert(Alert.AlertType.WARNING);
            showalert.setTitle("Cancellation Not Allowed");
            showalert.setHeaderText(null);
            showalert.setContentText("You cannot cancel a past or ongoing booking.");
            showalert.showAndWait();
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Cancellation");
        confirm.setHeaderText(null);
        confirm.setContentText("Are you sure you want to cancel this booking?");
        Optional<ButtonType> result = confirm.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                DatabaseConnection connectNow = new DatabaseConnection();
                Connection connectDB = connectNow.getConnection();

                String deleteQuery = "DELETE FROM tourdetails WHERE traveler_nid = ? AND hotel_name = ? AND StartDate = ? AND EndDate = ?";
                PreparedStatement deleteStmt = connectDB.prepareStatement(deleteQuery);
                deleteStmt.setInt(1, nid);
                deleteStmt.setString(2, selectedBooking.getHotel_name());
                deleteStmt.setDate(3, new java.sql.Date(selectedBooking.getStartDate().getTime()));
                deleteStmt.setDate(4, new java.sql.Date(selectedBooking.getEndDate().getTime()));

                int rows = deleteStmt.executeUpdate();
                if (rows > 0) {
                    travelerDashboardTableView.getItems().remove(selectedBooking);

                    Alert success = new Alert(Alert.AlertType.INFORMATION);
                    success.setTitle("Success");
                    success.setHeaderText(null);
                    success.setContentText("Booking cancelled successfully.");
                    success.showAndWait();
                } else {
                    Alert fail = new Alert(Alert.AlertType.ERROR);
                    fail.setTitle("Error");
                    fail.setHeaderText(null);
                    fail.setContentText("Failed to cancel booking.");
                    fail.showAndWait();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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

    public void switchtouserCarDashboardScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user_dashboard_car.fxml"));
        Parent root = fxmlLoader.load();
        Traveler_Car_Dashboard_Controller us = fxmlLoader.getController();
        us.setWelcome(s);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        us.initialize();
    }
}
