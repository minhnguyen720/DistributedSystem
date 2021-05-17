import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {

	private boolean makeQueryToDB(String userName,String password) {
		String databaseUrl = "jdbc:mysql://localhost:3306/";
		String databaseUsername = "root";
		String databasePassword = "WillieDaSpidie720";
		String databaseName = "mydb";
		
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(databaseUrl + databaseName, databaseUsername, databasePassword);
			System.out.println("Connect to database");
			PreparedStatement st = connection.prepareStatement(
			"SELECT * FROM user WHERE username = ? AND password = ?"
			);
			
			st.setString(1, userName);
			st.setString(2,password);
			ResultSet res = st.executeQuery();
			
			if(res.next())
				return true;
			return false;
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DO post run");
        String username = req.getParameter("userName");
        String password = req.getParameter("password");
        
        if(makeQueryToDB(username,password)) {
        	resp.addCookie(new Cookie("isLogin", "true"));
        	resp.sendRedirect(req.getContextPath() + "/cal.html");
        } else {
        	resp.sendRedirect(req.getContextPath() + "/cal.html");
        }
    }

}
