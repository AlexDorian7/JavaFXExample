package team.logica_populi.javafxdemo.ui.FormFields;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.jetbrains.annotations.Nullable;
import team.logica_populi.javafxdemo.utils.IntegerFormatter;

import java.util.function.Consumer;


public class IntegerField extends HBox {
    private final String name;
    private int value;

    public IntegerField(String name, int value, @Nullable Consumer<Integer> onChange) {

        this.name = name;
        this.value = value;

        setPrefSize(256, 32);
        Text label = new Text(name + ": ");
        TextField field = new TextField();
        if (onChange == null) {
            field.setDisable(true);
        }
        Text valueLabel = new Text(String.valueOf(this.value));
        valueLabel.setStroke(Color.gray(0.5));
        IntegerFormatter formatter = new IntegerFormatter(value);
        field.setTextFormatter(formatter);
        field.setOnAction(event -> {
            this.value = formatter.getValue();
            valueLabel.setText(String.valueOf(this.value));
            if (onChange != null) {
                onChange.accept(this.value);
            }
        });

        getChildren().addAll(label, field, valueLabel);
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
