module com.example.rateyourmovie {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.rateyourmovie to javafx.fxml;
    exports com.example.rateyourmovie;
}