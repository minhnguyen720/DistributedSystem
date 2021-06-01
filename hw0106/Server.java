import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class Server {
    private static DataInputStream in;
    private static ServerSocket serverSocket;

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(1000);
            System.out.println("Listening on the port 1000");

            Socket socket = serverSocket.accept();

            // receive data from client
            in = new DataInputStream(socket.getInputStream());
            // print xml
            String dataFromClient = in.readUTF();

            // read data
            var tokens = new StringTokenizer(dataFromClient,",");
            String[] token = new String[2];
            for(int i = 0 ; i < token.length; i++) {
                token[i] = tokens.nextToken();
            }

            // print data
            System.out.println(token[0]);
            new ReadXML(token[1]);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
