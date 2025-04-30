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

public class Admin_Approval_Controller {
    private Stage stage;
    private Scene scene;
    @FXML
    private Label approval_label;


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

    public void initialize() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        admin_provider_DashboardObservableList.clear();

        String adminproviderTableViewquery = "Select service_id,service_name,service_phone_no,service_type from serviceprovider_info where service_approval = 'No'";
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

    public void Approve(ActionEvent event)
    {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        ObservableList<Admin_provider_Dashboard> place_list;
        place_list = adminproviderrDashboardTableView.getSelectionModel().getSelectedItems();
        String query_name = "UPDATE `bookplango`.`serviceprovider_info`\n" +
                "SET\n" +
                "`service_approval` = 'Approved'\n" +
                "WHERE `service_id` = '"+place_list.get(0).getService_id()+"';";

        try{
            Statement statement2 = connectDB.createStatement();
            int res = statement2.executeUpdate(query_name);

            if(res>0) {
                System.out.println("Approved");
                approval_label.setText("Approved");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
