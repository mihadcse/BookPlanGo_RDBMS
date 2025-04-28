package org.example.bookplango;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import java.util.ArrayList;

public class Traveler_Message_Controller {
    int vl=0;
    private Stage stage;
    private Scene scene;
    private Parent root;
    String name;
    @FXML
    public Label username_label_dashboard,message_sent_label;
    @FXML
    private TextArea message_text_area_view,message_send_text;
    @FXML
    private ComboBox<String> reciever;
    @FXML
    private Button viewOrLoad;
    @FXML
    private Button send;
    @FXML
    private Button open;
    @FXML
    private  Button add;
    String s = "",Name;
    Integer nid;

    @FXML
    private TableView<Traveler_Message> travelerMessageTableView;
    @FXML
    private TableColumn<Traveler_Message,String> travelerNameTableColumn;
    @FXML
    private TableColumn<Traveler_Message,String>travelerMessageTableColumn;

    @FXML
    private TableView<Traveler_chat_list> travelerChatList;
    @FXML
    private TableColumn<Traveler_chat_list,String> travelerChatColumn;

    ObservableList<Traveler_Message> traveler_messageObservableList = FXCollections.observableArrayList();
    ObservableList<Traveler_chat_list> traveler_chatObservableList = FXCollections.observableArrayList();
    @FXML
    public void setChat() throws SQLException {
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
        travelerChatList.getItems().clear();

        String query_get_chat ;

        query_get_chat="WITH conversations AS (\n"+
                "SELECT to_name AS user_id FROM bookplango.message WHERE from_id = '"+String.valueOf(nid)+"'\n"+
                "UNION\n"+
                "SELECT from_id FROM bookplango.message WHERE to_name = '"+String.valueOf(nid)+"'\n"+
                ")\n"+
                "SELECT c.user_id\n"+
                "FROM conversations c\n"+
                "JOIN bookplango.message m\n"+
                "ON (c.user_id = m.from_id OR c.user_id = m.to_name)\n"+
                "WHERE m.from_id = '"+String.valueOf(nid)+"' OR m.to_name = '"+String.valueOf(nid)+"'\n"+
                "GROUP BY c.user_id\n"+
                "ORDER BY MAX(m.id) DESC;\n";

        Statement statement3 = connectDB.createStatement();
        ResultSet res3 = statement3.executeQuery(query_get_chat);

        while (res3.next()) {
            String queryName = res3.getString("user_id");
            if (!queryName.equals(String.valueOf(nid))) {
                traveler_chatObservableList.add(new Traveler_chat_list(queryName));
            }

        }
        res3.close();

        travelerChatColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        travelerChatList.setItems(traveler_chatObservableList);
    }

    public void setWelcome(String name) throws SQLException {
        boolean b=true;
        travelerMessageTableView.setDisable(b);
        message_send_text.setDisable(b);
        viewOrLoad.setDisable(b);
        message_text_area_view.setDisable(b);
        send.setDisable(b);
        add.setDisable(b);
        //username_label_dashboard.setText(name);
        System.out.println(name);
        s = name;
        setChat();

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





    public void initialize() throws SQLException {


        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        ObservableList<String> serviceIds = FXCollections.observableArrayList();
        serviceIds.add("Admin");
        String query_get_provider = "Select service_id from bookplango.serviceprovider_info";
        Statement statement0 = connectDB.createStatement();
        ResultSet res0 = statement0.executeQuery(query_get_provider);
        while (res0.next()) {
            serviceIds.add(String.valueOf(res0.getInt("service_id")));
        }
        res0.close();
        reciever.setItems(serviceIds);

    }
    @FXML
    public void setPreviousMsg() throws SQLException {
        boolean b=false;
        travelerMessageTableView.setDisable(b);
        message_send_text.setDisable(b);
        viewOrLoad.setDisable(b);
        message_text_area_view.setDisable(b);
        send.setDisable(b);


        travelerMessageTableView.getItems().clear();
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String query_get_msg;
        query_get_msg = "SELECT * FROM bookplango.message " +
                "WHERE (from_id = '" + name + "' OR to_name = '" + name+ "') " +
                "AND (from_id = '" + String.valueOf(nid) + "' OR to_name = '" + String.valueOf(nid) + "')" +
                "ORDER BY id DESC";

        Statement statement2 = connectDB.createStatement();
        ResultSet res1 = statement2.executeQuery(query_get_msg);

        while (res1.next()) {
            String queryName = res1.getString("from_id");
            String queryMessage = res1.getString("message");
            if (queryName.equals(String.valueOf(nid))) {
                queryName = "You";
            }
            traveler_messageObservableList.add(new Traveler_Message(queryName, queryMessage));
        }
        res1.close();

        travelerNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        travelerMessageTableColumn.setCellValueFactory(new PropertyValueFactory<>("message"));

        travelerMessageTableView.setItems(traveler_messageObservableList);
    }

    @FXML
    public void setOpen() throws SQLException {
        ObservableList<Traveler_chat_list> chat_list;
        chat_list = travelerChatList.getSelectionModel().getSelectedItems();
        name= chat_list.get(0).getName();
        setPreviousMsg();
    }

    @FXML
    public void setAdd() throws SQLException {
        name= reciever.getValue();
        setPreviousMsg();
    }
    @FXML
    public void setReciever() throws SQLException{
        if(!reciever.getValue().isBlank()){
            add.setDisable(false);
        }
    }

    public void click_view()
    {

        ObservableList<Traveler_Message> place_list;
        place_list = travelerMessageTableView.getSelectionModel().getSelectedItems();
        message_text_area_view.setText(place_list.get(0).getMessage());

    }

    public void click_message() throws SQLException {
        System.out.println(reciever.getValue());
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
                "`to_name`,\n" +
                "`message`)\n" +
                "VALUES\n" +
                "('"+String.valueOf(nid)+"',\n" +
                "'"+name+"',\n" +
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
        setChat();
        setPreviousMsg();
        statement.close();
        connectDB.close();
    }

}