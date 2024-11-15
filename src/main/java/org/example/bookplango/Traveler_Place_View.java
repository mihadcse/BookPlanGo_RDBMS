package org.example.bookplango;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Traveler_Place_View {
    private Stage stage;
    private Scene scene;
    UserWelcomeDashboard user_dash;
    String s = "",P_name,P_District,P_image,P_Division;

    Integer nid,check=0;

    @FXML
    private Label username_label_dashboard;

    @FXML
    private Label place_name_label,place_district_label,place_division_label;

    @FXML
    ImageView imageview;


    public void set_Welcome(String name)
    {
        System.out.println(name);
        s = name;
    }

    public void switchtouserPlanningScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("traveler_planning.fxml"));
        Parent root = fxmlLoader.load();
        Traveler_Planning_Controller us = fxmlLoader.getController();
        us.setWelcome(s);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void initialize(String name,String district,String division) {
        P_name = name;
        P_District = district;
        P_Division = division;

        place_name_label.setText(P_name);
        place_district_label.setText(P_District);
        place_division_label.setText(P_Division);

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String Viewquery = "Select * from travel_places where place_name = '"+P_name+"' and place_district = '"+P_District+"'";
        try{
            Statement statement2 = connectDB.createStatement();
            ResultSet queryOutput2 = statement2.executeQuery (Viewquery);
            while (queryOutput2.next()) {
                String queryimage = queryOutput2.getString("place_image_path");
                P_image = queryimage;
            }
            if (P_image != null) {
                Image image1 = new Image(P_image);
                imageview.setImage(image1);
            } else {
                System.out.println("No image");
            }
        }
        catch(SQLException e) {
            Logger.getLogger (Traveler_Place_View.class.getName()).log (Level. SEVERE,null, e);
            e.printStackTrace();
        }
    }

    public void add_favourite(ActionEvent event)
    {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String query_name = "Select NID from bookplango.userinfo where Username = '"+s+"'";

        try{
            Statement statement2 = connectDB.createStatement();
            ResultSet res = statement2.executeQuery(query_name);

            if(res.next()) {
                nid = res.getInt("NID");
                String check_query = "SELECT COUNT(*) FROM bookplango.traveler_fav_places WHERE traveler_nid = '" + nid + "' AND fav_place = '" + P_name + "' AND fav_place_district = '" + P_District + "'";
                ResultSet check_result = statement2.executeQuery(check_query);

                if (check_result.next()) {
                    int count = check_result.getInt(1);
                    if (count > 0) {
                        // Place already exists in favorites
                        System.out.println("Place already added to favorites.");
                        check = 1;
                        return; // No need to insert again
                    }
                }
            }
            if(check == 0) {
                //nid = res.getInt("NID");
                String Viewquery = "INSERT INTO `bookplango`.`traveler_fav_places`\n" +
                        "(`traveler_nid`,\n" +
                        "`fav_place`,\n" +
                        "`fav_place_district`,\n" +
                        "`fav_place_division`,\n" +
                        "`fav_place_image_path`)\n" +
                        "VALUES\n" +
                        "('"+nid+"',\n" +
                        "'"+P_name+"',\n" +
                        "'"+P_District+"',\n" +
                        "'"+P_Division+"',\n" +
                        "'"+P_image+"')";
                int queryOutput2 = statement2.executeUpdate (Viewquery);
                if(queryOutput2>0)
                {
                    System.out.println("Add Favourite Updated");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove_favourite(ActionEvent event)
    {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String query_name = "Select NID from bookplango.userinfo where Username = '"+s+"'";
        try{
            Statement statement2 = connectDB.createStatement();
            ResultSet res = statement2.executeQuery(query_name);
            if(res.next()) {
                Integer nid = res.getInt("NID");
                String Viewquery = "DELETE FROM `bookplango`.`traveler_fav_places`\n" +
                        "WHERE traveler_nid = '"+nid+"' and fav_place = '"+P_name+"' and fav_place_district = '"+P_District+"'";
                int queryOutput2 = statement2.executeUpdate (Viewquery);
                if(queryOutput2>0)
                {
                    System.out.println("Remove Favourite");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
