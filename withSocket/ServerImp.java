import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerImp implements ServerService {
    private Game game;
    private int PLAYER_ID;
    private int NUMBER_OF_CLIENT = 0;
    private String[] symbol = { "X", "O" };
    private Connection dbConnect;

    public ServerImp() {
        this.dbConnect = connectToDatabase("guest", "password");
        game = new Game();
    }

    public static Connection connectToDatabase(String user, String password) {
        String databaseURL = "jdbc:mysql://localhost:3306/distributed?user=" + user + "&password=" + password;
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(databaseURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public boolean authentication(String user, String password) {
        try {
            PreparedStatement preSt = dbConnect
                    .prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");

            preSt.setString(1, user);
            preSt.setString(2, password);

            ResultSet rs = preSt.executeQuery();

            if (rs.next())
                return true;
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean createAccount(String user, String password) {

        try {
            PreparedStatement prSt = dbConnect.prepareStatement("INSERT INTO users(username,password) VALUE (?,?);");
            prSt.setString(1, user);
            prSt.setString(2, password);
            prSt.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean chooseMove(int x, int y, String playerName) throws RemoteException {
        return game.setMove(x, y, playerName);
    }

    @Override
    public String isWin() throws RemoteException {
        return game.getWinner();
    }

    @Override
    public String display() {
        return game.toString();
    }

    @Override
    public String getClientToken() {
        PLAYER_ID = NUMBER_OF_CLIENT;
        NUMBER_OF_CLIENT++;
        return symbol[PLAYER_ID];
    }

}