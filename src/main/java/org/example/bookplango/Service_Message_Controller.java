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

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Service_Message_Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public Label username_label_dashboard,message_sent_label;
    @FXML
    private TextArea message_text_area_view,message_send_text;
    @FXML
    private TextField receiver_name;
    String s = "",Name;
    Integer nid;

    String S_ID;
    String S_name;

    public void setWelcome(String name)
    {
        //username_label_dashboard.setText(name);
        System.out.println(name);
        s = name;
        S_ID = name;
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
        hotelWelcomeDashboard.setWelcome(s);
        stage.show();
    }

    @FXML
    private TableView<Traveler_Message> serviceMessageTableView;
    @FXML
    private TableColumn<Traveler_Message,String> serviceNameTableColumn;
    @FXML
    private TableColumn<Traveler_Message,String>serviceMessageTableColumn;

    ObservableList<Traveler_Message> service_messageObservableList = FXCollections.observableArrayList();

    public void initialize() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        //Statement statement2 = connectDB.createStatement();
        String query_name = "Select service_name from bookplango.serviceprovider_info where service_id = '"+s+"'";
        Statement statement2 = connectDB.createStatement();
        ResultSet res = statement2.executeQuery(query_name);
        while (res.next()) {
            S_name = res.getString("service_name");
            /*System.out.println(S_name);
            System.out.println(S_name);
            System.out.println(S_name);
            System.out.println(S_name);*/
        }
        String queryOutput = "Select * from message where to_name = '" + S_name + "'";
        ResultSet res1 = statement2.executeQuery(queryOutput);

        while (res1.next()) {
            int queryName = res1.getInt("from_id");
            String queryMessage = res1.getString("message");
            service_messageObservableList.add(new Traveler_Message(queryMessage,queryName));
        }
        res1.close();

        serviceNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        serviceMessageTableColumn.setCellValueFactory (new PropertyValueFactory<>("message"));

        serviceMessageTableView.setItems (service_messageObservableList);

    }

    public void click_view()
    {
        ObservableList<Traveler_Message> place_list;
        place_list = serviceMessageTableView.getSelectionModel().getSelectedItems();

        message_text_area_view.setText(place_list.get(0).getMessage());
    }

    public void click_message() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        //Statement statement2 = connectDB.createStatement();
        String query_name = "Select service_name from bookplango.serviceprovider_info where service_id = '"+s+"'";
        Statement statement2 = connectDB.createStatement();
        ResultSet res = statement2.executeQuery(query_name);
        while (res.next()) {
            S_ID = res.getString("service_name");
            System.out.println(S_ID);
        }
        if(receiver_name.getText().equals("Admin")) {
            String query_insert1 = "INSERT INTO `bookplango`.`message`\n" +
                    "(`from_id`,\n" +
                    "`to_id`,\n" +
                    "`from_name`,\n" +
                    "`to_name`,\n" +
                    "`message`)\n" +
                    "VALUES\n" +
                    "(null,\n" +
                    "null,\n" +
                    "'"+ S_ID +"',\n" +
                    "'Admin',\n" +
                    "'"+ message_send_text.getText() +"')";
            Statement statement = connectDB.createStatement();
            int rowsAffected1 = statement.executeUpdate(query_insert1);
            if (rowsAffected1 > 0) {
                message_sent_label.setText("Message Sent");
                System.out.println("Message Sent");
            } else {
                message_sent_label.setText("Message Sending Failed");
                System.out.println("Message Sending Failed");
            }
        }
        else {

            String query_insert = "INSERT INTO `bookplango`.`message`\n" +
                    "(`from_id`,\n" +
                    "`to_id`,\n" +
                    "`from_name`,\n" +
                    "`to_name`,\n" +
                    "`message`)\n" +
                    "VALUES\n" +
                    "(null,\n" +
                    "'" + receiver_name.getText() + "',\n" +
                    "'" + S_ID + "',\n" +
                    "null,\n" +
                    "'" + message_send_text.getText() + "')";
            Statement statement3 = connectDB.createStatement();
            int rowsAffected = statement3.executeUpdate(query_insert);
            if (rowsAffected > 0) {
                message_sent_label.setText("Message Sent");
                System.out.println("Message Sent");
            } else {
                message_sent_label.setText("Message Sending Failed");
                System.out.println("Message Sending Failed");
            }
        }
        connectDB.close();
    }
}
