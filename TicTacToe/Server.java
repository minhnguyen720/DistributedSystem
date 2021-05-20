import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    public static void main(String[] args) {
        final int PORT = 1099;

        try {
            // Create stub to store in RMI server
            ServerService stub = (ServerService) UnicastRemoteObject.exportObject(new ServerImp(), PORT);

            //  Register stub to RMI server
            Registry res = LocateRegistry.createRegistry(PORT);
            // ServerService will be the name in RMI server when client will lookup
            res.bind("ServerService", stub);

            System.out.println("RMI server is ready to use");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
