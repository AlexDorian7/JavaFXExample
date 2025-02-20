package team.logica_populi.javafxdemo.ui.FormFields;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;


public class StringField extends HBox {
    private final String name;
    private String value;

    public StringField(String name, String value, @Nullable Consumer<String> onChange) {

        this.name = name;
        this.value = value;

        setPrefSize(256, 32);
        Text label = new Text(name + ": ");
        TextField field = new TextField();
        if (onChange == null) {
            field.setDisable(true);
        }
        Text valueLabel = new Text(this.value);
        valueLabel.setStroke(Color.gray(0.5));
        TextFormatter<String> formatter = new TextFormatter<>(TextFormatter.IDENTITY_STRING_CONVERTER, value);
        field.setTextFormatter(formatter);
        field.setOnAction(event -> {
            this.value = formatter.getValue();
            valueLabel.setText(this.value);
            if (onChange != null) {
                onChange.accept(this.value);
            }
        });

        getChildren().addAll(label, field, valueLabel) ;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
