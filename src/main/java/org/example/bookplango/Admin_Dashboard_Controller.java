package org.example.bookplango;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Admin_Dashboard_Controller {

    private Stage stage;
    private Scene scene;
    @FXML
    private Label total_user,total_service_provider;

    public void switchtoAdminSigninScene(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("admin_login.fxml"));
        Parent root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void switchtoAdminproviderScene(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("admin_provider_dashboard.fxml"));
        Parent root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void switchtoAdminMessagescene(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("admin_message.fxml"));
        Parent root = fxmlLoader.load();
        Admin_Message_Controller us = fxmlLoader.getController();
        us.initialize();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void switchtoAdminAddScene(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("admin_ADD_place.fxml"));
        Parent root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void switchtoAdmintravelerScene(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("admin_traveler_dashboard.fxml"));
        Parent root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void switchtoAdminApprovalScene(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("admin_approval.fxml"));
        Parent root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    /*public void switchtoAdminSigninScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin_login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }*/
    /*public void switchtoAdmintravelerScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin_traveler_dashboard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }*/
/*
    public void switchtoAdminAddScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin_ADD_place.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }*/
    /*public void switchtoAdminproviderScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin_provider_dashboard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }*/
    /*public void switchtoAdminMessagescene(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("admin_message.fxml"));
        Parent root = fxmlLoader.load();
        Admin_Message_Controller adc = fxmlLoader.getController();
        adc.initialize();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }*/
    /*public void switchtoAdminMessagescene(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("admin_message.fxml"));
        Parent root = fxmlLoader.load();
        Admin_Message_Controller us = fxmlLoader.getController();
        us.initialize();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }*/
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
            //total_service_provider.setText(String.valueOf(totalServiceProviders));
            total = totalUsers + totalServiceProviders;

        } catch (SQLException e) {
            Logger.getLogger(Admin_Dashboard_Controller.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        total_user.setText(String.valueOf(total));
    }

}
