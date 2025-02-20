package team.logica_populi.javafxdemo.utils;

import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;

public class DoubleFormatter extends TextFormatter<Double> {
    public DoubleFormatter(double d) {
        super(new StringConverter<>() {
            @Override
            public String toString(Double aDouble) {
                return String.valueOf(aDouble);
            }

            @Override
            public Double fromString(String s) {
                return Double.parseDouble(s);
            }
        }, d);
    }
}
