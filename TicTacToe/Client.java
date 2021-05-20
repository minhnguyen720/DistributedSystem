import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        final int PORT = 1099;

        try {
            String LOCAL_HOST = "127.0.0.1";

            Registry res = LocateRegistry.getRegistry(LOCAL_HOST, PORT);
            ServerService stub = (ServerService) res.lookup("ServerService");

            login(stub);

            String clientSymbol = stub.getClientToken();
            System.out.println(clientSymbol);

            while (stub.isWin() == null) {
                System.out.print("Player " + clientSymbol + " choose your move[1 - 3]: ");
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                if (!stub.chooseMove(x, y, clientSymbol)) {
                    System.out.println("Invalid Action");
                    continue;
                }

                System.out.println(stub.display());
            }
            if (stub.isWin().equals("Draw"))
                System.out.println("Draw. Game Over");
            else
                System.out.println("Player: " + stub.isWin() + " win!");

            scanner.close();

        } catch (

        Exception e) {
            e.printStackTrace();
        }
    }

    private static void login(ServerService stub) {
        System.out.print("Input username: ");
        String username = scanner.nextLine();
        System.out.print("Input password: ");
        String password = scanner.nextLine();
        try {
            if (!stub.authentication(username, password)) {
                System.out.println("you don't have account");

                System.out.println("=========Creating new account=========");
                System.out.print("Input username: ");
                String user = scanner.nextLine();
                System.out.print("Input password: ");
                String pass = scanner.nextLine();
                if (!stub.createAccount(user, pass)) {
                    System.out.println("Account exist. Try again");
                }
                System.out.println("Account created. Please login again");
                scanner.close();
                return;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
