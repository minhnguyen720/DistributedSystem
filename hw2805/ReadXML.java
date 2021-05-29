package hw2805;

import javax.xml.parsers.DocumentBuilderFactory;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ReadXML {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory dbFac = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbFac.newDocumentBuilder();
            File xmlFile = new File("hw2805/staffs.xml");
            Document doc = builder.parse(xmlFile);

            Element staffs = (Element) doc.getDocumentElement();
            NodeList list = staffs.getElementsByTagName("staff");

            for (int i = 0; i < list.getLength(); i++) {
                Element staff = (Element) list.item(i);

                System.out.println("ID: " + staff.getAttribute("id"));
                System.out.println("Firstname: " + staff.getElementsByTagName("firstname").item(0).getTextContent());
                System.out.println("Lastname: " + staff.getElementsByTagName("lastname").item(0).getTextContent());
                System.out.println("Nickname: " + staff.getElementsByTagName("nickname").item(0).getTextContent());
                // print amt of salary
                System.out.println("Salary: " + staff.getElementsByTagName("salary").item(0).getTextContent());
                // print currency
                System.out.println("Currency: "
                        + ((Element) staff.getElementsByTagName("salary").item(0)).getAttribute("currency"));
                System.out.println("");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
