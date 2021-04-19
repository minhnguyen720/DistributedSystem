import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private static Socket CLIENT_SOCKET;
    private static ObjectOutputStream out;
    private static int PORT = 2000;
    private static String HOST = "localhost";

    private static void initClient(String HOST, int PORT) {
        try {
            CLIENT_SOCKET = new Socket(HOST, PORT);
            out = new ObjectOutputStream(CLIENT_SOCKET.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void clientCom() {
        try {
            var gui = new GUI();
            boolean isBtnPressed = false;

            while (true) {
                System.out.print(""); // must have this line ,don't know why, it just work
                isBtnPressed = gui.getStatus(); 
                if (isBtnPressed) {
                    var student = new Student(gui.getName(), gui.getID(), gui.getYear());
                    // send to server
                    out.writeObject(student);
                    System.exit(1);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        initClient(HOST, PORT);
        clientCom();
    }
}