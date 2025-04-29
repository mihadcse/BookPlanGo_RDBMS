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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Traveler_Dashboard_Controller {
    private Stage stage;
    private Scene scene;
    UserWelcomeDashboard user_dash;
    Integer nid;
    String s = "";

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

    public void initialize() {
        traveler_dashboardObservableList.clear(); // ensure it's cleared before reloading

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String query_name = "SELECT NID FROM bookplango.userinfo WHERE Username = '" + username_label_dashboard.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet res = statement.executeQuery(query_name);
            if (res.next()) {
                nid = res.getInt("NID");
            }

            String travelerTableViewquery = "SELECT `tourdetails`.`traveler_nid`, " +
                    "`tourdetails`.`hotel_name`, " +
                    "`tourdetails`.`StartDate`, " +
                    "`tourdetails`.`EndDate`, " +
                    "`tourdetails`.`Destination`, " +
                    "`tourdetails`.`Total_Expenses` " +
                    "FROM `bookplango`.`tourdetails` WHERE `tourdetails`.`traveler_nid` = '" + nid + "'";

            ResultSet queryOutput = statement.executeQuery(travelerTableViewquery);

            while (queryOutput.next()) {
                String queryDestination = queryOutput.getString("Destination");
                Date queryStartdate = queryOutput.getDate("StartDate");
                Date queryEnddate = queryOutput.getDate("EndDate");
                Integer queryExpenses = queryOutput.getInt("Total_Expenses");
                String queryHotelName = queryOutput.getString("hotel_name");

                traveler_dashboardObservableList.add(new Traveler_Dashboard(
                        queryExpenses,
                        queryDestination,
                        queryHotelName,
                        queryStartdate,
                        queryEnddate
                ));
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
            System.out.println("No booking selected for cancellation.");
            return;
        }

        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();

            String hotel = selectedBooking.getHotel_name();
            String startDate = selectedBooking.getStartDate().toString();
            String endDate = selectedBooking.getEndDate().toString();

            String query = "DELETE FROM tourdetails WHERE traveler_nid = '" + nid + "' " +
                    "AND hotel_name = '" + hotel + "' " +
                    "AND StartDate = '" + startDate + "' " +
                    "AND EndDate = '" + endDate + "'";

            Statement statement = connectDB.createStatement();
            int rowsAffected = statement.executeUpdate(query);

            if (rowsAffected > 0) {
                travelerDashboardTableView.getItems().remove(selectedBooking);
                System.out.println("Booking cancelled successfully.");
            } else {
                System.out.println("No matching booking found to cancel.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
