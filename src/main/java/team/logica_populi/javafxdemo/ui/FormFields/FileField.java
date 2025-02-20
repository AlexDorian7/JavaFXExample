package team.logica_populi.javafxdemo.ui.FormFields;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.File;
import java.util.function.Consumer;
import java.util.logging.Logger;


public class FileField extends HBox {
    private final String name;
    private File value;

    private static final Logger logger = Logger.getLogger(FileField.class.getName());

    public FileField(String name, File value, @Nullable Consumer<File> onChange) {

        this.name = name;
        this.value = value;

        setPrefSize(256, 32);
        Text label = new Text(name + ": ");
        Button field = new Button("Choose File");
        if (onChange == null) {
            field.setDisable(true);
        }
        Text fileLabel = new Text(this.value.toString());
        fileLabel.setStroke(Color.gray(0.5));
        field.setOnAction(event -> {
            JFileChooser fileChooser = new JFileChooser(this.value);
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                this.value = fileChooser.getSelectedFile();
                fileLabel.setText(this.value.toString());
                if (onChange != null) {
                    onChange.accept(this.value);
                }
            }
        });
        getChildren().addAll(label, field, fileLabel);
    }

    public String getName() {
        return name;
    }

    public File getValue() {
        return value;
    }

    public void setValue(File value) {
        this.value = value;
    }
}
