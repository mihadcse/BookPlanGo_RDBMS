package org.example.bookplango;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hotel_details_Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public Label hotel_Name,hotel_location,booking_label;
    @FXML
    private TextField expence;

    @FXML
    private DatePicker pick_start;
    @FXML
    private DatePicker pick_end;
    String s = "",H_name,H_address,H_image;
    Integer H_ID;
    Integer nid;
    Long total_expense;

    @FXML
    ImageView imageview;


    public void set_Welcome(String name)
    {
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

    public void switchtouserBookingHotelScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user_Booking_hotel.fxml"));
        Parent root = fxmlLoader.load();
        Traveler_Booking_Controller us = fxmlLoader.getController();
        us.setWelcome(s);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        us.initialize_hotel();
    }

    @FXML
    private TableView<Hotel_details> travelerHotelDetailsTableView;
    @FXML
    private TableColumn<Hotel_details,Integer> travelerRoomNumberTableColumn;
    @FXML
    private TableColumn<Hotel_details,String>travelerBeddingTableColumn;
    @FXML
    private TableColumn<Hotel_details,String>travelerACTableColumn;
    @FXML
    private TableColumn<Hotel_details,Integer>travelerPriceTableColumn;

    @FXML
    private TableColumn<Hotel_details,String>travelerRatingTableColumn;

    ObservableList<Hotel_details> traveler_hotelDetailsObservableList = FXCollections.observableArrayList();

    public void initialize(String name,String address) {
        H_name = name;
        H_address = address;
        hotel_Name.setText(H_name);
        hotel_location.setText(H_address);


        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String UpdateAvailable = "UPDATE h_roomdetails\n" +
                "SET room_status = 'Available',customer_id = null,book_start_date = null, book_end_date = null\n" +
                "WHERE book_end_date IS NULL OR book_end_date < CURDATE()";

        String Viewquery = "SELECT h_roomdetails.room_num, h_roomdetails.bedding, h_roomdetails.room_ac, " +
                "h_roomdetails.room_price, h_roomdetails.Rating AS Rating, h_roomdetails.RatingNum AS RatingNum " +
                "FROM h_roomdetails " +
                "JOIN serviceprovider_info ON serviceprovider_info.service_id = h_roomdetails.Hotel_ID " +
                "WHERE serviceprovider_info.service_name = '" + H_name + "' " +
                "AND room_status = 'Available'";

        String view = "Select * from serviceprovider_info, h_roomdetails where serviceprovider_info.service_id = h_roomdetails.Hotel_ID and serviceprovider_info.service_name = '"+H_name+"'";
        try{
            Statement statement2 = connectDB.createStatement();
            ResultSet queryOutput2 = statement2.executeQuery (view);
            while (queryOutput2.next()) {
                String queryimage = queryOutput2.getString("service_image");
                Integer query_ID = queryOutput2.getInt("Hotel_ID");
                H_image = queryimage;
                H_ID = query_ID;
            }
            queryOutput2.close();
            int queryUpadte = statement2.executeUpdate (UpdateAvailable);
            if(queryUpadte>0)
            {
                System.out.println("Updated Available rooms");
            }
            if (H_image != null) {
                Image image1 = new Image(H_image);
                imageview.setImage(image1);
            } else {
                System.out.println("No image");
            }
        }
        catch(SQLException e) {
            Logger.getLogger (Hotel_details_Controller.class.getName()).log (Level. SEVERE,null, e);
            e.printStackTrace();
        }

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(Viewquery);
            while (queryOutput.next()) {
                Integer queryroomnum = queryOutput.getInt("room_num");
                String querybedding = queryOutput.getString("bedding");
                String queryac = queryOutput.getString("room_ac");
                Integer queryprice = queryOutput.getInt("room_price");
                String rating;
                Float trn=queryOutput.getFloat("RatingNum");
                int tr= queryOutput.getInt("Rating");
                if(trn==0){
                    rating="N/A";
                }else{
                    rating=String.format("%.2f",tr/trn );
                }

                traveler_hotelDetailsObservableList.add(new Hotel_details(queryroomnum, queryprice, querybedding,queryac,rating));
            }

            travelerRoomNumberTableColumn.setCellValueFactory(new PropertyValueFactory<>("Room_number"));
            travelerBeddingTableColumn.setCellValueFactory(new PropertyValueFactory<>("Bedding"));
            travelerACTableColumn.setCellValueFactory(new PropertyValueFactory<>("ac"));
            travelerPriceTableColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
            travelerRatingTableColumn.setCellValueFactory(new PropertyValueFactory<>("Rating"));

            travelerHotelDetailsTableView.setItems(traveler_hotelDetailsObservableList);
        }
        catch(SQLException e) {
            Logger.getLogger (Hotel_details_Controller.class.getName()).log (Level. SEVERE,null, e);
            e.printStackTrace();
        }
    }

    public void Book_room_click(ActionEvent event)
    {
        //System.out.println(H_name);
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        ObservableList<Hotel_details> room_list;
        room_list = travelerHotelDetailsTableView.getSelectionModel().getSelectedItems();
        LocalDate startDate = pick_start.getValue();
        LocalDate endDate = pick_end.getValue();

        // Get the day of the month for both dates
        /*int startDay = startDate.getDayOfMonth();
        int endDay = endDate.getDayOfMonth();*/

        // Calculate the difference in days between the two dates
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

        total_expense = daysBetween * room_list.get(0).getPrice();
        //expence.setText(Long.toString(total_expense));
        String query_name = "Select NID from bookplango.userinfo where Username = '"+s+"'";

        //String Viewquery = "UPDATE sys.h_roomdetails SET room_status = 'Booked' where room_num ='"+room_list.get(0).getRoom_number()+"'";
      
        try{
            String NewQuery;
            Integer queryoutput = 0;
            Statement statement2 = connectDB.createStatement();
            //int queryOutput2 = statement2.executeUpdate (Viewquery);
            
            ResultSet res = statement2.executeQuery(query_name);
            if(res.next())
            {

                nid = res.getInt("NID");
                NewQuery = "UPDATE bookplango.h_roomdetails\n" +
                        "SET customer_id = '"+nid+"',\n" +
                        "book_start_date = '"+startDate+"',\n" +
                        "book_end_date = '"+endDate+"',\n" +
                        "room_status = 'Booked'\n" +
                        "WHERE room_num = '"+room_list.get(0).getRoom_number()+"'\n" +
                        "AND room_status = 'Available'\n" +
                        "AND ( \n" +
                        "    '"+startDate+"' BETWEEN CURDATE() AND CURDATE() + INTERVAL 1 DAY  \n" +
                        "    OR  \n" +
                        "    '"+endDate+"' + INTERVAL 1 DAY BETWEEN CURDATE() AND CURDATE() + INTERVAL 1 DAY\n" +
                        ")";
                queryoutput = statement2.executeUpdate(NewQuery);
            }

            if(queryoutput>0)
            {
                System.out.println("Add date Updated");
                booking_label.setText("Booking complete");
                expence.setText(Long.toString(total_expense));
                String insert_details = "INSERT INTO `bookplango`.`tourdetails`\n" +
                        "(`traveler_nid`,`hotel_name`,`room_name`,`StartDate`,`EndDate`,`Destination`,`Total_Expenses`)\n" +
                        "VALUES\n" +
                        "("+nid+", '"+hotel_Name.getText()+"', '"+room_list.get(0).getRoom_number()+"', '"+startDate+"', '"+endDate+"', '"+hotel_location.getText()+"', "+total_expense+")";
                int insertResult = statement2.executeUpdate(insert_details);
                if (insertResult > 0) {
                    System.out.println("Insert done in tour details");
                }
            }
            else
            {
                booking_label.setText("Booking Rejected");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
