import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;

public class ReadXML {
    public ReadXML(String path) {
        var file = new File(path);
        try {
            // get target file
            DocumentBuilderFactory dbFac = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbFac.newDocumentBuilder();
            Document doc = builder.parse(file);

            // get element list session
            // get root node
            Element root = (Element) doc.getDocumentElement();
            NodeList list = root.getElementsByTagName("customer");

            // get and print target
            for (int i = 0; i < list.getLength(); i++) {
                Element customer = (Element) list.item(i);
                var newCustomer = new Customer(
                        Integer.parseInt(customer.getElementsByTagName("id").item(0).getTextContent()),
                        customer.getElementsByTagName("name").item(0).getTextContent(),
                        Integer.parseInt(customer.getElementsByTagName("age").item(0).getTextContent()));

                System.out.println(newCustomer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
