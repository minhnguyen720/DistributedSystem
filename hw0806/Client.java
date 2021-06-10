package hw0806;

import java.io.DataOutputStream;
import java.net.Socket;

import hw0806.xmlUtils.CreateXML;

public class Client {
    private final static int PORT = 2000;
    private static String LOCAL = "127.0.0.1";

    public static void main(String[] args) {
        try {

            // server 1
            var clientPort2000 = new Socket(LOCAL, PORT);
            var XML_creator_1 = new CreateXML();
            String willie = XML_creator_1.createDoc(new Student(1, "willie", 1.5f));
            String jason = XML_creator_1.createDoc(new Student(2,"jason",2.0f));

            String dataToServer = willie + "," + jason;

            var out1 = new DataOutputStream(clientPort2000.getOutputStream());
            out1.writeUTF(dataToServer);

            // closing
            clientPort2000.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
