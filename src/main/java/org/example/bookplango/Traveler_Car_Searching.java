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
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class Traveler_Car_Searching {
    @FXML
    private Label ID;
    @FXML
    private Label carLabel;
    @FXML
    private Label message;
    @FXML
    private Button seeDetails;
    @FXML
    private ComboBox<String> From;
    @FXML
    private ComboBox<String> To;
    @FXML
    private TableView<CarSearch> carSearchTable;
    @FXML
    private TableColumn<Button,CarSearch> Select;
    @FXML
    private TableColumn<Integer,CarSearch> Price;
    @FXML
    private TableColumn<String,CarSearch> SeatNum;
    @FXML
    private TableColumn<String,CarSearch> VehicleType;
    @FXML
    private TableColumn<String,CarSearch> VehicleRating;
    private Stage stage;
    private Scene scene;
    String S_ID,from,to,carL,bookingDate;
    int total;
    String[] carLicsence;
    CarSearch[] carSearch;
    Button[] buttons;
    @FXML
    private DatePicker datePicker;
    ObservableList<CarSearch> carSearches = FXCollections.observableArrayList();
    @FXML
    public void setCalender(){
        LocalDate minDate=LocalDate.now();
        LocalDate maxDate=LocalDate.now().plusDays(10);
        Callback<DatePicker, DateCell> dayCellFactory= datePicker1 -> new DateCell(){
            @Override
            public void updateItem(LocalDate item,boolean empty){
                super.updateItem(item,empty);
                if(item.isBefore(minDate) || item.isAfter(maxDate)){
                    setDisable(true);
                }
            }
        };
        datePicker.setDayCellFactory(dayCellFactory);
    }
    @FXML
    public void setData(String s){
        setCalender();
        S_ID=s;
        ID.setText(s);
        seeDetails.setDisable(true);
        ObservableList<String> observableList= FXCollections.observableArrayList("Dhaka","Mymensingh","Chattogram","Sylhet","Barishal","Khulna","Rangpur","Rajshahi");
        From.setItems(observableList);
        To.setItems(observableList);
    }
    @FXML
    private void setButtonAction(ActionEvent e){
        for(int i=0;i<total;i++){
            if(e.getSource()==buttons[i]){
                carLabel.setText(carLicsence[i]);
                carL=carLicsence[i];
                System.out.println(carL);
                seeDetails.setDisable(false);
                break;
            }
        }
    }
    public void setTable() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        Statement statement0=connectDB.createStatement();
        ResultSet resultSet0=statement0.executeQuery("Select count(*) as total from (select * from car_details WHERE location='"+from+"'  AND "+to+"='Yes' AND carStatus!='Unavailable' AND LiscenceNum NOT IN (SELECT a.LiscenceNum FROM car_details a JOIN carbookdetails b ON a.LiscenceNum = b.CarLicsence where BookingDate='"+bookingDate+"')) as subquery");
        Statement statement=connectDB.createStatement();
        ResultSet resultSet= statement.executeQuery("SELECT * \n" +
                "FROM car_details \n" +
                "WHERE location='"+from+"' \n" +
                "  AND "+to+"='Yes' \n" +
                "  AND carStatus!='Unavailable' AND LiscenceNum NOT IN (\n" +
                "    SELECT a.LiscenceNum \n" +
                "    FROM car_details a \n" +
                "    JOIN carbookdetails b \n" +
                "    ON a.LiscenceNum = b.CarLicsence where BookingDate='"+bookingDate+"'\n" +
                ");");
        while(resultSet0.next()){
            total=resultSet0.getInt("total");
        }carLicsence=new String[total];
        carSearch=new CarSearch[total];
        buttons=new Button[total];
        int i=0;
        String r="";
        while(resultSet.next()){
            carLicsence[i]=resultSet.getString("LiscenceNum");
            if(resultSet.getInt("RatingNum")==0){
                r="N/A";
            }else{
                r=String.format("%.2f",(resultSet.getFloat("Rating")/resultSet.getInt("RatingNum")));
            }
            carSearch[i]=new CarSearch(resultSet.getString("VehicleType"),resultSet.getString("Seat"),resultSet.getInt("price"),r);
            carSearches.add(carSearch[i]);
            i++;
        }
        for(int j=0;j<i;j++){
            buttons[j]=carSearch[j].getSelect();
            buttons[j].setOnAction(this::setButtonAction);
        }
        VehicleType.setCellValueFactory (new PropertyValueFactory<>("VType"));
        SeatNum.setCellValueFactory(new PropertyValueFactory<>("SNum"));
        Price.setCellValueFactory (new PropertyValueFactory<>("Price"));
        Select.setCellValueFactory (new PropertyValueFactory<>("Select"));
        VehicleRating.setCellValueFactory(new PropertyValueFactory<>("Rating"));
        carSearchTable.setItems (carSearches);
    }
    @FXML
    public void setSearch() throws SQLException {
        carSearchTable.getItems().clear();
        if(From.getValue()==null || To.getValue()==null || datePicker.getValue()==null){
            message.setText("Fill up Every Information");
        }else{
            from=From.getValue();
            to=To.getValue();
            bookingDate=String.valueOf(datePicker.getValue());
            setTable();
        }
    }
    @FXML
    public void setSearch2(String s,String f,String t,String bd) throws SQLException {
        setData(s);
        From.setValue(f);
        To.setValue(t);
        from=f;
        to=t;
        bookingDate=bd;
        datePicker.setValue(LocalDate.parse(bd));
        carSearchTable.getItems().clear();
        setTable();
    }
    @FXML
    public void setBack(ActionEvent event) throws IOException, SQLException, InterruptedException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user_Booking.fxml"));
        Parent root = fxmlLoader.load();
        //setWelcome(s);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        UserWelcomeDashboard userWelcomeDashboard=fxmlLoader.getController();
        userWelcomeDashboard.setWelcome(S_ID);
        stage.show();
    }
    @FXML
    public void setSeeDetails(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userSeeCarDetails.fxml"));
        Parent root = fxmlLoader.load();
        //setWelcome(s);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        UserSeeCarDetails userSeeCarDetails=fxmlLoader.getController();
        userSeeCarDetails.setData(S_ID,carL,from,to,bookingDate);
        stage.show();
    }
}
