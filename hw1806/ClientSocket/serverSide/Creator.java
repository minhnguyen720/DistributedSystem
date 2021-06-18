package ClientSocket.serverSide;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class Creator {

    public static Book createBook(String jsonString) {
        try {
            JSONObject bookObj = (JSONObject) new JSONParser().parse(jsonString);

            // get title
            String title = (String) bookObj.get("title");
            // get publisher
            String publisher = (String) bookObj.get("publisher");

            // get author
            JSONObject authorObj = (JSONObject) bookObj.get("author");
            String name = (String) authorObj.get("name");
            long age = (long) authorObj.get("age");

            // create book object
            var author = new Author(name,age);
            var book = new Book(title,publisher,author);

            return book;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
