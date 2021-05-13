public class Book extends Media {
    public String name;
    public String author;

    public Book(String name,String author) {
        super(name);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }
}
