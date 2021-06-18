package ClientSocket.serverSite;

public class Author {
    private String name;
    private long age;

    public Author(String name, long age) {
        this.name = name;
        this.age = age;
    }

    public long getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}
