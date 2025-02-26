package team.logica_populi.javafxdemo.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.util.Pair;
import team.logica_populi.javafxdemo.ui.controllers.QuestionPaneController;
import team.logica_populi.javafxdemo.xml.tags.Question;

import java.io.IOException;

public class UiComponentMaker {
    public static Pair<Parent, QuestionPaneController> createQuestionPane(Question question) {
        FXMLLoader fxmlLoader = new FXMLLoader(UiComponentMaker.class.getResource("/views/QuestionPane.fxml"));
        Parent parent = null;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) { // If we fail to make this component stuff wont work properly so just throw a new runtime exception
            throw new RuntimeException(e);
        }
        QuestionPaneController controller = fxmlLoader.getController();
        controller.setQuestion(question);
        return new Pair<>(parent, controller);
    }
}
