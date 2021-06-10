package hw0806;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ServerPort2000 {
    public static void main(String[] args) {
        final int PORT = 2000;
        try {
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("Listening on the port " + PORT);

            Socket client = server.accept();

            // receive data from client
            var in = new DataInputStream(client.getInputStream());

            String dataFromClient = in.readUTF();
            List<Student> students = ServerUtils.getStudents(dataFromClient);

            for (Student student : students) {
                GradeManagementPort2000.addStudentToTable(student);
            }

            // Sync database
            List<Student> list = GradeManagementPort2000.getAllStudents();
            List<Student> other = GradeManagementPort2001.getAllStudents();

            List<Student> syncList = SynchronizeDB.isDuplicate(list,other);
            SynchronizeDB.synchronizedDb(syncList);

            // closing
            ServerUtils.close(server, client);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}