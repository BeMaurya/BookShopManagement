package Main;
import java.sql.*;

public class Connections 
{
    public static Connection getConnections()
    {
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String username = "book_shop";
		String password = "book";
		Connection conn = null;
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, username, password);
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return conn;
	}
}
