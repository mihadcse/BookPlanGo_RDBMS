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

public class Hotel_Dashboard_Controller {
    private Stage stage;
    private Scene scene;
    @FXML
    public Label hotel_label_dashboard;
    @FXML
    public Button MakeUnavailable;
    @FXML
    public Button EditRoom;
    String name;

    public void switchtoserviceSigninScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("service_signin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void setWelcome(String name) throws SQLException {
        h_r_n_label.setText("");
        MakeUnavailable.setVisible(false);
        EditRoom.setVisible(false);
        hotel_label_dashboard.setText(name);
        System.out.println(name);
        this.name=name;
        initialize();
    }
    public void switchtoserviceUpdateProfile(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader=new FXMLLoader(BookPlanGo_Main.class.getResource("service_edit_profile.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("service_edit_profile.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setResizable(false);
        Service_Edit_Profile_Controller serviceEditProfileController=fxmlLoader.getController();
        serviceEditProfileController.setdata(name);
        stage.show();
    }

    @FXML
    private TableView<Hotel_Dashboard> hotelDashboardTableView;
    @FXML
    private TableColumn<Hotel_Dashboard,Button> Select;
    @FXML
    private TableColumn<Hotel_Dashboard,Integer> hotelroomPriceTableColumn;
    @FXML
    private TableColumn<Hotel_Dashboard,String>hotelroomTypeTableColumn;
    @FXML
    private TableColumn<Hotel_Dashboard,String>hotelroomACTableColumn;
    @FXML
    private TableColumn<Hotel_Dashboard,String>hotelroomStatusTableColumn;
    @FXML
    private TableColumn<Hotel_Dashboard,String>hotelroomNumTableColumn;
    String hRN,hB,hRS,hRAC,hRP;
    int total;
    Hotel_Dashboard[] hotelDashboards;
    String[] h_room_nums;
    String[] roomAvailability;
    @FXML
    Button[] buttons;
    @FXML
    private Label h_r_n_label;
    String room_num;
    @FXML
    public Label message;

    ObservableList<Hotel_Dashboard> hotel_dashboardObservableList = FXCollections.observableArrayList();
    @FXML
    private void setButtonAction(ActionEvent e){
        for(int i=0;i<total;i++){
            if(e.getSource()==buttons[i]){
                h_r_n_label.setText(h_room_nums[i]);
                room_num=h_room_nums[i];
                System.out.println(room_num);
                if(roomAvailability[i].equals("Available")){
                    MakeUnavailable.setText("Make Unavailable");
                    MakeUnavailable.setVisible(true);
                    EditRoom.setVisible(true);
                }else if(roomAvailability[i].equals("Unavailable")){
                    MakeUnavailable.setText("Make Available");
                    MakeUnavailable.setVisible(true);
                    EditRoom.setVisible(true);
                }else{
                    MakeUnavailable.setVisible(false);
                    EditRoom.setVisible(false);
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
        statement.executeUpdate("UPDATE h_roomdetails SET room_status = '"+status+"' WHERE Hotel_ID="+Integer.parseInt(name)+" AND room_num = '"+room_num+"'");
        initialize();
    }
    @FXML
    public void setMessage(){
        message.setText("Updated");
    }
    @FXML
    public void setEditRoom(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hotel_edit_room.fxml"));
        Parent root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        HotelEditRoom hotelEditRoom=fxmlLoader.getController();
        hotelEditRoom.setData(name,room_num);
        stage.show();
    }
    public void initialize () throws SQLException {
        MakeUnavailable.setVisible(false);
        EditRoom.setVisible(false);
        h_r_n_label.setText("");
        hotel_dashboardObservableList.clear();
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String countRoom = "Select count(*) as total from h_roomdetails where Hotel_ID ='" + hotel_label_dashboard.getText()+"'";

        String hotelTableViewquery = "Select room_num,bedding,room_status,room_ac,room_price from h_roomdetails where Hotel_ID ='" + hotel_label_dashboard.getText()+"'";
        try{
            Statement statement = connectDB.createStatement();
            Statement statement1 = connectDB.createStatement();
            ResultSet resultSet = statement.executeQuery (countRoom);
            while (resultSet.next()) {
                total=resultSet.getInt("total");
            }int i=0;
            hotelDashboards=new Hotel_Dashboard[total];
            buttons=new Button[total];
            h_room_nums=new String[total];
            roomAvailability=new String[total];
            ResultSet resultSet1 = statement1.executeQuery (hotelTableViewquery );
            while(resultSet1.next()){
                roomAvailability[i]=resultSet1.getString("room_status");
                hotelDashboards[i]=new Hotel_Dashboard(resultSet1.getString("room_num"),resultSet1.getInt("room_price"),resultSet1.getString("bedding"),resultSet1.getString("room_ac"),resultSet1.getString("room_status"));
                hotel_dashboardObservableList.add(hotelDashboards[i]);
                i++;
            }
            for(int j=0;j<i;j++){
                h_room_nums[j]=hotelDashboards[j].getRoom_num();
                buttons[j]=hotelDashboards[j].getSelect();
                buttons[j].setOnAction(this::setButtonAction);
            }
            hotelroomNumTableColumn.setCellValueFactory (new PropertyValueFactory<>("Room_num"));
            hotelroomTypeTableColumn.setCellValueFactory(new PropertyValueFactory<>("Bedding"));
            hotelroomACTableColumn.setCellValueFactory (new PropertyValueFactory<>("Ac_non_ac"));
            hotelroomStatusTableColumn.setCellValueFactory (new PropertyValueFactory<>("Status"));
            hotelroomPriceTableColumn.setCellValueFactory (new PropertyValueFactory<>("Price"));
            Select.setCellValueFactory (new PropertyValueFactory<>("Select"));
            hotelDashboardTableView.setItems (hotel_dashboardObservableList);
            for(int j=0;j<i;j++){
                System.out.println(roomAvailability[j]);
            }

        }
        catch(SQLException e) {
            Logger.getLogger (Hotel_Dashboard_Controller.class.getName()).log (Level. SEVERE,null, e);
            e.printStackTrace();
        }
    }
    @FXML
    public void switchtoAddRoomScene(ActionEvent event) throws IOException, SQLException {
        //Parent root = FXMLLoader.load(getClass().getResource("user_dashboard.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hotel_room-add_controller.fxml"));
        Parent root = fxmlLoader.load();
        Hotel_room_add_controller hotelRoomAddController = fxmlLoader.getController();
        hotelRoomAddController.setdata(name);
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
        hotel_name.setWelcome(name);
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
        hotelWelcomeDashboard.setWelcome(name);
        stage.show();
    }

}
