package ClientSocket.clientSide;

import org.json.simple.JSONObject;

public class Reader {
    public static String read(Book book) {
        var bookObj = new JSONObject();

        bookObj.put("title",book.getTitle());
        bookObj.put("publisher",book.getPublisher());

        Author author = book.getAuthor();
        var authorObj = new JSONObject();

        authorObj.put("name",author.getName());
        authorObj.put("age",author.getAge());

        bookObj.put("author",authorObj);

        String jsonString = bookObj.toJSONString();

        return jsonString;
    }
}
