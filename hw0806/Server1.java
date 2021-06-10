package hw0806;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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

            StringTokenizer tokenizer = new StringTokenizer(dataFromClient, ",");
            List<String> tokens = new ArrayList<>();
            while(tokenizer.hasMoreTokens()) {
                tokens.add(tokenizer.nextToken());
            }

            // add student to database
            List<Student> students = new ArrayList<>();
            XmlToObj converter = null;
            for (int i = 0; i < tokens.size(); i++) {
                converter = new XmlToObj(tokens.get(i));
                Student student = converter.getStudent();
                students.add(student);
            }

            for (int i = 0; i < students.size(); i++) {
                GradeManagementServer1.addStudentToTable(students.get(i));
            }

            // Sync database
            List<Student> other = GradeManagementServer2.getAllStudents();

            List<Student> syncList = SynchronizeDB.isDuplicate(students,other);
            SynchronizeDB.synchronizedDb(syncList);

            // closing
            server.close();
            client.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}