package team.logica_populi.javafxdemo.xml.tags;

import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import team.logica_populi.javafxdemo.xml.properties.StringProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public abstract class XMLTreeNode extends XMLSerializable {

    private static final Logger logger = Logger.getLogger(XMLTreeNode.class.getName());

    private XMLTreeNode parent;
    private final ArrayList<XMLTreeNode> children;

    public final StringProperty name = new StringProperty("Name", "");

    protected XMLTreeNode(String tagName) {
        super(tagName);
        parent = null;
        children = new ArrayList<>();
    }

    protected XMLTreeNode(String tagName, XMLTreeNode parent) {
        super(tagName);
        this.parent = parent;
        children = new ArrayList<>();
        addProperty(name);
    }

    public XMLTreeNode getParent() {
        return parent;
    }

    public void setParent(XMLTreeNode parent) {
        this.parent = parent;
    }

    public ArrayList<XMLTreeNode> getChildren() {
        return children;
    }

    @Override
    public Element toXML(Document doc) {
        Element element = super.toXML(doc);
        children.forEach(xmlTreeNode -> {
            element.appendChild(xmlTreeNode.toXML(doc));
        });
        return element;
    }

    @Override
    public void fromXML(Document doc, Node node) {
        super.fromXML(doc, node);
        NodeList childNodes = node.getChildNodes();
        ArrayList<XMLSerializable> arrayList = XMLRegistry.getInstance().parseNodeList(doc, childNodes);
        for (XMLSerializable xmlSerializable : arrayList) {
            if (xmlSerializable instanceof XMLTreeNode) {
                XMLTreeNode xmlTreeNode = (XMLTreeNode) xmlSerializable;
                xmlTreeNode.parent = this;
                children.add(xmlTreeNode);
            }
        }
    }

    @Override
    public String toString() {
        String str = getTagName() + ": [";
        for (XMLTreeNode child : children) {
            str += child.toString() + ", ";
        }
        str += "]";
        return str;
    }


    public <T extends XMLTreeNode> List<T> getChildrenOfType(Class<T> clazz) {
        return getChildrenOfType(clazz, false);
    }

    public <T extends XMLTreeNode> List<T> getChildrenOfType(Class<T> clazz, boolean recursive) {
        List<T> list = new ArrayList<>();

        for (XMLTreeNode child : children) {
            if (clazz.isInstance(child)) {  // Allows subclasses of clazz
                list.add(clazz.cast(child)); // Safe casting
            }
            if (recursive) {
                list.addAll(child.getChildrenOfType(clazz, true)); // Recursively collect children
            }
        }
        return list;
    }

    @Nullable
    public <T extends XMLTreeNode> T getFirstParentOfType(Class<T> clazz) {
        if (parent == null) return null;
        if (clazz.isInstance(parent)) return (T) parent;
        return parent.getFirstParentOfType(clazz);
    }
}
