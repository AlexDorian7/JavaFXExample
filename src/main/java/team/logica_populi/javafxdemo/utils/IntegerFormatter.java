package team.logica_populi.javafxdemo.utils;

import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;

public class IntegerFormatter extends TextFormatter<Integer> {
    public IntegerFormatter(int i) {
        super(new StringConverter<>() {
            @Override
            public String toString(Integer aInteger) {
                return String.valueOf(aInteger);
            }

            @Override
            public Integer fromString(String s) {
                return Integer.parseInt(s);
            }
        }, i);
    }
}
