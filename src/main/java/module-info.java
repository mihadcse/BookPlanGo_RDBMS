module org.example.bookplango {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens org.example.bookplango to javafx.fxml;
    exports org.example.bookplango;
}