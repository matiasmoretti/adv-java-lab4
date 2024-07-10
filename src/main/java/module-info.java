module com.example.collections {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.collections to javafx.fxml;
    exports com.example.collections;
}