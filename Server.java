import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private int PORT = 8000;
    private ServerSocket SERVER = null;

    public Server() throws Exception {
        this.SERVER = new ServerSocket(PORT);
        System.out.println("Server has been created");
    }

    public static void main(String[] args) {
        ArrayList<Socket> clientList = new ArrayList<>();

        try {
            ServerSocket serverSocket = ((new Server()).getSERVER());
            while (true) {
                if (clientList.size() <= 2) {
                    Socket client = serverSocket.accept();
                    clientList.add(client);
                    Thread worker = new Thread(new ServerThread(client, clientList, "Client " + clientList.size()));
                    worker.start();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ServerSocket getSERVER() {
        return SERVER;
    }
}
