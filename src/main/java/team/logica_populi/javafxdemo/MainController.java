package team.logica_populi.javafxdemo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {
    /**
     * reference to #welcomeText id in the FXML file
     */
    @FXML
    private Label welcomeText;

    /**
     * event handler set in the FXML file /views/main-view.fxml
     */
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}