package team.logica_populi.javafxdemo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@ExtendWith(ApplicationExtension.class)
class MainApplicationTest {

    private static final Logger logger = Logger.getLogger(MainApplicationTest.class.getName());

    @Start
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/views/main-view.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent, 320, 240);
        stage.setTitle("Hello!"); // Set the window title in the window title bar
        stage.setScene(scene); // set the scene that this window will display
        stage.show(); // make the stage (window) visible. Without this you won't see anything.
    }

    @Test
    void when_button_is_clicked_text_changes(FxRobot robot) {
        logger.info("Running Welcome Button Test.");
        robot.clickOn("#button");
        FxAssert.verifyThat("#welcomeText", LabeledMatchers.hasText("Welcome to JavaFX Application!"));
    }
}