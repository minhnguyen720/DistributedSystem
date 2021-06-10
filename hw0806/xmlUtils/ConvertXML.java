package hw0806.xmlUtils;

import org.w3c.dom.Document;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;

import java.io.File;
import java.io.StringWriter;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;

public class ConvertXML {
    private Document doc;
    private TransformerFactory transFactory;

    public ConvertXML(Document doc) {
        this.doc = doc;
        transFactory = TransformerFactory.newInstance();
    }

    public void resultToTerminal() {
        try {
            Transformer trans = transFactory.newTransformer();
            StreamResult output = new StreamResult(System.out);
            DOMSource src = new DOMSource(doc);

            trans.transform(src, output);

        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }

    public void resultToFile(String PATH) {
        try {
            Transformer trans = transFactory.newTransformer();
            StreamResult output = new StreamResult(new File(PATH));
            DOMSource src = new DOMSource(doc);

            trans.transform(src, output);

        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public String convertDoc2XmlString() {
        String output = "";

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            output = writer.getBuffer().toString();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        return output;
    }

}
