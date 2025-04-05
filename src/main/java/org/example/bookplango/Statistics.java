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
import java.util.logging.Level;
import java.util.logging.Logger;

public class Statistics {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label total_user,total_service_provider, total_total;
    @FXML
    private Label total_expenses;


    public void switchtoUserScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("selectuser.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private TableView<Statistics_Table> hotelstatTableView;
    @FXML
    private TableColumn<Statistics_Table,String> hotelNameTableColumn;
    @FXML
    private TableColumn<Statistics_Table,Integer>hotelCountTableColumn;

    ObservableList<Statistics_Table> statisticsTablesObservableList = FXCollections.observableArrayList();

    @FXML
    private TableView<Statistics_Table> carstatTableView;
    @FXML
    private TableColumn<Statistics_Table,Integer> carIDTableColumn;
    @FXML
    private TableColumn<Statistics_Table,Integer>carCountTableColumn;

    ObservableList<Statistics_Table> statisticsCarTablesObservableList = FXCollections.observableArrayList();

    public void initialize() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        int totalUsers = 0;
        int totalServiceProviders = 0;
        int total = 0;

        try {
            Statement statement = connectDB.createStatement();

            // Get total users
            ResultSet userResult = statement.executeQuery("SELECT COUNT(*) FROM userinfo");
            if (userResult.next()) {
                totalUsers = userResult.getInt(1);
            }

            // Get total service providers
            ResultSet serviceResult = statement.executeQuery("SELECT COUNT(*) FROM serviceprovider_info");
            if (serviceResult.next()) {
                totalServiceProviders = serviceResult.getInt(1);
            }

            // Update UI elements with the count (assuming you have labels like total_user and total_service_provider)
            total_user.setText(String.valueOf(totalUsers));
            total_service_provider.setText(String.valueOf(totalServiceProviders));
            //total_service_provider.setText(String.valueOf(totalServiceProviders));
            total = totalUsers + totalServiceProviders;
            total_total.setText(String.valueOf(total));

            // GET top hotels // GROUP BY ORDER BY
            ResultSet hotelResult = statement.executeQuery("SELECT hotel_name, COUNT(*) AS visit_count\n" +
                    "FROM bookplango.tourdetails\n" +
                    "GROUP BY hotel_name\n" +
                    "ORDER BY visit_count DESC\n" +
                    "LIMIT 5;");
            while (hotelResult.next()) {
                String queryName = hotelResult.getString("hotel_name");
                Integer queryCount = hotelResult.getInt("visit_count");
                statisticsTablesObservableList.add(new Statistics_Table(queryName, queryCount));
            }
            hotelResult.close();

            hotelNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
            hotelCountTableColumn.setCellValueFactory (new PropertyValueFactory<>("count"));

            hotelstatTableView.setItems (statisticsTablesObservableList);

            // GET top cars // GROUP BY ORDER BY
            ResultSet carResult = statement.executeQuery("SELECT CarID, COUNT(*) AS booking_count\n" +
                    "FROM bookplango.carbookdetails\n" +
                    "GROUP BY CarID\n" +
                    "ORDER BY booking_count DESC\n" +
                    "LIMIT 5;");
            while (carResult.next()) {
                Integer queryID = carResult.getInt("carID");
                Integer queryCount = carResult.getInt("booking_count");
                statisticsCarTablesObservableList.add(new Statistics_Table(queryID, queryCount));
            }
            carResult.close();

            carIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
            carCountTableColumn.setCellValueFactory (new PropertyValueFactory<>("count"));

            carstatTableView.setItems (statisticsCarTablesObservableList);

            // Get total expenses
            ResultSet expensesResult = statement.executeQuery("SELECT SUM(Total_Expenses) FROM bookplango.tourdetails");
            if (expensesResult.next()) {
                int totalExpenses = expensesResult.getInt(1);
                total_expenses.setText(String.valueOf(totalExpenses) + " BDT");
            }
            expensesResult.close();


        } catch (SQLException e) {
            Logger.getLogger(Admin_Dashboard_Controller.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }
}
