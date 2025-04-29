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
import javafx.scene.control.RadioButton;
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

public class More_Statistics {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private RadioButton star5;
    @FXML
    private RadioButton star4;
    @FXML
    private RadioButton star3;
    @FXML
    private RadioButton star2;
    @FXML
    private RadioButton star1;
    @FXML
    private Label rating_success_label;

    public void switchtoUserScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("selectuser.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private TableView<Statistics_Table> destinationstatTableView;
    @FXML
    private TableColumn<Statistics_Table, String> destinationNameTableColumn;
    @FXML
    private TableColumn<Statistics_Table, Integer> destinationCountTableColumn;

    ObservableList<Statistics_Table> statisticsDestinationTablesObservableList = FXCollections.observableArrayList();

    public void initialize() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        try {
            Statement statement = connectDB.createStatement();

            ResultSet destinationResult = statement.executeQuery(
                    "SELECT Destination, COUNT(*) AS booking_count " +
                            "FROM bookplango.tourdetails " +
                            "WHERE Destination IN ('Dhaka', 'Mymensingh', 'Barishal', 'Khulna', 'Chattogram', 'Rajshahi', 'Sylhet', 'Rangpur') " +
                            "GROUP BY Destination WITH ROLLUP;"
            );

            statisticsDestinationTablesObservableList.clear(); // Clear previous data

            while (destinationResult.next()) {
                String queryDestination = destinationResult.getString("Destination");
                Integer queryCount = destinationResult.getInt("booking_count");

                // Handle Grand Total (Destination = NULL)
                if (queryDestination == null) {
                    queryDestination = "Grand Total";
                }

                statisticsDestinationTablesObservableList.add(new Statistics_Table(queryDestination, queryCount));
            }
            destinationResult.close();

            // Link columns to data fields
            destinationNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
            destinationCountTableColumn.setCellValueFactory(new PropertyValueFactory<>("count"));

            // Set items into TableView
            destinationstatTableView.setItems(statisticsDestinationTablesObservableList);

        } catch (SQLException e) {
            Logger.getLogger(More_Statistics.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }

    @FXML
    private void submitRating(ActionEvent event) {
        int rating = 0;

        if (star5.isSelected()) {
            rating = 5;
        } else if (star4.isSelected()) {
            rating = 4;
        } else if (star3.isSelected()) {
            rating = 3;
        } else if (star2.isSelected()) {
            rating = 2;
        } else if (star1.isSelected()) {
            rating = 1;
        }

        if (rating != 0) {
            try {
                DatabaseConnection connectNow = new DatabaseConnection();
                Connection connectDB = connectNow.getConnection();
                Statement statement = connectDB.createStatement();
                String insertRating = "INSERT INTO bookplango.ratings (rating_value) VALUES (" + rating + ")";
                statement.executeUpdate(insertRating);

                System.out.println("Rating submitted successfully: " + rating + " star(s)");
                rating_success_label.setText("Thank you for your feedback!");
            } catch (SQLException e) {
                Logger.getLogger(More_Statistics.class.getName()).log(Level.SEVERE, null, e);
                e.printStackTrace();
            }
        } else {
            System.out.println("Please select a rating before submitting.");
        }
    }

}
