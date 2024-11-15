package org.example.bookplango;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServiceEditProfileCar {
    @FXML
    private Button Proceed;
    @FXML
    private Button Confirm;
    @FXML
    private Button Cancel;
    @FXML
    private TextField service_location;
    @FXML
    private TextField PhoneNumber;
    @FXML
    private TextField Password;
    @FXML
    private Label password;
    @FXML
    private Label serviceLocation;
    @FXML
    private Label phnnum;
    @FXML
    private Label notification;
    @FXML
    private Label service_id;
    String S_ID, current_s_location, current_phone, entered_s_location, entered_phone;
    private Stage stage;
    private Scene scene;

    @FXML
    public void setdata(String s) throws SQLException {
        service_id.setText(s);
        phnnum.setVisible(true);
        password.setVisible(true);
        Password.setVisible(true);
        PhoneNumber.setVisible(true);
        service_location.setVisible(true);
        serviceLocation.setVisible(true);
        Proceed.setVisible(true);
        password.setVisible(false);
        Password.setVisible(false);
        Confirm.setVisible(false);
        Cancel.setVisible(false);
        S_ID = s;
        DatabaseConnection connectNew = new DatabaseConnection();
        Connection connectDB = connectNew.getConnection();
        try {
            Statement statement = connectDB.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from serviceprovider_info where service_id=" + S_ID + " and service_type='Car'");
            while (resultSet.next()) {
                PhoneNumber.setText(resultSet.getString("service_phone_no"));
                service_location.setText(resultSet.getString("service_location"));
                current_phone = resultSet.getString("service_phone_no");
                current_s_location = resultSet.getString("service_location");
            }
        } catch (Exception e) {
            System.out.println("Error");
        }

    }@FXML
    protected void setProceed(){
        if(service_location.getText().equals(current_s_location) && PhoneNumber.getText().equals(current_phone)){
            notification.setText("No Change Made");
        }else {
            Password.setText("");
            phnnum.setVisible(false);
            password.setVisible(false);
            serviceLocation.setVisible(false);
            Password.setVisible(false);
            PhoneNumber.setVisible(false);
            service_location.setVisible(false);
            Proceed.setVisible(false);
            password.setVisible(true);
            Password.setVisible(true);
            Confirm.setVisible(true);
            Cancel.setVisible(true);
            notification.setText("Enter Password to Confirm");
            entered_s_location=service_location.getText();
            entered_phone=PhoneNumber.getText();
        }
    }@FXML
    protected void setCancel() throws SQLException {
        try {
            setdata(S_ID);
        } catch (SQLException e) {
            System.out.println("Error");
        }
    }@FXML
    protected void setConfirm() throws SQLException {
        String p = null;
        if(Password.getText().isBlank()){
            notification.setText("Enter Password to Confirm");
        }else {
            String enteredPassword = hashPassword(Password.getText());
            DatabaseConnection connectNew = new DatabaseConnection();
            Connection connectDB = connectNew.getConnection();
            try{
                Statement statement=connectDB.createStatement();
                ResultSet resultSet= statement.executeQuery("Select service_password from serviceprovider_info where service_id="+S_ID+" and service_type='Car';");
                while(resultSet.next()){
                    p=resultSet.getString("service_password");
                }if(!p.equals(enteredPassword)){
                    notification.setText("Invalid password");
                }else{
                    Statement statement1=connectDB.createStatement();
                    Statement statement2=connectDB.createStatement();
                    statement1.executeUpdate("UPDATE serviceprovider_info SET service_location = '"+entered_s_location+"', service_phone_no ='"+entered_phone+"' WHERE service_id = "+S_ID+" and service_type='Car'; ");
                    statement2.executeUpdate("UPDATE car_details SET Location ='"+entered_s_location+"' WHERE CarID = "+S_ID+";");
                    setdata(S_ID);
                    notification.setText("Updated");
                }
            } catch (SQLException e) {
                System.out.println("Error");
            }
        }
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

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}

