import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        int SERVER_PORT = 8000;
        Socket socket = null;
        Scanner reader = new Scanner(System.in);

        try {
            InetAddress serverHost = InetAddress.getByName("localhost");
            socket = new Socket(serverHost, SERVER_PORT);
            System.out.println("Just connected to " + socket.getRemoteSocketAddress());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            var sendObj = new ObjectOutputStream(socket.getOutputStream());

            // String message = "";
            // while (!message.equals("quit")) {
            // message = reader.nextLine();
            // out.writeUTF(message);
            // }

            while (true) {
                System.out.print("Command: ");
                String command = reader.nextLine();
                switch (command) {
                    case "book":
                        System.out.print("Name: ");
                        String name = reader.nextLine();
                        System.out.print("Author: ");
                        String author = reader.nextLine();

                        var newBook = new Book(name, author);
                        sendObj.writeObject(newBook);
                        break;
                    case "newspaper":
                        System.out.print("Name: ");
                        String newspaperName = reader.nextLine();
                        System.out.print("Type: ");
                        String type = reader.nextLine();
                        var newspaper = new Newspaper(newspaperName, type);
                        sendObj.writeObject(newspaper);
                        break;
                    default:
                        System.exit(1);
                }

                out.close();
                reader.close();
            }

        } catch (Exception e) {
        }
    }
}
