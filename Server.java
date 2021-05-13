import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Server {
    private static String DB_NAME = "";
    private static String DB_URL = "jdbc:mysql://localhost:3306/";
    private static String USER_NAME = "root";
    private static String PASSWORD = "WillieDaSpidie720";

    private static Connection connection;

    public static void main(String[] args) {
        int SERVER_PORT = 8000;
        ServerSocket serverSocket = null;

        int bookCounter = 0;
        int newspaperCounter = 0;
        int mediaCounter = 0;

        InitDatabaseConnection("book");
        InitDatabaseConnection("newspaper");

        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Server created");

            while (true) {
                Socket client = serverSocket.accept();
                System.out.println("Just connected to " + client.getRemoteSocketAddress());

                var in = new DataInputStream(client.getInputStream());
                var objIn = new ObjectInputStream(client.getInputStream());
                var out = new DataOutputStream(client.getOutputStream());

                String message = "";
                message = in.readUTF();
                System.out.println("Client says: " + message);

                switch (message) {
                    case "book":
                        Book BOOK = (Book) objIn.readObject();
                        System.out.println(BOOK);
                        String bookQuery = "INSERT INTO book (name, author) VALUE ('" + BOOK.getName() + "', '"
                                + BOOK.getAuthor() + "')";
                        Statement bookStatement = connection.createStatement();
                        bookStatement.executeUpdate(bookQuery);
                        bookCounter++;
                        mediaCounter++;
                        System.out.println("Successful !");
                        break;

                    case "newspaper":
                        Newspaper NEWSPAPER = (Newspaper) objIn.readObject();
                        System.out.println(NEWSPAPER);
                        String newspaperQuery = "INSERT INTO book (name, type) VALUE ('" + NEWSPAPER.getName() + "', '"
                                + NEWSPAPER.getType() + "')";
                        Statement newspaperStatement = connection.createStatement();
                        newspaperStatement.executeUpdate(newspaperQuery);
                        newspaperCounter++;
                        mediaCounter++;
                        System.out.println("Successful !");
                        break;

                    case "getNumberNewspaper":
                        out.writeInt(newspaperCounter);
                        System.out.println("Successful !");
                        break;

                    case "getNumberBook":
                        out.writeInt(bookCounter);
                        System.out.println("Successful !");
                        break;

                    case "getNumberMedia":
                        out.writeInt(mediaCounter);
                        System.out.println("Successful !");
                        break;
                        
                    default:
                        System.exit(1);
                }
                client.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void InitDatabaseConnection(String name) {
        Connection connection = null;
        DB_NAME = name;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL + DB_NAME, USER_NAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (connection != null) {
            Server.connection = connection;
            System.out.println("Database connection created !");
        }
    }
}