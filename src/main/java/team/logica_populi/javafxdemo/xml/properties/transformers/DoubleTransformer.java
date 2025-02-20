package team.logica_populi.javafxdemo.xml.properties.transformers;

public class DoubleTransformer implements Transformer<Double> {
    public static final DoubleTransformer transformer = new DoubleTransformer();

    @Override
    public String toString(Double value) {
        return String.valueOf(value);
    }

    @Override
    public Double fromString(String str) {
        return Double.parseDouble(str);
    }
}
