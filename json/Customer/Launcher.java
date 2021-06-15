package Customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class Launcher {
    public static void main(String[] args) throws IOException, ParseException {
        // customer init
        List<String> nameList = new ArrayList<>();
        var customer = new Customer(1,nameList,21);
        customer.setName("willie");
        customer.setName("jason");
        
        // write json
        String path = "Customer/customer.json";
        var jsonObject = new JSONObject();
        var jsonWriter = new WriteJSON(jsonObject);
        jsonWriter.write(customer, path);

        // read json
        var jsonReader = new ReadJSON(jsonObject);
        jsonReader.read(customer, nameList);
    }
}
