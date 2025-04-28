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
import java.util.logging.Level;
import java.util.logging.Logger;

public class Traveler_Booking_Controller {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public Label username_label_dashboard;
    @FXML
    TextField keywordTextField;
    String s = "";


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

    public void switchtouserdashboardScene(ActionEvent event) throws IOException {
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

    public void switchtouserBookingScene(ActionEvent event) throws IOException, SQLException, InterruptedException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user_Booking.fxml"));
        Parent root = fxmlLoader.load();
        UserWelcomeDashboard us = fxmlLoader.getController();
        us.setWelcome(s);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
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

    @FXML
    private TableView<Traveler_Booking> travelerBookingTableView;
    @FXML
    private TableColumn<Traveler_Booking,String>travelerhotelNameTableColumn;
    @FXML
    private TableColumn<Traveler_Booking,String>travelerhotelAddressTableColumn;
    @FXML
    private TableColumn<Traveler_Booking,String>travelerhotelContactTableColumn;
    @FXML
    private TableColumn<Traveler_Booking,String> travelerhotelRatingTableColumn;

    ObservableList<Traveler_Booking> traveler_bookingObservableList = FXCollections.observableArrayList();

    public void initialize_hotel () {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String travelerTableViewquery = "select s.service_name as name,s.service_location as location,s.service_phone_no as phone,sum(h.Rating) as rating, sum(h.RatingNum) as ratingNum from h_roomdetails h inner join serviceprovider_info s on h.Hotel_ID=s.service_id where s.service_approval='Approved' group by s.service_id;";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery (travelerTableViewquery);
            while (queryOutput.next()) {
                String name = queryOutput.getString( "name");
                String address = queryOutput.getString("location");
                String contact = queryOutput.getString("phone");
                String rating;
                Float trn=queryOutput.getFloat("ratingNum");
                int tr= queryOutput.getInt("rating");
                if(trn==0){
                    rating="N/A";
                }else{
                    rating=String.format("%.2f",tr/trn );
                }
                traveler_bookingObservableList.add(new Traveler_Booking (name, address, contact,rating));
            }

            travelerhotelNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
            travelerhotelAddressTableColumn.setCellValueFactory (new PropertyValueFactory<>("Address"));
            travelerhotelContactTableColumn.setCellValueFactory (new PropertyValueFactory<>("Contact"));
            travelerhotelRatingTableColumn.setCellValueFactory (new PropertyValueFactory<>("Rating"));

            travelerBookingTableView.setItems (traveler_bookingObservableList);

            //Initial filtered list
            FilteredList<Traveler_Booking> filteredData = new FilteredList<>(traveler_bookingObservableList, b -> true);
            keywordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate (Traveler_Booking -> {
                    // If no search value then display all records or whatever records it current have. no changes.
                    if (newValue.isEmpty() || newValue.isBlank () || newValue == null) {
                        return true;
                    }
                    String search_Keyword = newValue.toLowerCase();
                    if (Traveler_Booking.getName().toLowerCase().indexOf(search_Keyword) > -1) {
                        return true; // Means we found a match in ProductName
                    }
                    else if (Traveler_Booking.getAddress().toLowerCase().indexOf(search_Keyword) > -1) {
                        return true;
                    } else
                        return false;
                });
            });

            SortedList<Traveler_Booking> sortedData = new SortedList<>(filteredData);
            // Bind sorted result with Table View
            sortedData.comparatorProperty().bind(travelerBookingTableView.comparatorProperty());
            // Apply filtered and sorted data to the Table View
            travelerBookingTableView.setItems(sortedData);
        }
        catch(SQLException e) {
            Logger.getLogger (Traveler_Booking_Controller.class.getName()).log (Level. SEVERE,null, e);
            e.printStackTrace();
        }
    }

    /*public void initialize_car () {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String travelerTableViewquery = "Select serviceprovider_info.service_name,serviceprovider_info.service_location,serviceprovider_info.service_phone_no from serviceprovider_info where service_type = 'Car' and service_type = 'Approved'";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery (travelerTableViewquery);
            while (queryOutput.next()) {
                String queryName = queryOutput.getString( "service_name");
                String queryAddress = queryOutput.getString("service_location");
                String queryContact = queryOutput.getString("service_phone_no");
                traveler_bookingObservableList.add(new Traveler_Booking (queryName, queryAddress, queryContact));
            }

            travelerhotelNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
            travelerhotelAddressTableColumn.setCellValueFactory (new PropertyValueFactory<>("Address"));
            travelerhotelContactTableColumn.setCellValueFactory (new PropertyValueFactory<>("Contact"));

            travelerBookingTableView.setItems (traveler_bookingObservableList);

            //Initial filtered list
            FilteredList<Traveler_Booking> filteredData = new FilteredList<>(traveler_bookingObservableList, b -> true);
            keywordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate (Traveler_Booking -> {
                    // If no search value then display all records or whatever records it current have. no changes.
                    if (newValue.isEmpty() || newValue.isBlank () || newValue == null) {
                        return true;
                    }
                    String search_Keyword = newValue.toLowerCase();
                    if (Traveler_Booking.getName().toLowerCase().indexOf(search_Keyword) > -1) {
                        return true; // Means we found a match in ProductName
                    }
                    else if (Traveler_Booking.getAddress().toLowerCase().indexOf(search_Keyword) > -1) {
                        return true;
                    } else
                        return false;
                });
            });

            SortedList<Traveler_Booking> sortedData = new SortedList<>(filteredData);
            // Bind sorted result with Table View
            sortedData.comparatorProperty().bind(travelerBookingTableView.comparatorProperty());
            // Apply filtered and sorted data to the Table View
            travelerBookingTableView.setItems(sortedData);
        }
        catch(SQLException e) {
            Logger.getLogger (Traveler_Booking_Controller.class.getName()).log (Level. SEVERE,null, e);
            e.printStackTrace();
        }
    }*/

    public void Selection_hotel(ActionEvent event) throws IOException, SQLException {
        ObservableList<Traveler_Booking> hotel_list;
        hotel_list = travelerBookingTableView.getSelectionModel().getSelectedItems();
        System.out.println(hotel_list.get(0).getName());

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user_Hotel_details.fxml"));
        Parent root = fxmlLoader.load();
        Hotel_details_Controller room_detail = fxmlLoader.getController();
        room_detail.initialize(hotel_list.get(0).getName(), hotel_list.get(0).getAddress());
        room_detail.set_Welcome(s);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    /*public void Selection_car(ActionEvent event) throws IOException{

        ObservableList<Traveler_Booking> hotel_list;
        hotel_list = travelerBookingTableView.getSelectionModel().getSelectedItems();
        System.out.println (hotel_list.get(0).getName());

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user_Hotel_details.fxml"));
        Parent root = fxmlLoader.load();
        Hotel_details_Controller room_details = fxmlLoader.getController();
        room_details.initialize(hotel_list.get(0).getName(),hotel_list.get(0).getAddress());
        room_details.set_Welcome(s);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }*/

}
