package hw0806.xmlUtils;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import hw0806.Student;

public class CreateXML {
    public Document doc;
    public String path = "customer.xml";

    public String createDoc(Student student) {
        String output = "";
        try {
            // obj to doc
            DocumentBuilderFactory dbFac = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFac.newDocumentBuilder();
            doc = dBuilder.newDocument();

            // create root node
            Element root = doc.createElement("students");
            // add 'user' node to the root node
            Element node = newNode(doc, student.getId(), student.getName(), student.getGrade());
            root.appendChild(node);
            // add to doc
            doc.appendChild(root);

            // doc is ready

            // converting result to file
            var convertXML = new ConvertXML(doc);
            convertXML.resultToFile(path);
            output = convertXML.convertDoc2XmlString();
            

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return output;
    }

    private Element newNode(Document doc, int id, String name, double grade) {
        // create child node
        Element user = doc.createElement("student");

        // variable within the child node
        Element idNode = doc.createElement("id");
        idNode.appendChild(doc.createTextNode(Integer.toString(id)));
        user.appendChild(idNode);

        Element nameNode = doc.createElement("name");
        nameNode.appendChild(doc.createTextNode(name));
        user.appendChild(nameNode);

        Element gradeNode = doc.createElement("grade");
        gradeNode.appendChild(doc.createTextNode(Double.toString(grade)));
        user.appendChild(gradeNode);

        return user;
    }

    private Element newGradeNode(Document doc, double grade) {
        Element user = doc.createElement("student");
        Element gradeNode = doc.createElement("grade");
        gradeNode.appendChild(doc.createTextNode(Double.toString(grade)));
        user.appendChild(gradeNode);

        return user;
    }

    
    public String createDocGrade(Student student) {
        String output = "";
        try {
            // obj to doc
            DocumentBuilderFactory dbFac = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFac.newDocumentBuilder();
            doc = dBuilder.newDocument();

            // create root node
            Element root = doc.createElement("students");
            // add 'user' node to the root node
            Element node = newGradeNode(doc, student.getGrade());
            root.appendChild(node);
            // add to doc
            doc.appendChild(root);

            // doc is ready

            // converting result to file
            var convertXML = new ConvertXML(doc);
            output = convertXML.convertDoc2XmlString();
            

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return output;
    }

    public Document getDoc() {
        return doc;
    }

    public String getPath() {
        return path;
    }
}