package ClientSocket.serverSide;

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

        // create book
        Book book = Creator.createBook(jsonString);
        System.out.println("---- BOOK INFO -----");
        System.out.println("Title: " + book.getTitle());
        System.out.println("Publisher: " + book.getPublisher());
        System.out.println("Author: " + book.getAuthor().getName() + " | age " + book.getAuthor().getAge());
    }
}
