package ClientSocket.serverSite;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Writer {
    public static void write(String jsonString) throws IOException {
        File book = new File("ClientSocket/serverSite/book.json");
        if (book.createNewFile()) {
            System.out.println("File created: " + book.getName());
        } else {
            System.out.println("File already exists.");
        }

        var printer = new PrintWriter("ClientSocket/serverSite/book.json");
        printer.write(jsonString);

        printer.flush();
        printer.close();
    }
}
