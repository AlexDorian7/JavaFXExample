package team.logica_populi.javafxdemo.xml.properties.transformers;

public class BooleanTransformer implements Transformer<Boolean> {
    public static final BooleanTransformer transformer = new BooleanTransformer();

    @Override
    public String toString(Boolean value) {
        return String.valueOf(value);
    }

    @Override
    public Boolean fromString(String str) {
        return Boolean.parseBoolean(str);
    }
}
