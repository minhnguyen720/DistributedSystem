package hw0806;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ServerPort2001 {

    public static void main(String[] args) {
        final int PORT = 2001;
        try {
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("Listening on the port " + PORT);

            Socket client = server.accept();

            // receive data from client
            var in = new DataInputStream(client.getInputStream());

            String dataFromClient = in.readUTF();
            List<Student> students = ServerUtils.getStudents(dataFromClient);

            for (Student student : students) {
                GradeManagementPort2001.addStudentToTable(student);
            }

            // closing
            ServerUtils.close(server, client);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}