package team.logica_populi.javafxdemo.ui.FormFields;

import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import org.jetbrains.annotations.Nullable;
import team.logica_populi.javafxdemo.utils.DoubleFormatter;

import java.util.function.Consumer;


public class DoubleField extends HBox {
    private final String name;
    private double value;

    public DoubleField(String name, double value, @Nullable Consumer<Double> onChange) {

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
        DoubleFormatter formatter = new DoubleFormatter(value);
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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
