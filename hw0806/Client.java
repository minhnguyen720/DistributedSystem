package hw0806;

import java.io.DataOutputStream;
import java.net.Socket;

import hw0806.xmlUtils.CreateXML;

public class Client {

    public static void main(String[] args) {
        final int PORT = 2000;
        final String LOCAL = "127.0.0.1";
        try {
            var xmlCreator = new CreateXML();

            // port 2000
            var clientPort2000 = new Socket(LOCAL, PORT);

            String willie = xmlCreator.createDoc(new Student(1, "willie", 1.5f));
            String jason = xmlCreator.createDoc(new Student(2,"jason",2.0f));

            String dataToServer2000 = willie + "," + jason;

            var to2000 = new DataOutputStream(clientPort2000.getOutputStream());
            to2000.writeUTF(dataToServer2000);

            // port 2001
            var clientPort2001 = new Socket(LOCAL,2001);

            String kirin = xmlCreator.createDoc(new Student(3, "kirin", 2.0f));
            String nhu = xmlCreator.createDoc(new Student(4,"nhu",2.1f));

            String dataToServer2001 = kirin + "," + nhu;

            var to2001 = new DataOutputStream(clientPort2001.getOutputStream());
            to2001.writeUTF(dataToServer2001);

            // closing
            clientPort2000.close();
            clientPort2001.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
