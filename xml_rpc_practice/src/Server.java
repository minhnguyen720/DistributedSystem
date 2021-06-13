import org.apache.xmlrpc.WebServer;

public class Server {
    private static final int port = 2000;

    public static void main(String[] args) {
        WebServer webServer = new WebServer(port);
        webServer.addHandler("server", new Server());
        webServer.start();

        System.out.println("Server is running on port " + port);
    }

    public int sum(int x, int y) {
        return x + y;
    }

    public int minus(int x, int y) {
        return x - y;
    }

    public int multiply(int x, int y) {
        return x * y;
    }

    public int divide(int x, int y) {
        return x / y;
    }
}
