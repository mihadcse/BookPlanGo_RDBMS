package org.example.bookplango;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class UserSeeCarDetails {
    private Stage stage;
    private Scene scene;
    @FXML
    private Label vType;
    @FXML
    private Label sNum;
    @FXML
    private Label acNonAc;
    @FXML
    private Label price;
    @FXML
    private Label pID;
    @FXML
    private Label pContact;
    @FXML
    ImageView imageView;
    @FXML
    private Label message;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button Book;
    @FXML
    private Button Back;
    String S_ID,C_L,from,to,bookingDate;
    int cid;
    @FXML
    public void setData(String s,String c,String f,String t,String bd) throws SQLException, FileNotFoundException {
        System.out.println(c);
        S_ID=s;
        C_L=c;
        from=f;
        to=t;
        bookingDate=bd;
        DatabaseConnection connectNew = new DatabaseConnection();
        Connection connectDB = connectNew.getConnection();
        Statement statement=connectDB.createStatement();
        ResultSet resultSet=statement.executeQuery("Select CarID,AC,price,Seat,VehicleType,ImagePath from car_details where LiscenceNum='"+c+"';");
        String ac="",seat="",vt="",pc="",ip="";
        int p=-1;
        while(resultSet.next()){
            cid=resultSet.getInt("CarID");
            ac=resultSet.getString("AC");
            seat=resultSet.getString("Seat");
            vt=resultSet.getString("VehicleType");
            p=resultSet.getInt("price");
            ip=resultSet.getString("ImagePath");
        }Statement statement1=connectDB.createStatement();
        ResultSet resultSet1=statement1.executeQuery("Select service_phone_no from serviceprovider_info where service_id = "+cid+";");
        while(resultSet1.next()){
            pc=resultSet1.getString("service_phone_no");
        }vType.setText(vt);
        sNum.setText(seat);
        acNonAc.setText(ac);
        price.setText(String.valueOf(p));
        pID.setText(String.valueOf(cid));
        pContact.setText(pc);
        //Image image=new Image(new FileInputStream(ip+".png")); // Zarif's car image path
        // Convert filesystem path to URI
        File imageFile = new File(ip);
        Image image = new Image(imageFile.toURI().toString());
        imageView.setImage(image);
        System.out.println(ip);
        System.out.println(ip);
    }
    @FXML
    public void setBack(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user_Booking_car.fxml"));
        Parent root = fxmlLoader.load();
        //setWelcome(s);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        Traveler_Car_Searching travelerCarSearching=fxmlLoader.getController();
        travelerCarSearching.setSearch2(S_ID,from,to,bookingDate);
        stage.show();
    }@FXML
    public void setBook(ActionEvent event) throws SQLException, IOException, InterruptedException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        Statement statement0=connectDB.createStatement();
        statement0.executeUpdate("INSERT INTO carbookdetails (CarLicsence,BookingDate,CarID,Username,Start,End) VALUES('"+C_L+"','"+bookingDate+"',"+String.valueOf(cid)+",'"+S_ID+"','"+from+"','"+to+"');");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user_Booking.fxml"));
        Parent root = fxmlLoader.load();
        //setWelcome(s);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        UserWelcomeDashboard userWelcomeDashboard=fxmlLoader.getController();
        userWelcomeDashboard.setWelcome(S_ID);
        userWelcomeDashboard.BookingCondirm.setText("Car Booked");
        stage.show();
    }
}
