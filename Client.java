import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int PORT = 8000;
        Socket CLIENT = null;

        System.out.println("Welcome client");

        try {
            InetAddress HOST = InetAddress.getByName("localhost");
            CLIENT = new Socket(HOST, PORT);

            var toServer = new DataOutputStream(CLIENT.getOutputStream());
            var fromServer = new DataInputStream(CLIENT.getInputStream());

            Thread output = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(fromServer.readUTF());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            output.start();

            String message = "";
            while (!message.equals("bye")) {
                message = scanner.nextLine();
                toServer.writeUTF(message);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
