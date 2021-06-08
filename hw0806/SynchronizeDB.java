import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SynchronizeDB {
    public static void migrate() throws SQLException {
        Connection connA = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db1", "root", "WillieDaSpidie720");
        Connection connB = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db2", "root", "WillieDaSpidie720");

        PreparedStatement stmA = connA.prepareStatement("select * from table1 where 1=1");
        PreparedStatement stmB = connB.prepareStatement("insert into table2 values(?,?,?)");

        ResultSet rs = stmA.executeQuery();
        while (rs.next()) {
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                stmB.setObject(i + 1, rs.getObject(i + 1));
            }
            stmB.executeUpdate();
        }

        connA.close();
        connB.close();
    }
}