package hw1506;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Writer {
    private String path;
    private JSONObject object;

    public Writer(JSONObject object, String path) {
        this.object = object;
        this.path = path;
    }

    public void write(Customer customer) throws FileNotFoundException {
        object.put("id", customer.getId());
        object.put("name", customer.getName());
        object.put("age", customer.getAge());

        // JSON array
        var array = new JSONArray();
        Map map = new LinkedHashMap(2);
        for (int i = 0; i < customer.getAccounts().size(); i++) {
            map.put("accountId", customer.getAccount(i).getId());
            map.put("balance", customer.getAccount(i).getBalance());
            array.add(map);
        }
        
        object.put("accounts", array);

        String jsonString = object.toJSONString();
        var printer = new PrintWriter(path);
        printer.write(jsonString);

        printer.flush();
        printer.close();
    }
}
