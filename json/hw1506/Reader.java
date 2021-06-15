package hw1506;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Reader {
    private JSONObject object;
    private String path;

    public Reader(JSONObject object, String path) {
        this.object = object;
        this.path = path;
    }

    public void read(Customer customer) throws IOException, ParseException {
        var fileReader = new FileReader(path);
        object = (JSONObject) new JSONParser().parse(fileReader);

        // get data
        long id = (long) object.get("id");
        String name = (String) object.get("name");
        long age = (long) object.get("age");

        System.out.println("---- CUSTOMER ----");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);

        // get accounts
        JSONArray array = (JSONArray) object.get("accounts");
        Iterator accountItr = array.iterator();

        System.out.println("---- CUSTOMER'S ACCOUNT ----");
        while(accountItr.hasNext()) {
            Map map = (Map) accountItr.next();
            
            long accountId = (long) map.get("accountId");
            long balance = (long) map.get("balance");

            System.out.println("Account ID: " + accountId);
            System.out.println("Balance: " + balance + "\n");
        }
    }
}
