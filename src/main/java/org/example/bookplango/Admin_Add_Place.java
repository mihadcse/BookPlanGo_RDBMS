package org.example.bookplango;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.stage.FileChooser;
import java.io.File;

public class Admin_Add_Place {
    private Stage stage;
    private Scene scene;

    @FXML
    private Label success_label;
    @FXML
    private TextField place_name_text, place_district_text, place_division_text, place_image_text;

    // Switch to Admin Dashboard
    public void switchtoAdminDashboardScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin_welcome.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    // Select image file
    @FXML
    private void selectImageFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            place_image_text.setText(selectedFile.getAbsolutePath());
        }
    }

    // Add place to database
    public void addPlace() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        PreparedStatement pstmt = connectDB.prepareStatement("INSERT INTO `bookplango`.`travel_places` (" +
                "`place_name`, `place_district`, `place_division`, `place_image_path`) VALUES (?, ?, ?, ?);");

        pstmt.setString(1, place_name_text.getText());
        pstmt.setString(2, place_district_text.getText());
        pstmt.setString(3, place_division_text.getText());
        pstmt.setString(4, place_image_text.getText());

        pstmt.executeUpdate();
        System.out.println("Record inserted successfully");
        success_label.setText("Successfully Added");
    }
}

