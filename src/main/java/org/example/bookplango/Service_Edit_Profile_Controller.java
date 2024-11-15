package org.example.bookplango;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.sql.*;


public class Service_Edit_Profile_Controller  {
    @FXML
    private Button Proceed;
    @FXML
    private Button Confirm;
    @FXML
    private Button Cancel;
    @FXML
    private TextField service_name,image_path;
    @FXML
    private TextField PhoneNumber;
    @FXML
    private TextField Floor;
    @FXML
    private TextField Password;
    @FXML
    private Label password;
    @FXML
    private Label serviceName;
    @FXML
    private Label floor;
    @FXML
    private Label phnnum;
    @FXML
    private Label notification;
    @FXML
    private Label service_id;
    String S_ID,current_s_name,current_phone,entered_s_name,entered_phone;
    int curren_floor,entered_floor;
    private Stage stage;
    private Scene scene;

    @FXML
    public void setdata(String s) throws SQLException {
        service_id.setText(s);
        phnnum.setVisible(true);
        password.setVisible(true);
        serviceName.setVisible(true);
        Password.setVisible(true);
        PhoneNumber.setVisible(true);
        service_name.setVisible(true);
        Proceed.setVisible(true);
        floor.setVisible(true);
        Floor.setVisible(true);
        password.setVisible(false);
        Password.setVisible(false);
        Confirm.setVisible(false);
        Cancel.setVisible(false);
        S_ID=s;
        DatabaseConnection connectNew = new DatabaseConnection();
        Connection connectDB = connectNew.getConnection();
        try{
            Statement statement = connectDB.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from serviceprovider_info where service_id="+S_ID+" and service_type='Hotel'");
            while(resultSet.next()){
                PhoneNumber.setText(resultSet.getString("service_phone_no"));
                service_name.setText(resultSet.getString("service_name"));
                Floor.setText(String.valueOf(resultSet.getInt("Floor")));
                curren_floor=resultSet.getInt("Floor");
                current_phone=resultSet.getString("service_phone_no");
                current_s_name=resultSet.getString("service_name");
            }
        } catch (Exception e) {
            System.out.println("Error");
        }

    }@FXML
    protected void setProceed(){
        if(service_name.getText().equals(current_s_name) && Floor.getText().equals(String.valueOf(curren_floor)) && PhoneNumber.getText().equals(current_phone)){
            notification.setText("No Change Made");
        }else {
            Password.setText("");
            Floor.setVisible(false);
            phnnum.setVisible(false);
            password.setVisible(false);
            serviceName.setVisible(false);
            Password.setVisible(false);
            PhoneNumber.setVisible(false);
            service_name.setVisible(false);
            Proceed.setVisible(false);
            floor.setVisible(false);
            password.setVisible(true);
            Password.setVisible(true);
            Confirm.setVisible(true);
            Cancel.setVisible(true);
            notification.setText("Enter Password to Confirm");
            entered_floor=Integer.parseInt(Floor.getText());
            entered_s_name=service_name.getText();
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
                ResultSet resultSet= statement.executeQuery("Select service_password from serviceprovider_info where service_id="+S_ID+"");
                while(resultSet.next()){
                    p=resultSet.getString("service_password");
                }if(!p.equals(enteredPassword)){
                    notification.setText("Invalid password");
                }else{
                    Statement statement1=connectDB.createStatement();
                    statement1.executeUpdate("UPDATE serviceprovider_info SET service_name = '"+entered_s_name+"', service_phone_no ='"+entered_phone+"' , Floor ="+entered_floor+" WHERE service_id = "+S_ID+" and service_type='Hotel'; ");
                    setdata(S_ID);
                    notification.setText("Updated");
                }
            } catch (SQLException e) {
                System.out.println("Error");
            }
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
    public void switchtoHoteldashboardScene(ActionEvent event) throws IOException, SQLException {
        //Parent root = FXMLLoader.load(getClass().getResource("user_dashboard.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Hotel_dashboard.fxml"));
        Parent root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        Hotel_Dashboard_Controller hotel_name = fxmlLoader.getController();
        hotel_name.setWelcome(S_ID);
        stage.show();
    }
    @FXML
    public void switchtoAddRoomScene(ActionEvent event) throws IOException, SQLException {
        //Parent root = FXMLLoader.load(getClass().getResource("user_dashboard.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hotel_room-add_controller.fxml"));
        Parent root = fxmlLoader.load();
        Hotel_room_add_controller hotelRoomAddController = fxmlLoader.getController();
        hotelRoomAddController.setdata(S_ID);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    public void switchtoHotelmessageScene(ActionEvent event) throws IOException, SQLException {
        //Parent root = FXMLLoader.load(getClass().getResource("user_dashboard.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("service_message.fxml"));
        Parent root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        Service_Message_Controller hotel_name = fxmlLoader.getController();
        hotel_name.setWelcome(S_ID);
        hotel_name.initialize();
        stage.show();
    }
    @FXML
    public void back(ActionEvent event) throws SQLException, IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Hotel_welcomepage.fxml"));
        Parent root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        HotelWelcomeDashboard hotelWelcomeDashboard = fxmlLoader.getController();
        hotelWelcomeDashboard.setWelcome(S_ID);
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

    /*public void add_image_path_click(ActionEvent event) throws SQLException {
        DatabaseConnection connectNew = new DatabaseConnection();
        Connection connectDB = connectNew.getConnection();
        Statement statement1=connectDB.createStatement();

        String update_image = "UPDATE `sys`.`serviceprovider_info`\n" +
                "SET\n" +
                "`service_image` = ?\n" +
                "WHERE `service_id` = ?";

        PreparedStatement pstmt = connectDB.prepareStatement(update_image);
        // Setting values using pstmt.setString()
        pstmt.setString(1, image_path.getText());
        pstmt.setString(2, S_ID);
        Integer res = statement1.executeUpdate(update_image);
        if(res>0)
        {
            System.out.println("Image Added");
            notification.setText("Image Added");
        }
    }*/
    public void add_image_path_click() throws SQLException {
        // Database connection similar to addPlace()
        DatabaseConnection connectNew = new DatabaseConnection();
        Connection connectDB = connectNew.getConnection();

        // PreparedStatement for update with placeholders
        String update_image = "UPDATE `bookplango`.`serviceprovider_info`\n" +
                "SET\n" +
                "`service_image` = ?\n" +
                "WHERE `service_id` = ?";

        PreparedStatement pstmt = connectDB.prepareStatement(update_image);

        // Setting values using pstmt.setString()
        pstmt.setString(1, image_path.getText());
        pstmt.setString(2, S_ID);

        // Executing the update
        Integer res = pstmt.executeUpdate();
        if (res > 0) {
            System.out.println("Image Added");
            notification.setText("Image Added");
        }

        // Close the connection (recommended practice)
        pstmt.close();
        connectDB.close();
    }

}
