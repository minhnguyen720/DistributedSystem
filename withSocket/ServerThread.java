import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerThread implements Runnable {
    private Socket client;
    private ServerService ss;

    public ServerThread(Socket client, ServerService ss) {
        this.client = client;
        this.ss = ss;
    }

    // ANNOUNCEMENT
    private final String USERNAME_MESS = "Input username: ";
    private final String PASSWORD_MESS = "Input password: ";
    private final String NO_ACCOUNT = "Account is not exist";
    private final String ACCOUNT_EXISTED = "This account already exist";

    @Override
    public void run() {
        try {
            var fromClient = new DataInputStream(client.getInputStream());
            var toClient = new DataOutputStream(client.getOutputStream());

            login(fromClient, toClient);

            String clientSymbols = ss.getClientToken();
            String message = ss.display();
            boolean isMovable = true;

            while (ss.isWin() == null) {
                message = !isMovable ? "Already move Or you must wait for your openent\n" : "";
                message += ss.display() + "\n" + "Player " + clientSymbols + " choose your move[1 - 3]: \n";
                toClient.writeUTF(message);

                String input = fromClient.readUTF();
                int x = Integer.valueOf(input.split(" ")[0]);
                int y = Integer.valueOf(input.split(" ")[1]);
                if (!ss.chooseMove(x, y, clientSymbols))
                    isMovable = false;
            }
            if (ss.isWin().equals("draw"))
                toClient.writeUTF("Game over. Draw");
            else
                toClient.writeUTF("Game Over. Player " + ss.isWin() + " win!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void login(DataInputStream fromClient, DataOutputStream toClient) {
        try {
            toClient.writeUTF(USERNAME_MESS);
            String username = fromClient.readUTF();
            toClient.writeUTF(PASSWORD_MESS);
            String password = fromClient.readUTF();

            if (!ss.authentication(username, password)) {
                toClient.writeUTF(NO_ACCOUNT);
                createNewAccount(fromClient, toClient);
                client.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createNewAccount(DataInputStream fromClient, DataOutputStream toClient) {
        try {
            toClient.writeUTF("=========Createing new account=========\nInput username: ");
            String user = fromClient.readUTF();
            toClient.writeUTF("Input password: ");
            String pass = fromClient.readUTF();

            if (!ss.createAccount(user, pass))
                toClient.writeUTF(ACCOUNT_EXISTED);
            else
                toClient.writeUTF("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}