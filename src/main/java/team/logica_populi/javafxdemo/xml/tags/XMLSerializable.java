package team.logica_populi.javafxdemo.xml.tags;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import team.logica_populi.javafxdemo.xml.properties.Property;

import java.util.HashMap;
import java.util.logging.Logger;

public abstract class XMLSerializable {

    private static final Logger logger = Logger.getLogger(XMLSerializable.class.getName());
    private final HashMap<String, Property<?>> properties = new HashMap<>();
    private final String tagName;

    private XMLSerializable() {
        this("");
    }

    protected XMLSerializable(String tagName) {
        this.tagName = tagName;
    }

    protected void addProperties(Property<?>... properties) {
        for (Property<?> property : properties) {
            addProperty(property);
        }
    }

    protected void addProperty(Property<?> property) {
        if (properties.containsKey(property.getName())) {
            throw new IllegalStateException("Cannot add a property with a non unique name of " + property.getName());
        }
        properties.put(property.getName(), property);
    }

    public String getTagName() {
        return tagName;
    }

    public void fromXML(Document doc, Node node) {
        properties.forEach((String name, Property<?> property) -> {
            Node attr = node.getAttributes().getNamedItem(name);
            if (attr == null) {
                if (!property.isOptional()) {
                    throw new IllegalStateException("Property value not found for required property " + property.getName());
                }
                property.setValue(null);
                return;
            }
            property.fromString(attr.getNodeValue());
        });
    }

    public Element toXML(Document doc) {
        Element element = doc.createElement(tagName);
        properties.forEach((String name, Property<?> property) -> {
            if (!(property.isOptional() && property.getValue() == null)) {
                element.setAttribute(name, property.toString());
            }
        });
        return element;
    }

}
