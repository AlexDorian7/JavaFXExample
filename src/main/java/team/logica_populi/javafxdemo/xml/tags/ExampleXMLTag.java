package team.logica_populi.javafxdemo.xml.tags;

import team.logica_populi.javafxdemo.xml.properties.BooleanProperty;
import team.logica_populi.javafxdemo.xml.properties.IntegerProperty;

public class ExampleXMLTag extends XMLTreeNode {

    // Name should not contain spaces
    // Will be converted into <Example ExampleIntProperty="1" />
    public final IntegerProperty exampleIntProperty = new IntegerProperty("ExampleIntProperty", 1);
    // For optional fields, null is a valid value
    public final BooleanProperty exampleOptionalBoolProperty = new BooleanProperty("ExampleOptionalBoolProperty", null, true);
    protected ExampleXMLTag() {
        super("Example"); // <Example>...</Example>

        // Don't forget to add your properties to the back end. Without this the XML parser won't know they exist
        addProperties(exampleIntProperty, exampleOptionalBoolProperty);
    }
}
