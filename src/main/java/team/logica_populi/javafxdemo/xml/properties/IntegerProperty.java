package team.logica_populi.javafxdemo.xml.properties;

import team.logica_populi.javafxdemo.xml.properties.transformers.IntegerTransformer;

public class IntegerProperty extends Property<Integer> {
    public IntegerProperty(String name, Integer defaultValue) {
        this(name, defaultValue, false);
    }

    public IntegerProperty(String name, Integer defaultValue, boolean optional) {
        super(name, defaultValue, IntegerTransformer.transformer, optional);
    }
    public void addValue(int toAdd) {
        setValue(getValue() + toAdd);
    }

}
