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

public class Admin_Message_Controller {
    private Stage stage;
    private Scene scene;

    @FXML
    public Label username_label_dashboard,message_sent_label;
    @FXML
    private TextArea message_text_area_view,message_send_text;
    @FXML
    private TextField receiver_name;
    String s = "",Name;
    Integer nid,detect_id=0;

    /*public void switchtoAdmindashboardscene(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("admin_welcome.fxml"));
        Parent root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }*/
    public void switchtoAdmindashboardscene(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("admin_welcome.fxml"));
        Parent root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    @FXML
    private TableView<Traveler_Message> adminMessageTableView;
    @FXML
    private TableColumn<Traveler_Message,String> adminNameTableColumn;
    @FXML
    private TableColumn<Traveler_Message,String>adminMessageTableColumn;
    @FXML
    private TableColumn<Traveler_Message,Integer>adminIDTableColumn;

    ObservableList<Traveler_Message> admin_messageObservableList = FXCollections.observableArrayList();

    public void initialize() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        Statement statement2 = connectDB.createStatement();

        String queryOutput = "Select * from message where to_name = 'Admin'";
        ResultSet res1 = statement2.executeQuery(queryOutput);

        while (res1.next()) {
            String queryName = res1.getString("from_name");
            String queryMessage = res1.getString("message");
            Integer queryID = res1.getInt("from_id");
            admin_messageObservableList.add(new Traveler_Message(queryName,queryMessage,queryID));
        }
        res1.close();

        adminNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        adminMessageTableColumn.setCellValueFactory (new PropertyValueFactory<>("message"));
        adminIDTableColumn.setCellValueFactory (new PropertyValueFactory<>("id"));

        adminMessageTableView.setItems (admin_messageObservableList);
    }

    public void click_view()
    {
        ObservableList<Traveler_Message> place_list;
        place_list = adminMessageTableView.getSelectionModel().getSelectedItems();
        message_text_area_view.setText(place_list.get(0).getMessage());
    }

    public void click_message() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        Statement statement2 = connectDB.createStatement();
        /*String query_name = "Select NID from sys.userinfo where Username = '"+s+"'";
        Statement statement2 = connectDB.createStatement();
        ResultSet res = statement2.executeQuery(query_name);
        while (res.next()) {
            nid = res.getInt("NID");
            System.out.println(nid);
        }*/
        String text = receiver_name.getText();
        try {
            Integer.parseInt(text);
            detect_id = 1;
            System.out.println(text + " is a number");
        } catch (NumberFormatException e) {
            System.out.println(text + " is a string");
        }

        if(detect_id.equals(0)) {
            String query_insert1 = "INSERT INTO `bookplango`.`message`\n" +
                    "(`from_id`,\n" +
                    "`to_id`,\n" +
                    "`from_name`,\n" +
                    "`to_name`,\n" +
                    "`message`)\n" +
                    "VALUES\n" +
                    "(null,\n" +
                    "null,\n" +
                    "'Admin',\n" +
                    "'" + receiver_name.getText() + "',\n" +
                    "'" + message_send_text.getText() + "')";
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
        else if (detect_id.equals(1)) {
            String query_insert = "INSERT INTO `bookplango`.`message`\n" +
                    "(`from_id`,\n" +
                    "`to_id`,\n" +
                    "`from_name`,\n" +
                    "`to_name`,\n" +
                    "`message`)\n" +
                    "VALUES\n" +
                    "(null,\n" +
                    "'" + receiver_name.getText() + "',\n" +
                    "'Admin',\n" +
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
