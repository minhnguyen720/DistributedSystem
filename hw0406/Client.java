package hw0406;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 1000);

            // send data to server
            var createXML = new CreateXML();
            String xmlStr = createXML.createDoc(new Student(14485, "willie", 1.7));

            var out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(xmlStr);

            // receive data from server
            var in = new DataInputStream(socket.getInputStream());
            String data = in.readUTF();
            System.out.println(data);

            // close connection
            out.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}