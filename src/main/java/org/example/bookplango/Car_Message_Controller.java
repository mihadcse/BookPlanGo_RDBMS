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

public class Car_Message_Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public Label username_label_dashboard,message_sent_label;
    @FXML
    private TextArea message_text_area_view,message_send_text;
    @FXML
    private TextField receiver_name;
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
    private TableView<Traveler_Message> serviceMessageTableView;
    @FXML
    private TableColumn<Traveler_Message,String> serviceNameTableColumn;
    @FXML
    private TableColumn<Traveler_Message,String>serviceMessageTableColumn;

    @FXML
    private TableView<Traveler_chat_list> serviceChatList;
    @FXML
    private TableColumn<Traveler_chat_list,String> serviceChatColumn;

    ObservableList<Traveler_Message> service_messageObservableList = FXCollections.observableArrayList();
    ObservableList<Traveler_chat_list> service_chatObservableList = FXCollections.observableArrayList();

    String S_ID;
    String name;

    public void setChat() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        serviceChatList.getItems().clear();

        String query_get_chat ;

        query_get_chat="WITH conversations AS (\n"+
                "SELECT to_name AS user_id FROM bookplango.message WHERE from_id = '"+S_ID+"'\n"+
                "UNION\n"+
                "SELECT from_id FROM bookplango.message WHERE to_name = '"+S_ID+"'\n"+
                ")\n"+
                "SELECT c.user_id\n"+
                "FROM conversations c\n"+
                "JOIN bookplango.message m\n"+
                "ON (c.user_id = m.from_id OR c.user_id = m.to_name)\n"+
                "WHERE m.from_id = '"+S_ID+"' OR m.to_name = '"+S_ID+"'\n"+
                "GROUP BY c.user_id\n"+
                "ORDER BY MAX(m.id) DESC;\n";

        Statement statement3 = connectDB.createStatement();
        ResultSet res3 = statement3.executeQuery(query_get_chat);

        while (res3.next()) {
            String queryName = res3.getString("user_id");
            if (!queryName.equals(S_ID)) {
                service_chatObservableList.add(new Traveler_chat_list(queryName));
            }

        }
        res3.close();

        serviceChatColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        serviceChatList.setItems(service_chatObservableList);
    }

    public void setWelcome(String n) throws SQLException {
        boolean b=true;
        serviceMessageTableView.setDisable(b);
        message_send_text.setDisable(b);
        viewOrLoad.setDisable(b);
        message_text_area_view.setDisable(b);
        send.setDisable(b);
        add.setDisable(b);
        //username_label_dashboard.setText(name);
        S_ID = n;
        setChat();
    }

    @FXML
    public void back(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("car_welcome.fxml"));
        Parent root1 = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root1);
        stage.setScene(scene);
        stage.setResizable(false);
        CarWelcomeController carWelcomeController=fxmlLoader.getController();
        carWelcomeController.setData(S_ID);
        stage.show();
    }


    @FXML
    public void setPreviousMsg() throws SQLException {
        boolean b=false;
        serviceMessageTableView.setDisable(b);
        message_send_text.setDisable(b);
        viewOrLoad.setDisable(b);
        message_text_area_view.setDisable(b);
        send.setDisable(b);


        serviceMessageTableView.getItems().clear();
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String query_get_msg;
        query_get_msg = "SELECT * FROM bookplango.message " +
                "WHERE (from_id = '" + name + "' OR to_name = '" + name + "') " +
                "AND (from_id = '" + S_ID + "' OR to_name = '" + S_ID + "')" +
                "ORDER BY id DESC";

        Statement statement2 = connectDB.createStatement();
        ResultSet res1 = statement2.executeQuery(query_get_msg);

        while (res1.next()) {
            String queryName = res1.getString("from_id");
            //String queryMessage = res1.getString("message");
            String queryMessage = Encryption_Decryption_Message.decrypt(res1.getString("message"));
            if (queryName.equals(S_ID)) {
                queryName = "You";
            }
            service_messageObservableList.add(new Traveler_Message(queryName, queryMessage));
        }
        res1.close();

        serviceNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        serviceMessageTableColumn.setCellValueFactory(new PropertyValueFactory<>("message"));

        serviceMessageTableView.setItems(service_messageObservableList);
    }

    public void initialize() throws SQLException {
        ObservableList<String> serviceIds = FXCollections.observableArrayList();
        serviceIds.add("Admin");
        reciever.setItems(serviceIds);
    }
    @FXML
    public void setOpen() throws SQLException {
        ObservableList<Traveler_chat_list> chat_list;
        chat_list = serviceChatList.getSelectionModel().getSelectedItems();
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
        place_list = serviceMessageTableView.getSelectionModel().getSelectedItems();
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
                "('"+S_ID+"',\n" +
                "'"+name+"',\n" +
                "'"+Encryption_Decryption_Message.encrypt(message_send_text.getText())+"')";
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
