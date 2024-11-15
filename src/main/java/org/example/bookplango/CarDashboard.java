package org.example.bookplango;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class CarDashboard {
    @FXML
    private Label ID;
    @FXML
    private Label message;
    @FXML
    private Label carLicsenceLabel;
    @FXML
    private TableView<carData> carTableView;
    @FXML
    private TableColumn<carData, Button> Select;
    @FXML
    private TableColumn<carData,Integer> carPriceTableColumn;
    @FXML
    private TableColumn<carData,Integer> carLicsenceTableColumn;
    @FXML
    private TableColumn<carData,String>carTypeTableColumn;
    @FXML
    private TableColumn<carData,String>carACTableColumn;
    @FXML
    private TableColumn<carData,String>carStatusTableColumn;
    @FXML
    private Button MakeUnavailable;
    carData[] cardata;
    String[] clicsence;
    Button[] buttons;
    String[] carAvailability;
    ObservableList<carData> carDataObservableList = FXCollections.observableArrayList();
    String S_ID,carL;
    int total;
    private Stage stage;
    private Scene scene;

    @FXML
    private void setButtonAction(ActionEvent e){
        for(int i=0;i<total;i++){
            if(e.getSource()==buttons[i]){
                carLicsenceLabel.setText(clicsence[i]);
                carL=clicsence[i];
                System.out.println(carL);
                if(carAvailability[i].equals("Available")){
                    MakeUnavailable.setText("Make Unavailable");
                    MakeUnavailable.setVisible(true);
                    MakeUnavailable.setDisable(false);
                }else if(carAvailability[i].equals("Unavailable")){
                    MakeUnavailable.setText("Make Available");
                    MakeUnavailable.setDisable(false);
                    MakeUnavailable.setVisible(true);
                }else if(carAvailability[i].equals("Booked")){
                    MakeUnavailable.setDisable(true);
                }
                break;
            }
        }
    }
    @FXML
    public void setAvailable() throws SQLException {
        String status;
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        if(MakeUnavailable.getText().equals("Make Available")){
            status="Available";
        }else{
            status="Unavailable";
        }Statement statement=connectDB.createStatement();
        statement.executeUpdate("UPDATE car_details SET carStatus = '"+status+"' WHERE CarID="+Integer.parseInt(S_ID)+" AND LiscenceNum = '"+carL+"'");
        setData(S_ID);
    }
    @FXML
    public void setData(String s){
        S_ID=s;
        ID.setText(s);
        MakeUnavailable.setVisible(false);
        MakeUnavailable.setDisable(false);
        carLicsenceLabel.setText("");
        carDataObservableList.clear();
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String countVehicle = "Select count(*) as total from car_details where CarID ="+Integer.parseInt(s)+";";

        String carTableViewquery = "Select LiscenceNum,carStatus,VehicleType,AC,price from car_details where CarID ="+Integer.parseInt(s)+";";
        try{
            Statement statement = connectDB.createStatement();
            Statement statement1 = connectDB.createStatement();
            ResultSet resultSet = statement.executeQuery (countVehicle);
            while (resultSet.next()) {
                total=resultSet.getInt("total");
            }int i=0;
            cardata=new carData[total];
            buttons=new Button[total];
            clicsence=new String[total];
            carAvailability=new String[total];
            ResultSet resultSet1 = statement1.executeQuery (carTableViewquery );
            while(resultSet1.next()){
                carAvailability[i]=resultSet1.getString("carStatus");
                cardata[i]=new carData(resultSet1.getString("LiscenceNum"),resultSet1.getInt("price"),resultSet1.getString("VehicleType"),resultSet1.getString("AC"),resultSet1.getString("carStatus"));
                carDataObservableList.add(cardata[i]);
                i++;
            }
            for(int j=0;j<i;j++){
                clicsence[j]=cardata[j].getLicsence();
                buttons[j]=cardata[j].getSelect();
                buttons[j].setOnAction(this::setButtonAction);
            }
            carLicsenceTableColumn.setCellValueFactory (new PropertyValueFactory<>("Licsence"));
            carTypeTableColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
            carACTableColumn.setCellValueFactory (new PropertyValueFactory<>("Ac_non_ac"));
            carStatusTableColumn.setCellValueFactory (new PropertyValueFactory<>("Status"));
            carPriceTableColumn.setCellValueFactory (new PropertyValueFactory<>("Price"));
            Select.setCellValueFactory (new PropertyValueFactory<>("Select"));
            carTableView.setItems (carDataObservableList);
            for(int j=0;j<i;j++){
                System.out.println(carAvailability[j]);
            }

        }
        catch(SQLException e) {
            Logger.getLogger (Hotel_Dashboard_Controller.class.getName()).log (Level. SEVERE,null, e);
            e.printStackTrace();
        }

    }
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
    @FXML
    public void Booking(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader=new FXMLLoader(BookPlanGo_Main.class.getResource("car_manager_see_booking.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("service_edit_profile.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setResizable(false);
        CarManagerSeeBooking carManagerSeeBooking=fxmlLoader.getController();
        carManagerSeeBooking.setData(S_ID);
        stage.show();
    }

}
