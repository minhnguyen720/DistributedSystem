package Customer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class WriteJSON {
    private JSONObject object;

    public WriteJSON(JSONObject object) {
        this.object = object;
    }

    public void write(Customer customer, String path) throws FileNotFoundException{

        // putting data to JSON Object
        object.put("id", customer.getId());
        object.put("age", customer.getAge());

        // for names
        var jsonArray = new JSONArray();

        for (int i = 0; i < customer.getList().size(); i++) {
            jsonArray.add(customer.getNames(i));
        }
        object.put("names", jsonArray);

        // get jsonString
        String jsonString = object.toJSONString();
        var writer = new PrintWriter(path);
        writer.write(jsonString);

        writer.flush();
        writer.close();
    }
}
