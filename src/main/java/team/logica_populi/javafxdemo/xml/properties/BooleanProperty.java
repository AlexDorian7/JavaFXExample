package team.logica_populi.javafxdemo.xml.properties;

import team.logica_populi.javafxdemo.xml.properties.transformers.BooleanTransformer;

public class BooleanProperty extends Property<Boolean> {
    public BooleanProperty(String name, Boolean defaultValue) {
        this(name, defaultValue, false);
    }

    public BooleanProperty(String name, Boolean defaultValue, boolean optional) {
        super(name, defaultValue, BooleanTransformer.transformer, optional);
    }
}
