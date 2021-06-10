package hw0806;

public class Student {
    private final String name;
    private final int id;
    private final float grade;

    public Student(int id, String name, float grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public float getGrade() {
        return grade;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
