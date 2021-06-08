import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import hw0806.xmlUtils.XmlToObj;

public class Server1 {
    private final static int PORT = 2000;
    private static DataInputStream in;

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("Listening on the port " + PORT);

            Socket client = server.accept();

            // receive data from client
            in = new DataInputStream(client.getInputStream());
            // print xml
            String dataFromClient = in.readUTF();
            var converter = new XmlToObj(dataFromClient);
            Student student = converter.getStudent();

            // add student to database
            GradeManagementServer1.addStudentToTable(student);

            // closing
            server.close();
            client.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}