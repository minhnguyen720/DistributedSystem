import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CreateXML {
    private Document doc;
    private String path = "customer.xml";

    public void createDoc(Customer customer) {
        try {
            // obj to doc
            DocumentBuilderFactory dbFac = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFac.newDocumentBuilder();
            doc = dBuilder.newDocument();

            // create root node
            Element root = doc.createElement("customers");

            // add 'user' node to the root node
            root.appendChild(newNode(doc, customer.getID(), customer.getName(), customer.getAge()));

            // add to doc
            doc.appendChild(root);

            // doc is ready

            // save resule to xml file
            var convertXML = new ConvertXML(doc);
            convertXML.resultToFile(path);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private static Element newNode(Document doc, int id, String name, int age) {
        // create child node
        Element user = doc.createElement("customer");

        // variable within "book" node
        Element idNode = doc.createElement("id");
        idNode.appendChild(doc.createTextNode(Integer.toString(id)));
        user.appendChild(idNode);

        Element nameNode = doc.createElement("name");
        nameNode.appendChild(doc.createTextNode(name));
        user.appendChild(nameNode);

        Element ageNode = doc.createElement("age");
        ageNode.appendChild(doc.createTextNode(Integer.toString(age)));
        user.appendChild(ageNode);
        return user;
    }

    public String getPath() {
        return path;
    }

    public Document getDoc() {
        return doc;
    }
}