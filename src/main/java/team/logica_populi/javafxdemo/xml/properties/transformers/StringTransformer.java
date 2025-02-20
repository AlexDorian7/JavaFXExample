package team.logica_populi.javafxdemo.xml.properties.transformers;

public class StringTransformer implements Transformer<String> {
    public static final StringTransformer transformer = new StringTransformer();

    @Override
    public String toString(String value) {
        return value;
    }

    @Override
    public String fromString(String str) {
        return str;
    }
}
