package Main;
import java.sql.SQLException;

public class LoginOperation extends Base 
{
	boolean flag = false;
	String sqlquery;

	public LoginOperation(String UserId, String Password) 
	{
		super.UserId = UserId;
		super.Password = Password;
	}

	public boolean admin_loginValidate() 
	{
		super.conn = Connections.getConnections();
		sqlquery = "SELECT USERID,PASSWORD FROM ADMIN_DETAILS";
		try 
		{
			super.ps = super.conn.prepareStatement(sqlquery);
		} 
		catch (SQLException e1)
		{
			e1.printStackTrace();
		}
		try 
		{
			super.rs = super.ps.executeQuery();
		} 
		catch (SQLException e2) 
		{
			e2.printStackTrace();
		}
		try 
		{
			while (super.rs.next())
			{
				if (super.rs.getString(1).equals(super.UserId) && super.rs.getString(2).equals(super.Password))
				{
					flag = true;
				}
			}
		}
		catch (SQLException e3)
		{
			e3.printStackTrace();
		}
		return flag;
	}

	// <------CASHIER LOGIN----------->//
	public boolean cash_loginValidate() 
	{
		super.conn = Connections.getConnections();
		sqlquery = "SELECT USERID,PASSWORD FROM EMPLOYEE_DETAILS";
		try 
		{
			super.ps = super.conn.prepareStatement(sqlquery);
		} 
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		try 
		{
			super.rs = super.ps.executeQuery();
		} 
		catch (SQLException e2)
		{
			e2.printStackTrace();
		}
		try
		{
			while (super.rs.next())
			{
				if (super.rs.getString(1).equals(super.UserId) && super.rs.getString(2).equals(super.Password)) 
				{
					flag = true;
				}
			}
		} 
		catch (SQLException e3) 
		{
			e3.printStackTrace();
		}
		return flag;
	}
}

