package team.logica_populi.javafxdemo.xml.properties;

import team.logica_populi.javafxdemo.xml.properties.transformers.DoubleTransformer;

public class DoubleProperty extends Property<Double> {
    public DoubleProperty(String name, Double defaultValue) {
        this(name, defaultValue, false);
    }

    public DoubleProperty(String name, Double defaultValue, boolean optional) {
        super(name, defaultValue, DoubleTransformer.transformer, optional);
    }

    public void addValue(double toAdd) {
        setValue(getValue() + toAdd);
    }
}
