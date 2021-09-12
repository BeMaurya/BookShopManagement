package Main;
import java.sql.*;

public abstract class Base 
{
    protected Connection conn;
	protected PreparedStatement ps;
	protected ResultSet rs;
	protected String Id,Name,DOB,Gender,Qualification,Author,Type,NoOfBook,book_name,Publisher,Stock,Price,Description,PrescriptionNo,Prescription,Emailid,Address,UserType,FullName,UserId,Password,Mobile_No,SecurityQuestion,Answer,image,RegDate,RegNo,CustName,Age,State,Date,Time,Total_Price,SaleBy;
	protected static String Book = null;
	protected static int tprice = 0;
}
