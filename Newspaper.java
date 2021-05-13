public class Newspaper extends Media {
    public String type;

    public Newspaper(String name, String type) {
        super(name);
        this.type = type;
    }

    public String getType() {
        return type;
    }


}
