package Main;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateForm 
{
    public static boolean validateEmail(String email) 
    {
		Pattern pat = Pattern.compile("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");
		Matcher mat = pat.matcher(email);
		if (mat.matches()) 
		{
			return true;
		}
		return false;
	}

	public static boolean validateMobileNo(String mobno)
	{
		Pattern pat = Pattern.compile("\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d");
		Matcher mat = pat.matcher(mobno);
		if (mat.matches()) 
		{
			return true;
		}
		return false;
	}

	public static boolean validateAnyNo(String anyno)
	{
		Pattern pat = Pattern.compile("\\d+");
		Matcher mat = pat.matcher(anyno);
		if (mat.matches())
		{
			return true;
		}
		return false;
	}

	public static boolean validateAnyString(String anyString)
	{
		Pattern pat = Pattern.compile("[a-zA-Z\\s]+");
		Matcher mat = pat.matcher(anyString);
		if (mat.matches())
		{
			return true;
		}
		return false;
	}

	public static boolean validateheiWei(String pan) 
	{
		Pattern pat = Pattern.compile("[d\\a-zA-Z]+");
		Matcher mat = pat.matcher(pan);
		if (mat.matches())
		{
			return true;
		}
		return false;
	}

	public static boolean validateDate(String date) 
	{
		Pattern pat = Pattern.compile("[a-zA-Z\\]+");
		Matcher mat = pat.matcher(date);
		if (mat.matches()) 
		{
			return true;
		}
		return false;
	}
}
