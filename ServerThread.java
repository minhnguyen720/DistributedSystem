import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ServerThread implements Runnable {
    Socket client = null;
    ArrayList<Socket> clientList;
    String clientName;
    int counter = 0;

    int PLAYER_1 = 1;
    int PLAYER_2 = 2;

    int row_player1 = 0;
    int row_player2 = 0;

    int column_player1 = 0;
    int column_player2 = 0;

    int diagonal_player1 = 0;
    int diagonal_player2 = 0;

    boolean gameOver = false;

    int[][] GAME_BOARD = new int[3][3];

    public ServerThread(Socket client, ArrayList<Socket> clientList, String clientName) {
        this.client = client;
        this.clientName = clientName;
        this.clientList = clientList;
    }

    @Override
    public void run() {
        System.out.println("Connect from client " + clientName);
        try {
            DataInputStream in = new DataInputStream(client.getInputStream());
            String message = "";

            while (!message.equals("bye")) {
                message = in.readUTF();

                var sToken = new StringTokenizer(message);

                int xVal = Integer.parseInt(sToken.nextToken());
                int yVal = Integer.parseInt(sToken.nextToken());

                System.out.println(clientName + ": " + message);
                for (Socket otherClient : clientList) {
                    if (otherClient != this.client) {
                        DataOutputStream out = new DataOutputStream(otherClient.getOutputStream());
                        out.writeUTF(clientName + " said: " + message);
                    }
                }
                // game loop
                ticTacToe(xVal, yVal, this.clientName);
                isWin();

            }
            in.close();
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Game session
    private void ticTacToe(int x, int y, String clientName) {
        System.out.println("User: " + clientName);
        if (clientName.equals("Client 1"))
            GAME_BOARD[x][y] = PLAYER_1;
        else
            GAME_BOARD[x][y] = PLAYER_2;
    }

    private void isWin() {
        rowCheck(GAME_BOARD);
        columnCheck(GAME_BOARD);
        diagonalCheck(GAME_BOARD);
    }

    private void rowCheck(int[][] GAME_BOARD) {
        // row check
        for (int i = 0; i < 3; i++) {
            if (GAME_BOARD[i][0] == 1)
                row_player1++;
            if (GAME_BOARD[i][0] == 2)
                row_player2++;
        }

        if (row_player1 == 3) {
            System.out.println("PLAYER 1 WIN");
            reset();
        }

        if (row_player2 == 3) {
            System.out.println("PLAYER 2 WIN");
            reset();
        }

    }

    private void columnCheck(int[][] GAME_BOARD) {
        for (int i = 0; i < 3; i++) {
            if (GAME_BOARD[0][i] == 1) {
                column_player1++;
                if (column_player1 == 3) {
                    System.out.println("PLAYER 1 WIN");
                    reset();
                }
            }
            if (GAME_BOARD[0][i] == 2) {
                column_player2++;
                if (column_player2 == 3) {
                    System.out.println("PLAYER 2 WIN");
                    reset();
                }
            }
        }

    }

    private void diagonalCheck(int[][] GAME_BOARD) {
        // left
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j && GAME_BOARD[i][j] == 1)
                    diagonal_player1++;
                if (i == j && GAME_BOARD[i][j] == 2)
                    diagonal_player2++;
            }
        }

        // right
        for (int i = 0; i < 3; i++) {
            for (int j = 2; j >= 0; j--) {
                if (i == 0 && j == 2 && GAME_BOARD[i][j] == 1)
                    diagonal_player1++;
                if (i == 1 && j == 1 && GAME_BOARD[i][j] == 1)
                    diagonal_player1++;
                if (i == 2 && j == 0 && GAME_BOARD[i][j] == 1)
                    diagonal_player1++;

                if (i == 0 && j == 2 && GAME_BOARD[i][j] == 2)
                    diagonal_player2++;
                if (i == 1 && j == 1 && GAME_BOARD[i][j] == 2)
                    diagonal_player2++;
                if (i == 2 && j == 0 && GAME_BOARD[i][j] == 2)
                    diagonal_player2++;
            }
        }

        if (diagonal_player1 == 3) {
            System.out.println("PLAYER 1 WIN");
            reset();
        }

        if (diagonal_player2 == 3) {
            System.out.println("PLAYER 2 WIN");
            reset();
        }
    }

    private void reset() {
        row_player1 = 0;
        row_player2 = 0;
    
        column_player1 = 0;
        column_player2 = 0;
    
        diagonal_player1 = 0;
        diagonal_player2 = 0;
    }
}
