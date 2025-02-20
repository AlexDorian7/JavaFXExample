package team.logica_populi.javafxdemo.xml.properties.transformers;

public class IntegerTransformer implements Transformer<Integer> {
    public static final IntegerTransformer transformer = new IntegerTransformer();

    @Override
    public String toString(Integer value) {
        return String.valueOf(value);
    }

    @Override
    public Integer fromString(String str) {
        return Integer.parseInt(str);
    }
}
