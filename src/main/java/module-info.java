module com.example.cinemafrontend {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.cinemafrontend to javafx.fxml;
    opens com.example.cinemafrontend.controllers to javafx.fxml;
    opens com.example.cinemafrontend.controllers.models to javafx.fxml;
    exports com.example.cinemafrontend;
}