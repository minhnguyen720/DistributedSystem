package hw0406;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GradeManagement {
    public static void addStudentToTable(Student student) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb", "root",
                    "WillieDaSpidie720");
            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO student values(" + student.getId() + "," + "\"" + student.getName() + "\""
                    + "," + student.getGrade() + ")");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Student getStudentById(int id) {
        Student student = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb", "root",
                    "WillieDaSpidie720");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM student WHERE id=" + id);
            while (rs.next()) {
                student = new Student(rs.getInt(1), rs.getString(2), rs.getDouble(3));
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return student;
    }
}
