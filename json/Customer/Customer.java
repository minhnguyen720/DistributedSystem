package Customer;

import java.util.List;

public class Customer {
    private long id;
    private List<String> names;
    private long age;

    public Customer(long id, List<String> names, long age) {
        this.id = id;
        this.names = names;
        this.age = age;
    }

    public long getAge() {
        return age;
    }

    public long getId() {
        return id;
    }

    public String getNames(int index) {
        return names.get(index);
    }

    public List<String> getList() {
        return names;
    }

    public void setName(String name) {
        names.add(name);
    }
}