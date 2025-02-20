package team.logica_populi.javafxdemo.xml.properties;

import team.logica_populi.javafxdemo.xml.properties.transformers.Transformer;

public class Property<T> {

    private final String name;
    private T value;
    private final Transformer<T> transformer;
    private final boolean optional;

    public Property(String name, T defaultValue, Transformer<T> transformer) {
        this(name, defaultValue, transformer, false);
    }

    public Property(String name, T defaultValue, Transformer<T> transformer, boolean optional) {
        this.name = name;
        this.value = defaultValue;
        this.transformer = transformer;
        this.optional = optional;
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Transformer<T> getTransformer() {
        return transformer;
    }

    public boolean isOptional() {
        return optional;
    }

    public void fromString(String str) {
        this.value = transformer.fromString(str);
    }

    @Override
    public String toString() {
        return transformer.toString(value);
    }
}
