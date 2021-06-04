package hw0406;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class XmlToObj {
    public Student student;

    public XmlToObj(String xmlStr) {
        Document doc = convertXmlString2Doc(xmlStr);

        int id = Integer.parseInt(doc.getElementsByTagName("id").item(0).getTextContent());
        String name = doc.getElementsByTagName("name").item(0).getTextContent();
        double grade = Double.parseDouble(doc.getElementsByTagName("grade").item(0).getTextContent());

        student = new Student(id, name, grade);
    }

    private Document convertXmlString2Doc(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlStr)));
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Student getStudent() {
        return student;
    }

}
