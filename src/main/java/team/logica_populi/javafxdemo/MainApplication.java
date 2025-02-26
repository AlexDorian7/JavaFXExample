package team.logica_populi.javafxdemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Pair;
import team.logica_populi.javafxdemo.ui.FormFieldDemo;
import team.logica_populi.javafxdemo.ui.UiComponentMaker;
import team.logica_populi.javafxdemo.ui.controllers.QuestionPaneController;
import team.logica_populi.javafxdemo.xml.tags.AnswerOptions;
import team.logica_populi.javafxdemo.xml.tags.ExampleXMLTag;
import team.logica_populi.javafxdemo.xml.tags.Question;
import team.logica_populi.javafxdemo.xml.tags.XMLRegistry;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class MainApplication extends Application {
    /**
     * This method will be called from JavaFX once JavaFX is ready
     * @param stage The starting stage (window)
     * @throws IOException throws if the FXML file could not be found
     */
    @Override
    public void start(Stage stage) throws IOException {
        /*
          This modified version of the start method will instead display a question pane made using FXML and will also show you how you can load custom data into it.
          @see UiComponentMaker
          The old version of the method is below in a comment
         */
        Question question = new Question();
        question.setData("What is 2+2?", "3", "5", "4", "2", AnswerOptions.ANSWER3);
        Pair<Parent, QuestionPaneController> pair = UiComponentMaker.createQuestionPane(question);
        Scene scene = new Scene(pair.getKey(), 600, 400);
        stage.setTitle("Question 1");
        stage.setScene(scene);
        stage.show();
    }

//    @Override
//    public void start(Stage stage) throws IOException {
//        // Paths function how you would expect them to. Keep in mind relative paths will start in the same file as the class file.
//        // The class by default is placed under its package name if you replaced all the . with /
//        // So for this class it would be /team/logica_populi/javafxdemo/MainApplication.class
//        // Keep in mind that the resource loader only looks for files in the compiled jar file. if you want to put files in this jar file use the resources folder under src/main
//        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/views/main-view.fxml"));
//        Parent parent = fxmlLoader.load();
//        ((Pane) parent).getChildren().add(new FormFieldDemo()); // Add a form Field Demo to the loaded FXML file dynamically
//        Scene scene = new Scene(parent, 320, 240);
//        stage.setTitle("Hello!"); // Set the window title in the window title bar
//        stage.setScene(scene); // set the scene that this window will display
//        stage.show(); // make the stage (window) visible. Without this you won't see anything.
//    }

    /**
     * This is the main entry point to the program
     * @param args the cmd line args supplied to the program
     */
    public static void main(String[] args) {

        Logger logger = null;

        // Set up the logger
        try {
            InputStream stream = MainApplication.class.getResourceAsStream("/logging.properties");
            LogManager.getLogManager().readConfiguration(stream);
            logger = Logger.getLogger(MainApplication.class.getName());
        } catch (IOException e) {
            logger = Logger.getLogger(MainApplication.class.getName());
            logger.warning("Failed to load logging.properties");
        }

        // You can log things to the console using a Logger object
        logger.log(Level.INFO, "Hello World");
        logger.info("Info Shorthand");
        // you can also log exceptions (great for debugging)
        try {
            throw new RuntimeException("Oh no! Something broke!");
        } catch (Exception e) {
            logger.log(Level.WARNING, "I'm a message", e);
        }

        // You can create a new logger object in a class by using a line like this
        // private static final Logger logger = Logger.getLogger(String name);
        // Typically the name passed is the same as the class name which can be gotten by doing <ClassName>.class.getName()
        // e.g. MainApplication.class.getName() or for the full line private static final Logger logger = Logger.getLogger(MainApplication.class.getName());
        // This line should be placed outside any methods since it defines a private field

        logger.fine("Registering XML tags");
        registerXML();
        logger.fine("Registered XML tags");

        launch(args); // Tell JavaFX to start
    }

    /**
     * Tell the XMLRegistry that these classes exist and map to XML tags
     * Without this the XMLParser won't parse nodes of these types
     * The tagName passes should be the one that is also passed in the classes Constructor.
     * <p>
     * You can load an XML Document by using <code>XMLRegistry.getInstance().loadXMLTree(File path)</code>
     * and save one with <code>XMLRegistry.getInstance().saveXMLTree(File path, XMLTreeNode root)</code>
     */
    private static void registerXML() {
        XMLRegistry.getInstance().register("Example", ExampleXMLTag.class);
        XMLRegistry.getInstance().register("Question", Question.class);
    }
}