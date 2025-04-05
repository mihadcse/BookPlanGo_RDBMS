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

public class Admin_traveler_Dashboard_Controller {
    private Stage stage;
    private Scene scene;
    @FXML
    private Label total_traveler;

    @FXML
    private TableView<Admin_traveler_Dashboard> admintravelerDashboardTableView;
    @FXML
    private TableColumn<Admin_traveler_Dashboard,Integer> admintravelerNIDTableColumn;
    @FXML
    private TableColumn<Admin_traveler_Dashboard,String>admintravelerUsernameTableColumn;
    @FXML
    private TableColumn<Admin_traveler_Dashboard,String>admintravelerContactTableColumn;


    ObservableList<Admin_traveler_Dashboard> admin_traveler_DashboardObservableList = FXCollections.observableArrayList();

    /*public void switchtoAdminSigninScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin_login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void switchtoAdmintravelerScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin_traveler_dashboard.fxml"));
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
    public void switchtoAdminAddScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin_ADD_place.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void switchtoAdminproviderScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin_provider_dashboard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }*/
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
        Admin_Approval_Controller ap = fxmlLoader.getController();
        ap.initialize();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void initialize() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        int totaltraveler = 0;

        try {
            Statement statement = connectDB.createStatement();
            ResultSet userResult = statement.executeQuery("SELECT COUNT(*) FROM userinfo");
            if (userResult.next()) {
                totaltraveler = userResult.getInt(1);
            }
            total_traveler.setText(String.valueOf(totaltraveler));
        }
        catch (SQLException e) {
            Logger.getLogger(Traveler_Dashboard_Controller.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

        String admintravelerTableViewquery = "Select NID,Username,Contact from userinfo";
        try{
            Statement statement2 = connectDB.createStatement();
            ResultSet queryOutput2 = statement2.executeQuery (admintravelerTableViewquery);
            while (queryOutput2.next()) {
                Integer queryNID = queryOutput2.getInt("NID");
                String queryUsername = queryOutput2.getString( "Username");
                String queryContact = queryOutput2.getString("Contact");

                admin_traveler_DashboardObservableList.add(new Admin_traveler_Dashboard (queryNID, queryUsername,queryContact));
            }

            admintravelerNIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("NID"));
            admintravelerUsernameTableColumn.setCellValueFactory (new PropertyValueFactory<>("Username"));
            admintravelerContactTableColumn.setCellValueFactory (new PropertyValueFactory<>("Contact"));

            admintravelerDashboardTableView.setItems (admin_traveler_DashboardObservableList);
        }
        catch(SQLException e) {
            Logger.getLogger (Traveler_Dashboard_Controller.class.getName()).log (Level. SEVERE,null, e);
            e.printStackTrace();
        }

    }
}
