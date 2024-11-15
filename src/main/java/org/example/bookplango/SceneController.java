/*

package org.example.bookplango;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.lang.annotation.Inherited;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField NID_Text,phone_email_Text,service_phone_Text,service_ID_Text,service_name_Text;
    @FXML
    private TextField username_Text;
    @FXML
    private TextField admin_server,admin_user;
    @FXML
    private PasswordField admin_pass;
    @FXML
    private Label userlogin_label,servicelogin_label,username_label,admin_login_label;
    @FXML
    private PasswordField password_text,service_pass_Text;
    @FXML
    private Button login_button;
    @FXML
    private Button signup_button;
    int switch_to_welcome = 0,switch_to_welcome_service = 0;
    int hotel_clicked = 0, car_clicked = 0;
    public String name = "";
    @FXML
    private Label service_name;
    @FXML
    private Label service_hotel_floor;
    @FXML
    private ComboBox<String> serviceLocation;

    @FXML
    private TextField signup_service;
    @FXML
    private TextField signup_text;
    @FXML
    private TextField service_location_text;
    @FXML
    private TextField service_hotel_floor_text;
    int hotel_button_click_signup = 0,car_button_click_signup = 0;
    @FXML
    public void setLoc() {
        serviceLocation.setItems(FXCollections.observableArrayList("Dhaka", "Sylhet", "Rangpur", "Mymensingh", "Chattogram", "Khulna", "Rajshahi", "Barishal"));
    }
    public void hotel_click_signup(ActionEvent event)
    {
        setLoc();
        service_hotel_floor_text.setVisible(true);
        service_hotel_floor.setVisible(true);
        service_name_Text.setVisible(true);
        hotel_button_click_signup = 1;
        service_name.setDisable(false);
        service_name_Text.setDisable(false);
        service_hotel_floor.setDisable(false);
        service_hotel_floor_text.setDisable(false);
        service_name.setText("Hotel Name");
        service_name.setVisible(true);
        car_button_click_signup = 0;
        System.out.println(hotel_clicked+" "+hotel_button_click_signup+" "+car_clicked+" "+car_button_click_signup);
    }
    public void car_click_signup(ActionEvent event)
    {
        setLoc();
        service_hotel_floor_text.setVisible(false);
        service_hotel_floor.setVisible(false);
        service_name_Text.setVisible(false);
        car_button_click_signup = 1;
        hotel_button_click_signup = 0;
        service_name.setVisible(false);
        System.out.println(hotel_clicked+" "+hotel_button_click_signup+" "+car_clicked+" "+car_button_click_signup);
    }
    */
/*@FXML
    private StackPane rootpane;*//*


    */
/*private void makeFadeOut() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(rootpane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
    }*//*

    public void switchtoAdminSigninScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin_login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void switchtoUserScene(ActionEvent event) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(BookPlanGo_Main.class.getResource("selectuser.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("selectuser.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        //Initialize();
    }

    public void switchtouserSigninScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("signin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void switchtohotelwelcomeScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hotel_welcomepage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void switchtoserviceSigninScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("service_signin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void switchtouserSignupScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void switchtoserviceSignupScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("service_signup.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        //service_name.setDisable(true);
        //service_name_Text.setDisable(true);
    }

    public void switchtoHoteldashboardScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Hotel_dashboard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        //username_label.setText(username_Text.getText());
    }
    public void switchtoadminwelcomeScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin_welcome.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        //username_label.setText(username_Text.getText());
    }

    public void select_hotel(ActionEvent event)
    {
        hotel_clicked = 1;
        car_clicked = 0;
    }
    public void select_car(ActionEvent event)
    {
        hotel_clicked = 0;
        car_clicked = 1;
    }

    public void adminloginbuttonclick(ActionEvent event) throws IOException {
        System.out.println(admin_server.getText()+'\n'+admin_user.getText()+'\n'+admin_pass.getText());
        if(admin_server.getText().equals("sys") && admin_user.getText().equals("root") && admin_pass.getText().equals("dBase@BookPlanGo24"))
        {
            switchtoadminwelcomeScene(event);
        }
        else
        {
            admin_login_label.setText("Invalid");
        }
    }
    public void useronloginButtonclick(ActionEvent event) throws IOException {
        if (username_Text.getText().isBlank() == false && password_text.getText().isBlank() == false) {
            uservalidateLogin();
            if(switch_to_welcome == 1) {
                //makeFadeOut();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("welcomeuser.fxml"));
                Parent root1 = fxmlLoader.load();
                UserWelcomeDashboard user_name_dashboard = fxmlLoader.getController();
                user_name_dashboard.setWelcome(username_Text.getText());
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root1);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            }
        } else {
            userlogin_label.setText("Enter username/password");
        }
    }

    public void setWelcome(String name)
    {
        username_label.setText(name);
        System.out.println(name);
    }
    public void useronsignupButtonclick(ActionEvent event) {
        if (username_Text.getText().isBlank() == false && password_text.getText().isBlank() == false && phone_email_Text.getText().isBlank() == false) {
            //userlogin_label.setText("Account Created");
            uservalidateSignup();
        } else {
            userlogin_label.setText("Enter Informations");
        }
    }

    public void uservalidateLogin() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String username = username_Text.getText();
        String password = hashPassword(password_text.getText()); // Hash the provided password

        String verifylogin = "SELECT count(1) FROM userinfo WHERE Username = '" + username + "' AND Password = '" + password + "'";

        // String verifyname = "SELECT Username FROM userinfo WHERE Username = '" + username + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifylogin);
            //Statement st = connectDB.createStatement() ;
            //ResultSet query = st.executeQuery(verifyname);
            //name = query.getString("Username");
            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    switch_to_welcome = 1;
                    */
/*if (query.next()) {
                        name = query.getString("Username");
                    }*//*

                } else {
                    userlogin_label.setText("Invalid Login. Please try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            // Close the database connection
            try {
                connectDB.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Print stack trace for debugging
            }
        }
    }

    */
/*public void uservalidateSignup() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String NID = NID_Text.getText();
        String username = username_Text.getText();
        String password = hashPassword(password_text.getText()); // Hash the password

        String verifysignup = "Insert into userinfo (NID,Username,Password,Contact) values ('"+NID+"','"+username+"','"+password+"','"+phone_email_Text.getText()+"')";
        try {
            Statement statement = connectDB.createStatement();
            int rowsAffected = statement.executeUpdate(verifysignup);
            if (rowsAffected > 0) {
                userlogin_label.setText("Welcome! Account Created");
            } else {
                userlogin_label.setText("Failed to create account. Please check your information.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*//*


    public void uservalidateSignup() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String NID = NID_Text.getText();
        String username = username_Text.getText();
        String password = hashPassword(password_text.getText()); // Hash the password

        String verifysignup = "INSERT INTO userinfo (NID, Username, Password, Contact) " +
                "VALUES (" + Integer.parseInt(NID) + ", '" + username + "', '" + password + "', '" + phone_email_Text.getText() + "')";

        try {
            Statement statement = connectDB.createStatement();
            try {
                int rowsAffected = statement.executeUpdate(verifysignup);
                if (rowsAffected > 0) {
                    userlogin_label.setText("Welcome! Account Created");
                } else {
                    userlogin_label.setText("Failed to create account. Please check your information.");
                }
            } catch (java.sql.SQLIntegrityConstraintViolationException e) {
                userlogin_label.setText("Username or NID already exists. Please choose a different one.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the database connection
            try {
                connectDB.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    public void serviceonloginButtonclick(ActionEvent event) throws IOException, SQLException {
        if (service_ID_Text.getText().isBlank() == false && service_pass_Text.getText().isBlank() == false) {
            String type=servicevalidateLogin();
            if(type.equals("nothing")){
                servicelogin_label.setText("Invalid Login. Please try again.");
            }
            else if(type.equals("Hotel"))
            {
                //switchtohotelwelcomeScene(event);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hotel_welcomepage.fxml"));
                Parent root1 = fxmlLoader.load();
                HotelWelcomeDashboard hotel_name_dashboard = fxmlLoader.getController();
                hotel_name_dashboard.setWelcome(service_ID_Text.getText());
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root1);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            }
            else if(type.equals("Car"))
            {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("car_welcome.fxml"));
                Parent root1 = fxmlLoader.load();
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root1);
                stage.setScene(scene);
                stage.setResizable(false);
                CarWelcomeController carWelcomeController=fxmlLoader.getController();
                carWelcomeController.setData(service_ID_Text.getText());
                stage.show();
            }
        } else {
            servicelogin_label.setText("Enter ID/password");
        }
    }

    public String servicevalidateLogin() throws SQLException {
        int x=-1;
        String type="nothing";
        DatabaseConnection connectNew = new DatabaseConnection();
        Connection connectDB = connectNew.getConnection();

        String ID = service_ID_Text.getText();
        String password = hashPassword(service_pass_Text.getText()); // Hash the provided password

        String verifylogin = "SELECT count(*) as total FROM serviceprovider_info WHERE service_id = "+Integer.parseInt(ID)+ " AND service_password = '" + password + "'";
        Statement statement = connectDB.createStatement();
        ResultSet queryResult = statement.executeQuery(verifylogin);
        while(queryResult.next()){
            x=queryResult.getInt("total");
        }if(x==0){
            return type;
        }Statement statement1 = connectDB.createStatement();
        ResultSet queryResult1 = statement.executeQuery("Select service_type from serviceprovider_info WHERE service_id = "+Integer.parseInt(ID)+ ";");
        while(queryResult1.next()){
            type=queryResult1.getString("service_type");
        }return type;

        */
/*try {
            System.out.println("SQL Query: " + verifylogin); // Print SQL query for debugging
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifylogin);
            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    servicelogin_label.setText("welcome");
                    switch_to_welcome_service = 1;

                } else {
                    servicelogin_label.setText("Invalid Login. Please try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace for debugging
        } finally {
            // Close the database connection
            try {
                connectDB.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Print stack trace for debugging
            }
        }*//*

    }

    public void serviceonsignupButtonclick(ActionEvent event) {
        if(hotel_button_click_signup==0 && car_button_click_signup==0){
            servicelogin_label.setText("Click Button to select type of service");
        }else{
            String loc=serviceLocation.getValue();
            if(service_ID_Text.getText().isBlank()  || service_pass_Text.getText().isBlank()  || service_phone_Text.getText().isBlank() || loc==null){
                servicelogin_label.setText("Enter Informations");
            }else{
                if((hotel_button_click_signup == 1 && !service_name_Text.getText().isBlank() && !service_hotel_floor_text.getText().isBlank()) || car_button_click_signup==1){
                    servicevalidateSignup();
                }else{
                    servicelogin_label.setText("Enter Informations");
                }
            }
        }
        */
/*if(hotel_button_click_signup==0 && car_button_click_signup==0){
            servicelogin_label.setText("Click Button to select type of service");
        }else {
            if (service_ID_Text.getText().isBlank() == false && service_pass_Text.getText().isBlank() == false && service_phone_Text.getText().isBlank() == false && service_location_text.getText().isBlank() == false) {
                //userlogin_label.setText("Account Created");
                if (hotel_button_click_signup == 1) {
                    if (!service_location_text.getText().isBlank()) {
                        servicevalidateSignup();
                    } else {
                        servicelogin_label.setText("Enter Informations");
                    }
                } else if (car_button_click_signup == 1) {
                    servicevalidateSignup();
                }
            } else {
                servicelogin_label.setText("Enter Informations");
            }
        }*//*

    }
    public void servicevalidateSignup() {
        System.out.println(hotel_clicked+" "+hotel_button_click_signup+" "+car_clicked+" "+car_button_click_signup);
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String servicename="null";
        int f=0;
        String S_ID = service_ID_Text.getText();
        if(hotel_button_click_signup==1) {
            servicename = service_name_Text.getText();
            f=Integer.parseInt(service_hotel_floor_text.getText());
        }
        String password = hashPassword(service_pass_Text.getText()); // Hash the password
        String location=serviceLocation.getValue();

        String checkDuplicateID = "SELECT * FROM serviceprovider_info WHERE service_id = " +Integer.parseInt(S_ID)+ "";

        String verifysignup = "Insert into serviceprovider_info (service_id,service_name,service_password,service_phone_no,service_type,service_location,Floor) values ("+Integer.parseInt(S_ID)+",'"+servicename+"','"+password+"','"+service_phone_Text.getText()+"','"+"Hotel"+"','"+location+"',"+f+")";

        String verifysignup2 = "Insert into serviceprovider_info (service_id,service_name,service_password,service_phone_no,service_type,service_location,Floor) values ("+Integer.parseInt(S_ID)+",'"+servicename+"','"+password+"','"+service_phone_Text.getText()+"','"+"Car"+"','"+location+"',"+f+")";

        // Create a dynamic SQL query string to create a table for the service provider
        */
/*String createHotelTable = "CREATE TABLE " + servicename + "_" + S_ID + " (" +
                "Hotel_ID INT PRIMARY KEY NOT NULL, " +
                "Hotel_available_rooms INT NOT NULL, " +
                "Hotel_booked_rooms INT NOT NULL, " +
                "Hotel_address VARCHAR(65) NOT NULL, " +
                "Hotel_city VARCHAR(30) NOT NULL)";
        String createCarTable = "CREATE TABLE " + servicename + "_" + S_ID + " (" +
                "Car_ID INT PRIMARY KEY NOT NULL, " +
                "Car_available INT NOT NULL, " +
                "Car_booked INT NOT NULL, " +
                "Car_address VARCHAR(65) NOT NULL, " +
                "Car_city VARCHAR(30) NOT NULL)";*//*


        try {
            Statement statement = connectDB.createStatement();
            ResultSet checkResult = statement.executeQuery(checkDuplicateID);
            if(!checkResult.next()) {
                if (hotel_button_click_signup == 1) {
                    int rowsAffected = statement.executeUpdate(verifysignup);
                    //int createTableRowsAffected = statement.executeUpdate(createHotelTable);
                    if (rowsAffected > 0) {
                        servicelogin_label.setText("Welcome! Account Created");
                    } else {
                        servicelogin_label.setText("Failed to create account.");
                    }
                }
                if (car_button_click_signup == 1) {
                    int rowsAffected = statement.executeUpdate(verifysignup2);
                    //int createTableRowsAffected = statement.executeUpdate(createCarTable);
                    if (rowsAffected > 0) {
                        servicelogin_label.setText("Welcome! Account Created");
                    } else {
                        servicelogin_label.setText("Failed to create account.");
                    }
                }
            }
            else{
                servicelogin_label.setText("Service ID already exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

*/


package org.example.bookplango;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.lang.annotation.Inherited;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField NID_Text,phone_email_Text,service_phone_Text,service_ID_Text,service_name_Text;
    @FXML
    private TextField username_Text;
    @FXML
    private TextField admin_server,admin_user;
    @FXML
    private PasswordField admin_pass;
    @FXML
    private Label userlogin_label,servicelogin_label,username_label,admin_login_label;
    @FXML
    private PasswordField password_text,service_pass_Text;
    @FXML
    private Button login_button;
    @FXML
    private Button signup_button;
    int switch_to_welcome = 0,switch_to_welcome_service = 0;
    int hotel_clicked = 0, car_clicked = 0;
    public String name = "";
    @FXML
    private Label service_name;
    @FXML
    private Label service_hotel_floor;
    @FXML
    private ComboBox<String> serviceLocation;

    @FXML
    private TextField signup_service;
    @FXML
    private TextField signup_text;
    @FXML
    private TextField service_location_text;
    @FXML
    private TextField service_hotel_floor_text;
    int hotel_button_click_signup = 0,car_button_click_signup = 0;
    @FXML
    private Button SignUpHotel;
    @FXML
    private Button SignUpCar;
    @FXML
    public void setLoc() {
        serviceLocation.setItems(FXCollections.observableArrayList("Dhaka", "Sylhet", "Rangpur", "Mymensingh", "Chattogram", "Khulna", "Rajshahi", "Barishal"));
    }
    public void hotel_click_signup(ActionEvent event)
    {
        SignUpHotel.setOpacity(1.0);
        SignUpCar.setOpacity(0.75);
        setLoc();
        service_hotel_floor_text.setVisible(true);
        service_hotel_floor.setVisible(true);
        service_name_Text.setVisible(true);
        hotel_button_click_signup = 1;
        service_name.setDisable(false);
        service_name_Text.setDisable(false);
        service_hotel_floor.setDisable(false);
        service_hotel_floor_text.setDisable(false);
        service_name.setText("Hotel Name");
        service_name.setVisible(true);
        car_button_click_signup = 0;
        System.out.println(hotel_clicked+" "+hotel_button_click_signup+" "+car_clicked+" "+car_button_click_signup);
    }
    public void car_click_signup(ActionEvent event)
    {
        SignUpCar.setOpacity(1.0);
        SignUpHotel.setOpacity(0.75);
        setLoc();
        service_hotel_floor_text.setVisible(false);
        service_hotel_floor.setVisible(false);
        service_name_Text.setVisible(false);
        car_button_click_signup = 1;
        hotel_button_click_signup = 0;
        service_name.setVisible(false);
        System.out.println(hotel_clicked+" "+hotel_button_click_signup+" "+car_clicked+" "+car_button_click_signup);
    }
    /*@FXML
    private StackPane rootpane;*/

    /*private void makeFadeOut() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(rootpane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
    }*/
    public void switchtoAdminSigninScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin_login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void switchtoUserScene(ActionEvent event) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(BookPlanGo_Main.class.getResource("selectuser.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("selectuser.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        //Initialize();
    }

    public void switchtouserSigninScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("signin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void switchtohotelwelcomeScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hotel_welcomepage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void switchtoserviceSigninScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("service_signin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void switchtouserSignupScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void switchtoserviceSignupScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("service_signup.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        //service_name.setDisable(true);
        //service_name_Text.setDisable(true);
    }

    public void switchtoHoteldashboardScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Hotel_dashboard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        //username_label.setText(username_Text.getText());
    }
    public void switchtoadminwelcomeScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin_welcome.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        //username_label.setText(username_Text.getText());
    }

    public void select_hotel(ActionEvent event)
    {
        hotel_clicked = 1;
        car_clicked = 0;
    }
    public void select_car(ActionEvent event)
    {
        hotel_clicked = 0;
        car_clicked = 1;
    }

    public void adminloginbuttonclick(ActionEvent event) throws IOException {
        System.out.println(admin_server.getText()+'\n'+admin_user.getText()+'\n'+admin_pass.getText());
        if(admin_server.getText().equals("sys") && admin_user.getText().equals("root") && admin_pass.getText().equals("dBase@BookPlanGo24"))
        {
            switchtoadminwelcomeScene(event);
        }
        else
        {
            admin_login_label.setText("Invalid");
        }
    }
    public void useronloginButtonclick(ActionEvent event) throws IOException {
        if (username_Text.getText().isBlank() == false && password_text.getText().isBlank() == false) {
            uservalidateLogin();
            if(switch_to_welcome == 1) {
                //makeFadeOut();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("welcomeuser.fxml"));
                Parent root1 = fxmlLoader.load();
                UserWelcomeDashboard user_name_dashboard = fxmlLoader.getController();
                user_name_dashboard.setWelcome(username_Text.getText());
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root1);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            }
        } else {
            userlogin_label.setText("Enter username/password");
        }
    }

    public void setWelcome(String name)
    {
        username_label.setText(name);
        System.out.println(name);
    }
    String checkMailorPhone(String s){
        int i,l=s.length(),pn=0;
        if(l<9){
            return "invalid";
        }if(l==11){
            for(i=0;i<11;i++) {
                if (!Character.isDigit(s.charAt(i))) {
                    return "email";
                }
            }return "phone";
        }return "email";
    }
    boolean checkPhone(String s){
        String operator[]={"013","014","015","016","017","018","019"};
        for(int i=0;i<operator.length;i++){
            if(s.startsWith(operator[i])){
                return true;
            }
        }return false;
    }
    boolean checkEmail(String s){
        String domain[]={"@gmail.com","@yahoo.com","@hotmail.com","@outlook.com","@msn.com","@aol.com","@iut-dhaka.edu"};
        for(int i=0;i<domain.length;i++){
            if(s.length()>domain[i].length() && s.endsWith(domain[i])){
                return true;
            }
        }return false;
    }
    boolean valid;
    public void useronsignupButtonclick(ActionEvent event) {
        if (username_Text.getText().isBlank() == false && password_text.getText().isBlank() == false && phone_email_Text.getText().isBlank() == false &&
                NID_Text.getText().isBlank()==false) {
            String contact=phone_email_Text.getText();
            String check=checkMailorPhone(contact);
            if(check.equals("phone")){
                valid=checkPhone(contact);
            }else if(check.equals("email")){
                valid=checkEmail(contact);
            }if(!valid || check.equals("invalid")){
                userlogin_label.setText("Invalid contact information");
            }else {
                //userlogin_label.setText("Account Created");
                if (password_text.getText().length() < 4 || username_Text.getText().length() < 4) {
                    userlogin_label.setText("Password and Username should contain atleast 4 words");
                } else {
                    uservalidateSignup();
                }
            }
        } else {
            userlogin_label.setText("Enter Informations");
        }
    }

    public void uservalidateLogin() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String username = username_Text.getText();
        String password = hashPassword(password_text.getText()); // Hash the provided password

        String verifylogin = "SELECT count(1) FROM userinfo WHERE Username = '" + username + "' AND Password = '" + password + "'";

        // String verifyname = "SELECT Username FROM userinfo WHERE Username = '" + username + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifylogin);
            //Statement st = connectDB.createStatement() ;
            //ResultSet query = st.executeQuery(verifyname);
            //name = query.getString("Username");
            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    switch_to_welcome = 1;
                    /*if (query.next()) {
                        name = query.getString("Username");
                    }*/
                } else {
                    userlogin_label.setText("Invalid Login. Please try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            // Close the database connection
            try {
                connectDB.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Print stack trace for debugging
            }
        }
    }

    /*public void uservalidateSignup() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String NID = NID_Text.getText();
        String username = username_Text.getText();
        String password = hashPassword(password_text.getText()); // Hash the password

        String verifysignup = "Insert into userinfo (NID,Username,Password,Contact) values ('"+NID+"','"+username+"','"+password+"','"+phone_email_Text.getText()+"')";
        try {
            Statement statement = connectDB.createStatement();
            int rowsAffected = statement.executeUpdate(verifysignup);
            if (rowsAffected > 0) {
                userlogin_label.setText("Welcome! Account Created");
            } else {
                userlogin_label.setText("Failed to create account. Please check your information.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public void uservalidateSignup() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String NID = NID_Text.getText();
        String username = username_Text.getText();
        String password = hashPassword(password_text.getText()); // Hash the password

        String verifysignup = "INSERT INTO userinfo (NID, Username, Password, Contact) " +
                "VALUES (" + Integer.parseInt(NID) + ", '" + username + "', '" + password + "', '" + phone_email_Text.getText() + "')";

        try {
            Statement statement = connectDB.createStatement();
            try {
                int rowsAffected = statement.executeUpdate(verifysignup);
                if (rowsAffected > 0) {
                    userlogin_label.setText("Welcome! Account Created");
                } else {
                    userlogin_label.setText("Failed to create account. Please check your information.");
                }
            } catch (java.sql.SQLIntegrityConstraintViolationException e) {
                userlogin_label.setText("Username or NID already exists. Please choose a different one.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the database connection
            try {
                connectDB.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    public void serviceonloginButtonclick(ActionEvent event) throws IOException, SQLException {
        if (service_ID_Text.getText().isBlank() == false && service_pass_Text.getText().isBlank() == false) {
            String type=servicevalidateLogin();
            if(type.equals("nothing")){
                servicelogin_label.setText("Invalid Login. Please try again.");
            }
            else if(type.equals("Hotel"))
            {
                //switchtohotelwelcomeScene(event);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hotel_welcomepage.fxml"));
                Parent root1 = fxmlLoader.load();
                HotelWelcomeDashboard hotel_name_dashboard = fxmlLoader.getController();
                hotel_name_dashboard.setWelcome(service_ID_Text.getText());
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root1);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            }
            else if(type.equals("Car"))
            {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("car_welcome.fxml"));
                Parent root1 = fxmlLoader.load();
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root1);
                stage.setScene(scene);
                stage.setResizable(false);
                CarWelcomeController carWelcomeController=fxmlLoader.getController();
                carWelcomeController.setData(service_ID_Text.getText());
                stage.show();
            }
        } else {
            servicelogin_label.setText("Enter ID/password");
        }
    }

    public String servicevalidateLogin() throws SQLException {
        int x=-1;
        String type="nothing";
        DatabaseConnection connectNew = new DatabaseConnection();
        Connection connectDB = connectNew.getConnection();

        String ID = service_ID_Text.getText();
        String password = hashPassword(service_pass_Text.getText()); // Hash the provided password

        String verifylogin = "SELECT count(*) as total FROM serviceprovider_info WHERE service_id = "+Integer.parseInt(ID)+ " AND service_password = '" + password + "'";
        Statement statement = connectDB.createStatement();
        ResultSet queryResult = statement.executeQuery(verifylogin);
        while(queryResult.next()){
            x=queryResult.getInt("total");
        }if(x==0){
            return type;
        }Statement statement1 = connectDB.createStatement();
        ResultSet queryResult1 = statement.executeQuery("Select service_type from serviceprovider_info WHERE service_id = "+Integer.parseInt(ID)+ ";");
        while(queryResult1.next()){
            type=queryResult1.getString("service_type");
        }return type;

        /*try {
            System.out.println("SQL Query: " + verifylogin); // Print SQL query for debugging
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifylogin);
            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    servicelogin_label.setText("welcome");
                    switch_to_welcome_service = 1;

                } else {
                    servicelogin_label.setText("Invalid Login. Please try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace for debugging
        } finally {
            // Close the database connection
            try {
                connectDB.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Print stack trace for debugging
            }
        }*/
    }
    boolean valid1;
    public void serviceonsignupButtonclick(ActionEvent event) {
        if(hotel_button_click_signup==0 && car_button_click_signup==0){
            servicelogin_label.setText("Click Button to select type of service");
        }else{
            String loc=serviceLocation.getValue();
            if(service_ID_Text.getText().isBlank()  || service_pass_Text.getText().isBlank()  || service_phone_Text.getText().isBlank() || loc==null){
                servicelogin_label.setText("Enter Informations");
            }else{
                if((hotel_button_click_signup == 1 && !service_name_Text.getText().isBlank() && !service_hotel_floor_text.getText().isBlank()) || car_button_click_signup==1){
                    String contact= service_phone_Text.getText();
                    String check=checkMailorPhone(contact);
                    if(check.equals("phone")){
                        valid1=checkPhone(contact);
                    }else if(check.equals("email")){
                        valid1=checkEmail(contact);
                    }if(!valid1 || check.equals("invalid")){
                        servicelogin_label.setText("Invalid contact information");
                    }else {
                        //userlogin_label.setText("Account Created");
                        if (service_pass_Text.getText().length() < 4 || service_ID_Text.getText().length() < 4) {
                            servicelogin_label.setText("Password and Service ID should contain atleast 4 words");
                        } else {
                            servicevalidateSignup();
                        }
                    }
                }else{
                    servicelogin_label.setText("Enter Informations");
                }
            }
        }
        /*if(hotel_button_click_signup==0 && car_button_click_signup==0){
            servicelogin_label.setText("Click Button to select type of service");
        }else {
            if (service_ID_Text.getText().isBlank() == false && service_pass_Text.getText().isBlank() == false && service_phone_Text.getText().isBlank() == false && service_location_text.getText().isBlank() == false) {
                //userlogin_label.setText("Account Created");
                if (hotel_button_click_signup == 1) {
                    if (!service_location_text.getText().isBlank()) {
                        servicevalidateSignup();
                    } else {
                        servicelogin_label.setText("Enter Informations");
                    }
                } else if (car_button_click_signup == 1) {
                    servicevalidateSignup();
                }
            } else {
                servicelogin_label.setText("Enter Informations");
            }
        }*/
    }
    public void servicevalidateSignup() {
        System.out.println(hotel_clicked+" "+hotel_button_click_signup+" "+car_clicked+" "+car_button_click_signup);
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String servicename="null";
        int f=0;
        String S_ID = service_ID_Text.getText();
        if(hotel_button_click_signup==1) {
            servicename = service_name_Text.getText();
            f=Integer.parseInt(service_hotel_floor_text.getText());
        }
        String password = hashPassword(service_pass_Text.getText()); // Hash the password
        String location=serviceLocation.getValue();

        String checkDuplicateID = "SELECT * FROM serviceprovider_info WHERE service_id = " +Integer.parseInt(S_ID)+ "";

        String verifysignup = "Insert into serviceprovider_info (service_id,service_name,service_password,service_phone_no,service_type,service_location,Floor,service_approval) values ("+Integer.parseInt(S_ID)+",'"+servicename+"','"+password+"','"+service_phone_Text.getText()+"','"+"Hotel"+"','"+location+"',"+f+",'No')";

        String verifysignup2 = "Insert into serviceprovider_info (service_id,service_name,service_password,service_phone_no,service_type,service_location,Floor,service_approval) values ("+Integer.parseInt(S_ID)+",'"+servicename+"','"+password+"','"+service_phone_Text.getText()+"','"+"Car"+"','"+location+"',"+f+",'No')";

        // Create a dynamic SQL query string to create a table for the service provider
        /*String createHotelTable = "CREATE TABLE " + servicename + "_" + S_ID + " (" +
                "Hotel_ID INT PRIMARY KEY NOT NULL, " +
                "Hotel_available_rooms INT NOT NULL, " +
                "Hotel_booked_rooms INT NOT NULL, " +
                "Hotel_address VARCHAR(65) NOT NULL, " +
                "Hotel_city VARCHAR(30) NOT NULL)";
        String createCarTable = "CREATE TABLE " + servicename + "_" + S_ID + " (" +
                "Car_ID INT PRIMARY KEY NOT NULL, " +
                "Car_available INT NOT NULL, " +
                "Car_booked INT NOT NULL, " +
                "Car_address VARCHAR(65) NOT NULL, " +
                "Car_city VARCHAR(30) NOT NULL)";*/

        try {
            Statement statement = connectDB.createStatement();
            ResultSet checkResult = statement.executeQuery(checkDuplicateID);
            if(!checkResult.next()) {
                if (hotel_button_click_signup == 1) {
                    int rowsAffected = statement.executeUpdate(verifysignup);
                    //int createTableRowsAffected = statement.executeUpdate(createHotelTable);
                    if (rowsAffected > 0) {
                        servicelogin_label.setText("Welcome! Account Created");
                    } else {
                        servicelogin_label.setText("Failed to create account.");
                    }
                }
                if (car_button_click_signup == 1) {
                    int rowsAffected = statement.executeUpdate(verifysignup2);
                    //int createTableRowsAffected = statement.executeUpdate(createCarTable);
                    if (rowsAffected > 0) {
                        servicelogin_label.setText("Welcome! Account Created");
                    } else {
                        servicelogin_label.setText("Failed to create account.");
                    }
                }
            }
            else{
                servicelogin_label.setText("Service ID already exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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


