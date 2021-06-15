package hw1506;

import java.util.List;

public class Customer {
    private int id;
    private String name;
    private int age;
    private List<Account> accounts;

    public Customer(int id, String name, int age, List<Account> accounts) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.accounts = accounts;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public Account getAccount(int index) {
        return accounts.get(index);
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }
}
