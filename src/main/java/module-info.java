module com.test.bancafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires java.net.http;

    opens com.test.bancafx to javafx.fxml;
    opens com.test.bancafx.controllers to javafx.fxml;
    opens com.test.bancafx.model to javafx.fxml;
    opens com.test.bancafx.services to javafx.fxml;

    exports com.test.bancafx;
    exports com.test.bancafx.controllers;
    exports com.test.bancafx.model;
    exports com.test.bancafx.services;
}