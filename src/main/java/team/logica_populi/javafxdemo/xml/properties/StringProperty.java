package team.logica_populi.javafxdemo.xml.properties;

import team.logica_populi.javafxdemo.xml.properties.transformers.StringTransformer;

public class StringProperty extends Property<String> {
    public StringProperty(String name, String defaultValue) {
        this(name, defaultValue, false);
    }

    public StringProperty(String name, String defaultValue, boolean optional) {
        super(name, defaultValue, StringTransformer.transformer, optional);
    }
}
