package team.logica_populi.javafxdemo.xml.properties.transformers;

public interface Transformer<T> {
    String toString(T value);
    T fromString(String str);
}
