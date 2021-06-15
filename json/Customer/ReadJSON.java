package Customer;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class ReadJSON {
    private JSONObject object;

    public ReadJSON(JSONObject object) {
        this.object = object;
    }

    public void read(Customer customer, List<String> list) {
        // JSON Object
        // var file = new FileReader("Customer/customer.json");
        // JSONObject jsonObject = (JSONObject) new JSONParser().parse(file);

        // get data
        long id = (long) object.get("id");
        long age = (long) object.get("age");

        System.out.println(id);
        System.out.println(age);

        // get names
        var jsonArray = (JSONArray) object.get("names");
        var nameItr = jsonArray.iterator();
        while (nameItr.hasNext()) {
            var item = (String) nameItr.next();
            System.out.println(item);
        }
    }
}
