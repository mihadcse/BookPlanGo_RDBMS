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

public class Traveler_Message_Controller {
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

    public void setWelcome(String name)
    {
        //username_label_dashboard.setText(name);
        System.out.println(name);
        s = name;
    }

    public void switchtouserdashboardScene(ActionEvent event) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("user_dashboard.fxml"));
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

    @FXML
    private TableView<Traveler_Message> travelerMessageTableView;
    @FXML
    private TableColumn<Traveler_Message,String> travelerNameTableColumn;
    @FXML
    private TableColumn<Traveler_Message,String>travelerMessageTableColumn;

    ObservableList<Traveler_Message> traveler_messageObservableList = FXCollections.observableArrayList();

    public void initialize() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String query_name = "Select NID from bookplango.userinfo where Username = '"+s+"'";
        Statement statement2 = connectDB.createStatement();
        ResultSet res = statement2.executeQuery(query_name);
        while (res.next()) {
            nid = res.getInt("NID");
            System.out.println(nid);
        }
        res.close();
        String queryOutput = "Select * from message where to_id = '" + nid + "'";
        ResultSet res1 = statement2.executeQuery(queryOutput);

        while (res1.next()) {
            String queryName = res1.getString("from_name");
            String queryMessage = res1.getString("message");
            traveler_messageObservableList.add(new Traveler_Message(queryName, queryMessage));
        }
        res1.close();

        travelerNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        travelerMessageTableColumn.setCellValueFactory (new PropertyValueFactory<>("message"));

        travelerMessageTableView.setItems (traveler_messageObservableList);

    }

    public void click_view()
    {
        ObservableList<Traveler_Message> place_list;
        place_list = travelerMessageTableView.getSelectionModel().getSelectedItems();

        message_text_area_view.setText(place_list.get(0).getMessage());
    }

    public void click_message() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String query_name = "Select NID from bookplango.userinfo where Username = '"+s+"'";
        Statement statement2 = connectDB.createStatement();
        ResultSet res = statement2.executeQuery(query_name);
        while (res.next()) {
            nid = res.getInt("NID");
            System.out.println(nid);
        }

        String query_insert = "INSERT INTO `bookplango`.`message`\n" +
                "(`from_id`,\n" +
                "`to_id`,\n" +
                "`from_name`,\n" +
                "`to_name`,\n" +
                "`message`)\n" +
                "VALUES\n" +
                "('"+nid+"',\n" +
                "null,\n" +
                "null,\n" +
                "'"+receiver_name.getText()+"',\n" +
                "'"+message_send_text.getText()+"')";
        Statement statement = connectDB.createStatement();
        int rowsAffected = statement.executeUpdate(query_insert);
        if (rowsAffected > 0) {
            message_sent_label.setText("Message Sent");
            System.out.println("Message Sent");
        } else {
            message_sent_label.setText("Message Sending Failed");
            System.out.println("Message Sending Failed");
        }
        statement.close();
        connectDB.close();
    }

}
