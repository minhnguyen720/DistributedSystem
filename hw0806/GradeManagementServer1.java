package hw0806;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GradeManagementServer1 {

    private static int id;

    public static void addStudentToTable(Student student) {
        try {
            id = student.getId();

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db1", "root",
                    "WillieDaSpidie720");
            Statement st = con.createStatement();

            // check duplicate id
            if (getStudentById(id) == null)
                st.executeUpdate("INSERT INTO table1 values(" + student.getId() + "," + "\"" + student.getName() + "\""
                        + "," + student.getGrade() + ")");
            else {
                st.executeUpdate("DELETE FROM `db1`.`table1` WHERE (`id`= '" + id + "');");
                st.executeUpdate("INSERT INTO table1 values(" + student.getId() + "," + "\"" + student.getName() + "\""
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
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db1", "root",
                    "WillieDaSpidie720");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM table1 WHERE id=" + id);
            while (rs.next()) {
                student = new Student(rs.getInt(1), rs.getString(2), rs.getFloat(3));
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return student;
    }
}
