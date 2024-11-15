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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class CarManagerSeeBooking {
    private Stage stage;
    private Scene scene;
    @FXML
    private Label ID;
    @FXML
    private TableView<SeeBookingDetailsCar> Table;
    @FXML
    private TableColumn<String,SeeBookingDetailsCar>CarLicsence;
    @FXML
    private TableColumn<String,SeeBookingDetailsCar>BookingDate;
    @FXML
    private TableColumn<String,SeeBookingDetailsCar>Username;
    @FXML
    private TableColumn<String,SeeBookingDetailsCar>UserContact;
    String S_ID;
    int total;
    SeeBookingDetailsCar[] seeBookingDetailsCars;
    ObservableList<SeeBookingDetailsCar> observableList= FXCollections.observableArrayList();
    @FXML
    public void setData(String s) throws SQLException {
        S_ID=s;
        ID.setText(s);
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        Statement statement0=connectDB.createStatement();
        ResultSet resultSet0=statement0.executeQuery("Select count(*) as total from carbookdetails where CarID="+s+";");
        while(resultSet0.next()){
            total=resultSet0.getInt("total");
        }
        Statement statement=connectDB.createStatement();
        ResultSet resultset=statement.executeQuery("Select a.CarLicsence as Licsence,a.BookingDate as Bookingdate,a.Username as Username,b.Contact as Contact from carbookdetails a Join userinfo b ON a.Username=b.Username where a.CarId="+s+";");
        seeBookingDetailsCars=new SeeBookingDetailsCar[total];
        int i=0;
        while(resultset.next()){
            Date date=resultset.getDate("Bookingdate");
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            String d=format.format(date);
            seeBookingDetailsCars[i]=new SeeBookingDetailsCar(resultset.getString("Licsence"), d,resultset.getString("Username"),resultset.getString("Contact"));
            observableList.add(seeBookingDetailsCars[i]);
            i++;
        }CarLicsence.setCellValueFactory (new PropertyValueFactory<>("Licsence"));
        BookingDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        Username.setCellValueFactory (new PropertyValueFactory<>("Username"));
        UserContact.setCellValueFactory (new PropertyValueFactory<>("UserContact"));
        Table.setItems(observableList);
    }
    @FXML
    public void switchtoserviceSigninScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("service_signin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    @FXML
    public void switchtoserviceUpdateProfile(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader=new FXMLLoader(BookPlanGo_Main.class.getResource("service_edit_profileCar.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("service_edit_profile.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setResizable(false);
        ServiceEditProfileCar serviceEditProfileCar=fxmlLoader.getController();
        serviceEditProfileCar.setdata(S_ID);
        stage.show();
    }
    @FXML
    public void AddVehicle(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader=new FXMLLoader(BookPlanGo_Main.class.getResource("carAddVehicle.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("service_edit_profile.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setResizable(false);
        CarAddVehicle carAddVehicle=fxmlLoader.getController();
        carAddVehicle.setData(S_ID);
        stage.show();
    }
    @FXML
    public void carDashboard(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader=new FXMLLoader(BookPlanGo_Main.class.getResource("car_dashboard.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("service_edit_profile.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setResizable(false);
        CarDashboard carDashboard=fxmlLoader.getController();
        carDashboard.setData(S_ID);
        stage.show();
    }
    @FXML
    public void back(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("car_welcome.fxml"));
        Parent root1 = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root1);
        stage.setScene(scene);
        stage.setResizable(false);
        CarWelcomeController carWelcomeController=fxmlLoader.getController();
        carWelcomeController.setData(S_ID);
        stage.show();
    }
}
