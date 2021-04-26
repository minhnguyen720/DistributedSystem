import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        int serverPort = 2000;
        Socket socket = null;
        Scanner reader = new Scanner(System.in);
        try {
            InetAddress serverHost = InetAddress.getByName("localhost");
            System.out.println("Connecting to server on port " + serverPort);
            socket = new Socket(serverHost, serverPort);
            System.out.println("Just connected to " + socket.getRemoteSocketAddress());

            // Instruction
            System.out.println("=========== LIST OF COMMAND ==========");
            System.out.println(
                    "getTV - get TV's total\ngetRadio - get radio's total\ngetNewspaper - get newspaper's total\ngetSale - get total sale");

            // send message to server
            DataOutputStream toServer = new DataOutputStream(socket.getOutputStream());
            DataInputStream fromServer = new DataInputStream(socket.getInputStream());

            String request = reader.nextLine();
            toServer.writeUTF(request);

            double result = fromServer.readDouble();
            System.out.println("============== REPORT ==============");
            System.out.println(request + ": " + result);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            reader.close();
        }
    }
}
