package org.example.bookplango;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Traveler_Planning_Controller {
    private Stage stage;
    private Scene scene;
    UserWelcomeDashboard user_dash;

    String s = "";

    @FXML
    private Label username_label_dashboard;
    @FXML
    TextField keywordTextField;

    @FXML
    private TableView<Traveler_Planning> travelerPlanningTableView;
    @FXML
    private TableColumn<Traveler_Planning,String> travelerPlaceColumn;
    @FXML
    private TableColumn<Traveler_Planning,String>travelerDistrictTableColumn;
    @FXML
    private TableColumn<Traveler_Planning,String>travelerDivisionTableColumn;

    public void setWelcome(String name)
    {
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

    public void switchtouserdashboardScene(ActionEvent event) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("user_dashboard.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user_dashboard.fxml"));
        Parent root = fxmlLoader.load();
        Traveler_Dashboard_Controller user_name_dashboard_control = fxmlLoader.getController();
        user_name_dashboard_control.setWelcome(s);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        Traveler_Dashboard_Controller traveler_Control = fxmlLoader.getController();
        traveler_Control.initialize();
    }

    public void switchtouserFavScene(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("traveler_fav_place.fxml"));
        Parent root = fxmlLoader.load();
        Traveler_Fav_place us = fxmlLoader.getController();
        us.setWelcome(s);
        us.initialize();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
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

    ObservableList<Traveler_Planning> traveler_planningObservableList = FXCollections.observableArrayList();

    public void initialize () {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        //String query_name = "Select NID from sys.userinfo where Username = '"+username_label_dashboard.getText()+"'";
        String queryOutput = "Select * from bookplango.travel_places";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet res = statement.executeQuery(queryOutput);

            while (res.next()) {
                String queryName = res.getString("place_name");
                String queryDistrict = res.getString("place_district");
                String queryDivision = res.getString("place_division");
                traveler_planningObservableList.add(new Traveler_Planning(queryName, queryDistrict, queryDivision));
            }
            travelerPlaceColumn.setCellValueFactory(new PropertyValueFactory<>("place_name"));
            travelerDistrictTableColumn.setCellValueFactory (new PropertyValueFactory<>("place_district"));
            travelerDivisionTableColumn.setCellValueFactory (new PropertyValueFactory<>("place_division"));

            travelerPlanningTableView.setItems (traveler_planningObservableList);

            //Initial filtered list
            FilteredList<Traveler_Planning> filteredData = new FilteredList<>(traveler_planningObservableList, b -> true);
            keywordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate (Traveler_Planning -> {
                    // If no search value then display all records or whatever records it current have. no changes.
                    if (newValue.isEmpty() || newValue.isBlank () || newValue == null) {
                        return true;
                    }
                    String search_Keyword = newValue.toLowerCase();
                    if (Traveler_Planning.getPlace_name().toLowerCase().indexOf(search_Keyword) > -1) {
                        return true; // Means we found a match in ProductName
                    }
                    else if (Traveler_Planning.getPlace_district().toLowerCase().indexOf(search_Keyword) > -1) {
                        return true;
                    }
                    else if (Traveler_Planning.getPlace_division().toLowerCase().indexOf(search_Keyword) > -1) {
                        return true;
                    }
                    else
                        return false;
                });
            });

            SortedList<Traveler_Planning> sortedData = new SortedList<>(filteredData);
            // Bind sorted result with Table View
            sortedData.comparatorProperty().bind(travelerPlanningTableView.comparatorProperty());
            // Apply filtered and sorted data to the Table View
            travelerPlanningTableView.setItems(sortedData);

        }
        catch(SQLException e) {
            Logger.getLogger (Traveler_Planning_Controller.class.getName()).log (Level. SEVERE,null, e);
            e.printStackTrace();
        }
    }

    public void Selection_place(ActionEvent event) throws IOException, SQLException {
        ObservableList<Traveler_Planning> place_list;
        place_list = travelerPlanningTableView.getSelectionModel().getSelectedItems();
        System.out.println(place_list.get(0).getPlace_name());

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("traveler_place_view.fxml"));
        Parent root = fxmlLoader.load();
        Traveler_Place_View room_detail = fxmlLoader.getController();
        room_detail.initialize(place_list.get(0).getPlace_name(), place_list.get(0).getPlace_district(),place_list.get(0).getPlace_division());
        room_detail.set_Welcome(s);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
