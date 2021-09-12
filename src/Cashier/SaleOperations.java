package Cashier;
import java.sql.ResultSet;
import java.sql.SQLException;
import Main.*;
public class SaleOperations extends Base 
{
	int status = 0;
	String sqlquery, sqlqury, id, psid, eid;
	Boolean flag = true;

	public SaleOperations() 
	{

	}

	public SaleOperations(String id)
	{
		super.Id = id;
	}

	public SaleOperations(String Date, String Id, String cust_name, String mobile_no, String Address,String Book, String Total_Price, String SaleBy) 
	{
		try 
		{
			super.Date = Date;
			super.Id = Id;
			super.CustName = cust_name;
			super.Mobile_No = mobile_no;
			super.Address = Address;
			super.book_name = Book;
			super.Total_Price = Total_Price;
			super.SaleBy = SaleBy;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

	}

	public ResultSet sec_recordforApp(String id)
	{
		String Sql = "select APPOINTMENT.REG_NO,APPOINTMENT.OWNER_NAME,APPOINTMENT.PET_NAME,APPOINTMENT.AGE,ANIMAL_DETAILS.BREED,APPOINTMENT.HEIGHT,APPOINTMENT.WEIGHT,APPOINTMENT.PROBLEM from APPOINTMENT INNER JOIN ANIMAL_DETAILS ON APPOINTMENT.REG_NO=ANIMAL_DETAILS.REG_NO WHERE APPOINTMENT_NO=?";
		conn = Connections.getConnections();
		try 
		{
			ps = conn.prepareStatement(Sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return rs;
	}

	public int sale_saverecord()
	{
		sqlquery = "insert into sale_details(SALE_DATE,ID,CUSTOMER,MOBILE_NO,ADDRESS,BOOK,TOTAL_PRICE,SALEBY) values('"+ super.Date + "','" + super.Id + "','" + super.CustName + "','" + super.Mobile_No + "','"+ super.Address + "','" + super.book_name + "','" + super.Total_Price + "','"+ super.SaleBy + "')";
		super.conn = Connections.getConnections();
		int status = 0;
		try 
		{
			super.ps = super.conn.prepareStatement(sqlquery);
			status = ps.executeUpdate();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return status;
	}
	public Boolean cust_userValidate()
	{
		sqlquery = "select ID from SALE_DETAILS";
		super.conn = Connections.getConnections();
		try 
		{
			super.ps = super.conn.prepareStatement(sqlquery);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		try
		{
			super.rs = super.ps.executeQuery();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		try
		{
			while (rs.next())
			{
				if (super.Id.equals(super.rs.getString(1)))
				{
					flag = false;
				}
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return flag;
	}

	public String cust_getid()
	{
		String sql = "select ID from SALE_DETAILS";
		conn = Connections.getConnections();
		id = "CUSTs-0";
		try 
		{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) 
			{
				id = rs.getString(1);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{
				conn.close();
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		id = "CUST-" + Integer.toString(Integer.parseInt(id.substring(5, id.length())) + 1);
		return id;
	}
}
