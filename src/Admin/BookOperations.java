package Admin;
import java.sql.ResultSet;
import java.sql.SQLException;
import Main.*;
public class BookOperations extends Base 
{
	int status = 0;
	String sqlquery, sqlqury, id;
	Boolean flag = true;

	public BookOperations()
	{

	}

	public BookOperations(String Id) 
	{
		super.Id = Id;
	}

	public BookOperations(String Id, String Name) 
	{
		super.Id = Id;
		super.Name = Name;
	}

	public BookOperations(String Id, String Name, String Author, String Publisher, String Type, String Stock, String Price)
	{
		super.Id = Id;
		super.Name = Name;
		super.Author = Author;
		super.Publisher = Publisher;
		super.Type = Type;
		super.Stock = Stock;
		super.Price = Price;
	}

	public ResultSet sec_recordforApp(String id) 
	{
		String Sql = "select AUTHOR,PUBLICATION,PRICE from BOOK_DETAILS where NAME=?";
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
	
	public Boolean Book_userValidate()
	{
		sqlquery = "select ID from BOOK_DETAILS";
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

	public int Book_saveRecord() 
	{
		sqlquery = "insert into BOOK_DETAILS(ID,NAME,AUTHOR,PUBLICATION,TYPE,STOCK,PRICE) values('" + super.Id + "','" + super.Name + "','" + super.Author + "','" + super.Publisher + "','" + super.Type + "','" + super.Stock + "','" + super.Price + "')";
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
	
	public String Book_getid()
	{
		String sql = "select ID from BOOK_DETAILS";
		conn = Connections.getConnections();
		id = "BOOK-0";
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
		id = "BOOK-" + Integer.toString(Integer.parseInt(id.substring(5, id.length())) + 1);
		return id;
	}

	public int Book_update()
	{
		sqlquery = "update BOOK_DETAILS set NAME='" + super.Name.toUpperCase() + "',AUTHOR='" + super.Author.toUpperCase() + "', PUBLICATION='" + super.Publisher + "', TYPE='" + super.Type + "', STOCK = '" + super.Stock + "',PRICE = '" + super.Price + "' where ID='" + super.Id + "'";
		super.conn = Connections.getConnections();
		try
		{
			super.ps = conn.prepareStatement(sqlquery);
			status = ps.executeUpdate();
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
		return status;
	}

	public int Book_delete() 
	{
		sqlquery = "delete from BOOK_DETAILS where ID='" + super.Id + "'";
		super.conn = Connections.getConnections();
		try 
		{
			super.ps = conn.prepareStatement(sqlquery);
			status = ps.executeUpdate();
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
		return status;
	}

	public Boolean Author_userValidate()
	{
		sqlquery = "select ID from AUTHOR_DETAILS";
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

	public int Author_saveRecord()
	{
		sqlquery = "insert into AUTHOR_DETAILS(ID,NAME) values('" + super.Id + "','"+ super.Name + "')";
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

	public String Author_getId() 
	{
		String sql = "select ID from AUTHOR_DETAILS";
		conn = Connections.getConnections();
		id = "ATH-0";
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
		id = "ATH-" + Integer.toString(Integer.parseInt(id.substring(4, id.length())) + 1);
		return id;
	}

	public int Author_update() 
	{
		sqlquery = "update AUTHOR_DETAILS set NAME='" + super.Name.toUpperCase() + "' where ID='" + super.Id + "'";
		super.conn = Connections.getConnections();
		try 
		{
			super.ps = conn.prepareStatement(sqlquery);
			status = ps.executeUpdate();
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
		return status;
	}

	public int Author_delete()
	{
		sqlquery = "delete from AUTHOR_DETAILS where ID='" + super.Id + "'";
		super.conn = Connections.getConnections();
		try 
		{
			super.ps = conn.prepareStatement(sqlquery);
			status = ps.executeUpdate();
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
		return status;
	}

	//////////////////////////////////

	public Boolean Publisher_userValidate() 
	{
		sqlquery = "select ID from PUBLISHER_DETAILS";
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

	public int Publisher_saveRecord() 
	{
		sqlquery = "insert into PUBLISHER_DETAILS(ID,NAME) values('" + super.Id + "','" + super.Name+ "')";
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

	public String Publisher_getId() 
	{
		String sql = "select ID from PUBLISHER_DETAILS";
		conn = Connections.getConnections();
		id = "PUBS-0";
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		id = "PUBS-" + Integer.toString(Integer.parseInt(id.substring(5, id.length())) + 1);
		return id;
	}

	public int Publisher_update()
	{
		sqlquery = "update PUBLISHER_DETAILS set NAME='" + super.Name.toUpperCase() + "' where ID='"+ super.Id + "'";
		super.conn = Connections.getConnections();
		try 
		{
			super.ps = conn.prepareStatement(sqlquery);
			status = ps.executeUpdate();
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
		return status;
	}

	public int Publisher_delete()
	{
		sqlquery = "delete from PUBLISHER_DETAILS where ID='" + super.Id + "'";
		super.conn = Connections.getConnections();
		try
		{
			super.ps = conn.prepareStatement(sqlquery);
			status = ps.executeUpdate();
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
		return status;
	}
}