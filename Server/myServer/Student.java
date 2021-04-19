import java.io.Serializable;

public class Student implements Serializable {
    private String id, name, year;

    public Student(String name, String id, String year) {
        this.name = name;
        this.year = year;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    @Override
    public String toString() {
        return id + "|" + name + "|" + year;
    }
}