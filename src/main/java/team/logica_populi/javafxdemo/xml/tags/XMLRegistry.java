package team.logica_populi.javafxdemo.xml.tags;

import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XMLRegistry {

    private static final XMLRegistry instance = new XMLRegistry();
    private static final DocumentBuilder builder;
    private static final Logger logger = Logger.getLogger(XMLRegistry.class.getName());

    static {
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    private final HashMap<String, Class<? extends XMLSerializable>> registry = new HashMap<>();

    private XMLRegistry() {}

    public static XMLRegistry getInstance() {
        return instance;
    }

    public void register(String tagName, Class<? extends XMLSerializable> clazz) {
        if (registry.containsKey(tagName)) {
            throw new IllegalStateException("Cannot register XML with non unique name.");
        }
        registry.put(tagName, clazz);
    }

    @Nullable
    public Document loadDocument(File f) {
        Document doc;
        try {
            doc = builder.parse(f);
        } catch (SAXException | IOException e) {
            logger.log(Level.WARNING, "Failed to load/parse XML file at " + f.toString(), e);
            return null;
        }
        doc.getDocumentElement().normalize();
        return doc;
    }

    public ArrayList<XMLSerializable> parseNodeList(Document doc) {
        return parseNodeList(doc, doc.getDocumentElement().getChildNodes(), "");
    }

    public ArrayList<XMLSerializable> parseNodeList(Document doc, String parseOnly) {
        return parseNodeList(doc, doc.getDocumentElement().getChildNodes(), parseOnly);
    }

    public ArrayList<XMLSerializable> parseNodeList(Document doc, NodeList list) {
        return parseNodeList(doc, list, "");
    }

    public ArrayList<XMLSerializable> parseNodeList(Document doc, NodeList list, String parseOnly) {
        ArrayList<XMLSerializable> arrayList = new ArrayList<>();
        for (int i=0; i<list.getLength(); i++) {
            Node node = list.item(i);
            String tagName = node.getNodeName();
            if (!parseOnly.isEmpty() && !parseOnly.equals(tagName)) continue; // Don't parse this node
            if (registry.containsKey(tagName)) {
                try {
                    XMLSerializable xmlSerializable = registry.get(tagName).getDeclaredConstructor().newInstance();
                    xmlSerializable.fromXML(doc, node);
                    arrayList.add(xmlSerializable);
                } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                         IllegalAccessException e) {
                    logger.log(Level.FINEST, "ERROR: ", e);
                    throw new RuntimeException(e);
                }
            }
        }
        return arrayList;
    }

    @Nullable
    public XMLSerializable parseNode(Document doc, Node node) {
        String tagName = node.getNodeName();
        if (registry.containsKey(tagName)) {
            try {
                XMLSerializable xmlSerializable = registry.get(tagName).getDeclaredConstructor().newInstance();
                xmlSerializable.fromXML(doc, node);
                return xmlSerializable;
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                     IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Nullable
    public XMLSerializable parseNode(Document doc) {
        return parseNode(doc, doc.getDocumentElement());
    }

    @Nullable
    public XMLTreeNode loadXMLTree(File f) {
        Document doc = loadDocument(f);
        XMLSerializable xmlSerializable = parseNode(doc);
        if (xmlSerializable instanceof XMLTreeNode) {
            return (XMLTreeNode) xmlSerializable;
        }
        return null;
    }

    public void saveXMLTree(File f, XMLTreeNode root) {
        Document doc = XMLRegistry.getInstance().createDocument();
        doc.appendChild(root.toXML(doc));
        XMLRegistry.getInstance().saveXML(f, doc);
    }

    public void saveXML(File f, Document doc) {
        try {
            if (!f.exists()) {
                f.createNewFile();
            } else {
                String parentDir = f.getParent();
                String newName = ".~" + f.getName();
                File newFile = new File(parentDir, newName);
                if (newFile.exists()) {
                    boolean deleted = newFile.delete();
                    logger.info(String.valueOf(deleted));
                }
                boolean renamed = newFile.renameTo(newFile);
                logger.info(String.valueOf(renamed));
            }
            if (!f.canWrite()) {
                logger.log(Level.WARNING, "Cannot write to file: " + f);
                return;
            }
            FileWriter writer = new FileWriter(f);
            writer.write(getStringFromDocument(doc, true));
            writer.close();
        } catch (TransformerException | IOException e) {
            logger.log(Level.WARNING, "Failed to save to file: " + f, e);
        }
    }

    public static String getStringFromDocument(Document doc) throws TransformerException {
        return getStringFromDocument(doc, false);
    }

    public static String getStringFromDocument(Document doc, boolean formatOutput) throws TransformerException {
        DOMSource domSource = new DOMSource(doc);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();

        if (formatOutput) {
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        }

        transformer.transform(domSource, result);
        return writer.toString();
    }


    public Document createDocument() {
        return builder.newDocument();
    }
}
