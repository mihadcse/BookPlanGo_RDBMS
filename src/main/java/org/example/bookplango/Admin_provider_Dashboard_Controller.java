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

public class Admin_provider_Dashboard_Controller {
    private Stage stage;
    private Scene scene;
    @FXML
    private Label total_provider;


    @FXML
    private TableView<Admin_provider_Dashboard> adminproviderrDashboardTableView;
    @FXML
    private TableColumn<Admin_provider_Dashboard,Integer> adminproviderSIDTableColumn;
    @FXML
    private TableColumn<Admin_provider_Dashboard,String>adminproviderServiceNameTableColumn;
    @FXML
    private TableColumn<Admin_provider_Dashboard,Integer>adminproviderServicePhoneTableColumn;
    @FXML
    private TableColumn<Admin_provider_Dashboard,String>adminproviderServiceTypeTableColumn;


    ObservableList<Admin_provider_Dashboard> admin_provider_DashboardObservableList = FXCollections.observableArrayList();


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
    /*public void switchtoAdminproviderScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin_provider_dashboard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }*/
    /*public void switchtoAdminAddScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin_ADD_place.fxml"));
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
            ResultSet userResult = statement.executeQuery("SELECT COUNT(*) FROM serviceprovider_info");
            if (userResult.next()) {
                totaltraveler = userResult.getInt(1);
            }
            total_provider.setText(String.valueOf(totaltraveler));
        }
        catch (SQLException e) {
            Logger.getLogger(Traveler_Dashboard_Controller.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

        String adminproviderTableViewquery = "Select service_id,service_name,service_phone_no,service_type from serviceprovider_info where service_approval = 'Approved'";
        try{
            Statement statement2 = connectDB.createStatement();
            ResultSet queryOutput2 = statement2.executeQuery (adminproviderTableViewquery);
            while (queryOutput2.next()) {
                Integer querySID = queryOutput2.getInt("service_id");
                String queryServicename = queryOutput2.getString( "service_name");
                Integer queryPhone = queryOutput2.getInt("service_phone_no");
                String queryType = queryOutput2.getString( "service_type");

                admin_provider_DashboardObservableList.add(new Admin_provider_Dashboard (querySID, queryServicename,queryPhone,queryType));
            }

            adminproviderSIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("service_id"));
            adminproviderServiceNameTableColumn.setCellValueFactory (new PropertyValueFactory<>("service_name"));
            adminproviderServicePhoneTableColumn.setCellValueFactory(new PropertyValueFactory<>("Service_phone"));
            adminproviderServiceTypeTableColumn.setCellValueFactory(new PropertyValueFactory<>("service_type"));

            adminproviderrDashboardTableView.setItems (admin_provider_DashboardObservableList);
        }
        catch(SQLException e) {
            Logger.getLogger (Traveler_Dashboard_Controller.class.getName()).log (Level. SEVERE,null, e);
            e.printStackTrace();
        }

    }
}
