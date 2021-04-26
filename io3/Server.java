import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
    public static void main(String[] args) {
        int port = 2000;
        ServerSocket SERVER_SOCKET = null;
        boolean STATUS = true;

        try {
            SERVER_SOCKET = new ServerSocket(port);
            System.out.println("Server created");
            ReadFile readFile = new ReadFile();
            List<Advertisement> list = readFile.readData("advertising.csv");
            readFile.writeData("testingFile.csv", list);

            while (STATUS) {
                Socket CLIENT_SOCKET = SERVER_SOCKET.accept();
                System.out.println("Just connected to " + CLIENT_SOCKET.getRemoteSocketAddress());

                DataOutputStream toClient = new DataOutputStream(CLIENT_SOCKET.getOutputStream());
                DataInputStream fromClient = new DataInputStream(CLIENT_SOCKET.getInputStream());

                String command = fromClient.readUTF();
                System.out.println("Command: " + command);

                switch (command) {
                case "getTV":
                    System.out.println("TV: " + readFile.getTvCounter());
                    toClient.writeDouble(readFile.getTvCounter());
                    break;
                case "getRadio":
                    System.out.println("Radio: " + readFile.getRadioCounter());
                    toClient.writeDouble(readFile.getRadioCounter());
                    break;
                case "getNewspaper":
                    System.out.println("Newspaper: " + readFile.getNewspaperCounter());
                    toClient.writeDouble(readFile.getNewspaperCounter());
                    break;
                case "getSale":
                    System.out.println("Sale" + readFile.getSaleCounter());
                    toClient.writeDouble(readFile.getSaleCounter());
                    break;
                default:
                    System.exit(1);
                }

                CLIENT_SOCKET.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}