package org.example.bookplango;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BookPlanGo_Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BookPlanGo_Main.class.getResource("selectuser.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("selectuser.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 475, 404);

        stage.setTitle("BookPlanGo!");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}

// dBase@BookPlanGo24


