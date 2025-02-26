module team.logica_populi.javafxdemo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires java.xml;
    requires java.logging;
    requires org.jetbrains.annotations;
    requires java.desktop;

    opens team.logica_populi.javafxdemo to javafx.fxml;
    opens team.logica_populi.javafxdemo.ui.controllers to javafx.fxml;
    exports team.logica_populi.javafxdemo;
    exports team.logica_populi.javafxdemo.xml.tags;
    exports team.logica_populi.javafxdemo.xml.properties;
    exports team.logica_populi.javafxdemo.xml.properties.transformers;
    exports team.logica_populi.javafxdemo.ui.controllers;
}