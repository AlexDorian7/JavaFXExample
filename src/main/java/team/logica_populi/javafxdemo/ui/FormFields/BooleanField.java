package team.logica_populi.javafxdemo.ui.FormFields;

import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.logging.Logger;


public class BooleanField extends HBox {
    private final String name;
    private boolean value;

    private static final Logger logger = Logger.getLogger(BooleanField.class.getName());

    public BooleanField(String name, boolean value, @Nullable Consumer<Boolean> onChange) {

        this.name = name;
        this.value = value;

        setPrefSize(256, 32);
        Text label = new Text(name + ": ");
        CheckBox field = new CheckBox();
        field.setSelected(value);
        if (onChange == null) {
            field.setDisable(true);
        }
        Text valueLabel = new Text(String.valueOf(this.value));
        valueLabel.setStroke(Color.gray(0.5));
        field.setOnAction(event -> {
            logger.finer("ACTION TRIGGERED");
            logger.finer(String.valueOf(field.isSelected()));
            this.value = field.isSelected();
            valueLabel.setText(String.valueOf(this.value));
            if (onChange != null) {
                onChange.accept(field.isSelected());
            }
        });
        getChildren().addAll(label, field, valueLabel);
    }

    public String getName() {
        return name;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
