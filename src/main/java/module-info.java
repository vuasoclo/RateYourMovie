module com.example.rateyourmovie {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.web;
    requires javafx.graphics;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.rateyourmovie to javafx.fxml;
    exports com.example.rateyourmovie;
}