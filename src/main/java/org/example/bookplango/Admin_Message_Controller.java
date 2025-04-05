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
    @FXML
    public ComboBox<String> reciever;
    @FXML
    private Button viewOrLoad;
    @FXML
    private Button send;
    @FXML
    private Button open;
    @FXML
    private  Button add;

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

    @FXML
    private TableView<Traveler_chat_list> AdminChatList;
    @FXML
    private TableColumn<Traveler_chat_list,String> AdminChatColumn;

    ObservableList<Traveler_chat_list> Admin_chatObservableList = FXCollections.observableArrayList();

    String name;
    @FXML
    public void setChat() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        AdminChatList.getItems().clear();

        String query_get_chat ;

        query_get_chat="WITH conversations AS (\n"+
                "SELECT to_name AS user_id FROM bookplango.message WHERE from_id = 'Admin'\n"+
                "UNION\n"+
                "SELECT from_id FROM bookplango.message WHERE to_name = 'Admin'\n"+
                ")\n"+
                "SELECT c.user_id\n"+
                "FROM conversations c\n"+
                "JOIN bookplango.message m\n"+
                "ON (c.user_id = m.from_id OR c.user_id = m.to_name)\n"+
                "WHERE m.from_id = 'Admin' OR m.to_name = 'Admin'\n"+
                "GROUP BY c.user_id\n"+
                "ORDER BY MAX(m.id) DESC;\n";

        Statement statement3 = connectDB.createStatement();
        ResultSet res3 = statement3.executeQuery(query_get_chat);

        while (res3.next()) {
            String queryName = res3.getString("user_id");
            if (!queryName.equals("Admin")){
                Admin_chatObservableList.add(new Traveler_chat_list(queryName));
            }

        }
        res3.close();

        AdminChatColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        AdminChatList.setItems(Admin_chatObservableList);
    }

    public void initialize() throws SQLException {
        boolean b=true;
        adminMessageTableView.setDisable(b);
        message_send_text.setDisable(b);
        viewOrLoad.setDisable(b);
        message_text_area_view.setDisable(b);
        send.setDisable(b);
        add.setDisable(b);
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        Statement statement2 = connectDB.createStatement();
        ObservableList<String> serviceIds = FXCollections.observableArrayList();
        String query_get_provider = "Select service_id from bookplango.serviceprovider_info";
        Statement statement0 = connectDB.createStatement();
        ResultSet res0 = statement0.executeQuery(query_get_provider);
        while (res0.next()) {
            serviceIds.add(String.valueOf(res0.getInt("service_id")));
        }
        res0.close();
        query_get_provider = "Select NID from bookplango.userinfo";
        Statement statement = connectDB.createStatement();
        ResultSet res = statement.executeQuery(query_get_provider);
        while (res.next()) {
            serviceIds.add(String.valueOf(res.getInt("NID")));
        }
        res.close();
        reciever.setItems(serviceIds);

        String queryOutput = "Select * from message where to_name = 'Admin' or from_id='Admin'";
        ResultSet res1 = statement2.executeQuery(queryOutput);

        while (res1.next()) {
            String queryName = res1.getString("from_id");
            String queryMessage = res1.getString("message");
            String queryID = res1.getString("to_name");
            admin_messageObservableList.add(new Traveler_Message(queryName,queryMessage));
        }
        res1.close();
        setChat();
    }

    @FXML
    public void setPreviousMsg() throws SQLException {
        boolean b=false;
        adminMessageTableView.setDisable(b);
        message_send_text.setDisable(b);
        viewOrLoad.setDisable(b);
        message_text_area_view.setDisable(b);
        send.setDisable(b);


        adminMessageTableView.getItems().clear();
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String query_get_msg;
        query_get_msg = "SELECT * FROM bookplango.message " +
                "WHERE (from_id = '" + name + "' OR to_name = '" + name+ "') " +
                "AND (from_id = 'Admin' OR to_name = 'Admin')" +
                "ORDER BY id DESC";

        Statement statement2 = connectDB.createStatement();
        ResultSet res1 = statement2.executeQuery(query_get_msg);

        while (res1.next()) {
            String queryName = res1.getString("from_id");
            String queryMessage = res1.getString("message");
            if (queryName.equals("Admin")) {
                queryName = "You";
            }
            admin_messageObservableList.add(new Traveler_Message(queryName, queryMessage));
        }
        res1.close();

        adminNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        adminMessageTableColumn.setCellValueFactory(new PropertyValueFactory<>("message"));
        adminMessageTableView.setItems(admin_messageObservableList);
    }

    @FXML
    public void setOpen() throws SQLException {
        ObservableList<Traveler_chat_list> chat_list;
        chat_list = AdminChatList.getSelectionModel().getSelectedItems();
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
        place_list = adminMessageTableView.getSelectionModel().getSelectedItems();
        message_text_area_view.setText(place_list.get(0).getMessage());
    }

    public void click_message() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String query_insert = "INSERT INTO `bookplango`.`message`\n" +
                "(`from_id`,\n" +
                "`to_name`,\n" +
                "`message`)\n" +
                "VALUES\n" +
                "('Admin',\n" +
                "'" + name + "',\n" +
                "'" + message_send_text.getText() + "')";
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
