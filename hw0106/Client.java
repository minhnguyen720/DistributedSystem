import java.io.DataOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 1000);

            // create data
            var createXML = new CreateXML();
            createXML.createDoc(new Customer(14485, "Nguyen", 21));

            // get storage location of data
            var convertXML = new ConvertXML(createXML.getDoc());
            String data = convertXML.docToXmlString() + "," + createXML.getPath();

            // send data to server
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(data);

            // close connection
            out.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}