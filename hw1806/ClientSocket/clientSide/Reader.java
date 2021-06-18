package ClientSocket.clientSide;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class Reader {
    public static String read() throws IOException, ParseException {
        // JSON object
        var fileReader = new FileReader("ClientSocket/clientSide/Book.json");
        JSONObject jsonObj = (JSONObject) new JSONParser().parse(fileReader);

        // get data
        String title = (String) jsonObj.get("title");
        String publisher = (String) jsonObj.get("publisher");

        System.out.println("---- BOOK ----");
        System.out.println("title: " + title);
        System.out.println("publisher: " + publisher);

        JSONArray jsonArr = (JSONArray) jsonObj.get("author");
        Iterator itr = jsonArr.iterator();

        while(itr.hasNext()) {
            Map map = (Map) itr.next();

            String name = (String) map.get("name");
            long age = (long) map.get("age");
            System.out.println("Name: " + name);
            System.out.println("Age: " + age);
        }

        String jsonString = jsonObj.toJSONString();
        return jsonString;
    }

    public static void main(String[] args) {

    }
}
