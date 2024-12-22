module com.example.calculatorfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jdi;


    opens com.example.calculatorfx to javafx.fxml;
    exports com.example.calculatorfx;
    exports com.example.calculatorfx.FXMLview;
    opens com.example.calculatorfx.FXMLview to javafx.fxml;
    exports com.example.calculatorfx.controller;
    opens com.example.calculatorfx.controller to javafx.fxml;
    exports com.example.calculatorfx.model;
    opens com.example.calculatorfx.model to javafx.fxml;
}