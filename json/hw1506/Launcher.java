package hw1506;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class Launcher {
    public static void main(String[] args) throws IOException, ParseException {
        String path = "hw1506/customer.json";
        List<Account> accounts = new ArrayList<>();
        var object = new JSONObject();

        // create customer
        var willie = new Customer(1, "willie", 21, accounts);
        willie.addAccount(new Account(1234,10000));
        willie.addAccount(new Account(2345, 2000));
        
        // writing json
        var writer = new Writer(object,path);
        writer.write(willie);

        // reading json
        var reader = new Reader(object, path);
        reader.read(willie);
    }
}
