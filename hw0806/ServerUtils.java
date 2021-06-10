package hw0806;

import hw0806.Student;
import hw0806.xmlUtils.XmlToObj;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ServerUtils {
    public static List<Student> getStudents(String dataFromClient) {
        var tokenizer = new StringTokenizer(dataFromClient, ",");
        List<String> tokens = new ArrayList<>();
        while(tokenizer.hasMoreTokens()) {
            tokens.add(tokenizer.nextToken());
        }

        // add student to database
        List<Student> students = new ArrayList<>();
        XmlToObj converter = null;

        for (String token : tokens) {
            converter = new XmlToObj(token);
            Student student = converter.getStudent();
            students.add(student);
        }
        return students;
    }

    public static void close(ServerSocket server, Socket client) throws IOException {
        server.close();
        client.close();
    }
}
