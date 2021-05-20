import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerService extends Remote {
    boolean chooseMove(int x, int y, String player) throws RemoteException;

    String isWin() throws RemoteException;

    String display() throws RemoteException;

    String getClientToken() throws RemoteException;

    boolean authentication(String username, String password) throws RemoteException;

    boolean createAccount(String username, String password) throws RemoteException;
}
