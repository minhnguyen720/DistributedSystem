package hw0406;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static DataInputStream in;
    private static ServerSocket serverSocket;

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(1000);
            System.out.println("Listening on the port 1000");

            Socket socket = serverSocket.accept();

            // receive data from client
            in = new DataInputStream(socket.getInputStream());
            // print xml
            String dataFromClient = in.readUTF();

            var converter = new XmlToObj(dataFromClient);
            Student student = converter.getStudent();
            
            // add obj to database
            GradeManagement.addStudentToTable(student);

            // get data from database
           Student result = GradeManagement.getStudentById(14485);
           // create  xml
           var creator = new CreateXML();
           String xmlStr = creator.createDocGrade(result);

           // send data back to client
           var out = new DataOutputStream(socket.getOutputStream());
           out.writeUTF(xmlStr);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
