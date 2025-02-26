package team.logica_populi.javafxdemo.ui;

import javafx.scene.layout.VBox;
import team.logica_populi.javafxdemo.ui.FormFields.*;

import java.io.File;

public class FormFieldDemo extends VBox {
    public FormFieldDemo() {
        super();
        BooleanField booleanField = new BooleanField("Example Bool Field", false, ((Boolean newValue) -> {
            // TODO: do something with the new value.
            // Note that any Fields that have a text box for their input require "Enter" to be pressed for this function to be called.
            // Also note that if this function is instead replaced with null, the field will be considered disabled and will not allow changes.
        }));
        DoubleField doubleField = new DoubleField("Example Double Field", 1.0, (Double newValue) -> {});
        FileField fileField = new FileField("Example File Field", new File("/"), (File newValue) -> {});
        IntegerField integerField = new IntegerField("Example Integer Field", 7, (Integer newValue) -> {});
        StringField stringField = new StringField("Example String Field", "Hello World", (String newValue) -> {});
        getChildren().addAll(booleanField, doubleField, fileField, integerField, stringField);
    }
}
