package ClientSocket.clientSide;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.json.simple.parser.ParseException;

public class Client {
    public static void main(String[] args) throws IOException, ParseException {
        var clientSocket = new Socket("127.0.0.1", 2000);

        // send data to server
        var out = new DataOutputStream(clientSocket.getOutputStream());

        // create book object
        var willie = new Author("willie",21);
        var book = new Book("hello world","vgu",willie);

        String jsonString = Reader.read(book);
        out.writeUTF(jsonString);

    }
}
