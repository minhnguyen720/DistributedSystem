import org.apache.xmlrpc.XmlRpcClient;

import java.util.Scanner;
import java.util.Vector;

public class Client {
    public static void main(String[] args) {
        try {
            String[] options = {"sum","minus","multiply","divide"};
            final String localhost = "http://localhost:2000";

            var client = new XmlRpcClient(localhost);
            var scanner = new Scanner(System.in);

            String option = "";
            while(!(option.equals("5"))) {
                System.out.print("1. plus\n2. minus\n3. divide\n4. multiply\n5. exit\nYour option: ");
                option = scanner.next();
                if(option.equals("5"))
                    break;

                // Integer case
                System.out.println("First number: ");
                int x = scanner.nextInt();
                System.out.println("Second number: ");
                int y = scanner.nextInt();

                Vector<Integer> params = new Vector<>();
                params.addElement(x);
                params.addElement(y);

                // send requested function to server
                Object res = client.execute("server." + options[Integer.parseInt(option) - 1], params);
                System.out.println("Result: " + res);
            }

            scanner.close();
            System.out.println("scanner closed");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
