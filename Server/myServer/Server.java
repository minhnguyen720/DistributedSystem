import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Server {
    private static String DB_NAME = "mydb";
    private static String DB_URL = "jdbc:mysql://localhost:3306/";
    private static String USER_NAME = "root";
    private static String PASSWORD = "WillieDaSpidie720";
    
    private static Connection connection;
    private static boolean STATUS = true;
    private static ServerSocket SERVER_SOCKET;

    private static void DBConnect() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL + DB_NAME, USER_NAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (connection != null) {
            Server.connection = connection;
            System.out.println("Success");
        }

    }

    private static void initServer(int port) {
        try {
            SERVER_SOCKET = new ServerSocket(port);
            System.out.println("Server created");

            while (STATUS) {
                Socket CLIENT_SOCKET = SERVER_SOCKET.accept();
                var in = new ObjectInputStream(CLIENT_SOCKET.getInputStream());

                Student STUDENT = (Student) in.readObject();
                System.out.println(STUDENT);

                String query = "INSERT INTO student (name, id, year) VALUE ('" + STUDENT.getName() + "', '"
                        + STUDENT.getId() + "', '" + STUDENT.getYear() + "')";
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);

                CLIENT_SOCKET.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        DBConnect();
        initServer(2000);
    }
}
