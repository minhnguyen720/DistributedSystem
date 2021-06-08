import java.io.DataOutputStream;
import java.net.Socket;

import hw0806.xmlUtils.CreateXML;

public class Client2 {

    private final static int PORT = 2001;
    private static String LOCAL = "127.0.0.1";

    public static void main(String[] args) {
        try {
            var clientPort2001 = new Socket(LOCAL, PORT);
            var XML_creator_2 = new CreateXML();
            String xmlString2 = XML_creator_2.createDoc(new Student(2, "jason", 2.0f));

            var out2 = new DataOutputStream(clientPort2001.getOutputStream());
            out2.writeUTF(xmlString2);

            clientPort2001.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
