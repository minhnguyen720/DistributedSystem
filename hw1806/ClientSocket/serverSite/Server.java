package ClientSocket.serverSite;

import org.json.simple.parser.ParseException;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException, ParseException {
        var serverSocket = new ServerSocket(2000);
        System.out.println("Listening to port 2000");

        Socket clientSocket = serverSocket.accept();

        var in = new DataInputStream(clientSocket.getInputStream());
        String jsonString = in.readUTF();
        Writer.write(jsonString);
        // create book
        var book = Creator.createBook();
        System.out.println(book.getTitle());
        System.out.println(book.getPublisher());
        System.out.println(book.getAuthor().getName() + "," + book.getAuthor().getAge());
    }
}
