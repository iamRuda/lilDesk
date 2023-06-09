module com.iamruda.lildesk {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.iamruda.lildesk to javafx.fxml;
    exports com.iamruda.lildesk;
    exports com.iamruda.lildesk.tables;
    opens com.iamruda.lildesk.tables to javafx.fxml;
}