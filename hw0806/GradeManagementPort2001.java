package hw0806;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GradeManagementPort2001 {
    private static int id;

    public static void addStudentToTable(Student student) {
        try {
            id = student.getId();

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db2", "root",
                    "WillieDaSpidie720");
            Statement st = con.createStatement();
            
            if (getStudentById(id) == null)
                st.executeUpdate("INSERT INTO table2 values(" + student.getId() + "," + "\"" + student.getName() + "\""
                        + "," + student.getGrade() + ")");
            else {
                st.executeUpdate("DELETE FROM `db2`.`table2` WHERE (`id`= '" + id + "');");
                st.executeUpdate("INSERT INTO table2 values(" + student.getId() + "," + "\"" + student.getName() + "\""
                        + "," + student.getGrade() + ")");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Student getStudentById(int id) {
        Student student = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db2", "root",
                    "WillieDaSpidie720");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM table2 WHERE id=" + id);
            while (rs.next()) {
                student = new Student(rs.getInt(1), rs.getString(2), rs.getFloat(3));
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

    public static List<Student> getAllStudents() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db2", "root",
                "WillieDaSpidie720");

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM table2;");
        List<Student> list = new ArrayList<>();
        while(rs.next()) {
            list.add(new Student(rs.getInt(1), rs.getString(2), rs.getFloat(3)));
        }

        return list;
    }
}
