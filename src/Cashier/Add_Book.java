package Cashier;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import Main.*;
class AddBook extends Base implements ActionListener, ItemListener 
{
	public JFrame frm;
	public PreparedStatement ps;
	public ResultSet rs;
	public Connection conn;
	public JLabel lblID, lblName, lblHeading, lblTitle, barlabel, lblAuthor, lblNoOfBook, lblPublication, lblTotalPrice, lblPrice;
	public JTextField txtid, txtname, txtPrice, txtdosremarks, txtTotalPrice, txtcompny;
	public JComboBox<String> cmbName, cmbNoOfBook, cmbPublication, cmbAuthor;
	public JButton btnADD, btndrugs, btnupdate, btncancle, btnPrice;
	String quantity[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
	String addbook, price, noofbook;
	int totalprice;
	public MaskFormatter mf;
	Font f = new Font("consolas", Font.BOLD, 14);
	boolean flag;

	AddBook()
	{
		frm = new JFrame("BOOK");
		frm.setSize(370, 440);
		frm.setLayout(null);
		frm.setResizable(false);
		frm.getContentPane().setBackground(Color.decode("#c1fff0"));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int framewidth = frm.getSize().width;
		int frameheigth = frm.getSize().height;
		int framelocationX = (dim.width - framewidth) / 2;
		int framelocationY = (dim.height - frameheigth) / 2;
		frm.setLocation(framelocationX, framelocationY);

		frm.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png")));
		
		lblHeading = new JLabel("Book Shop Management");
		lblHeading.setFont(new Font("Algerian", Font.BOLD, 18));
		lblHeading.setForeground(Color.WHITE);
		lblHeading.setBounds(70, 12, 370, 20);
		frm.add(lblHeading);

		Canvas canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setForeground(Color.RED);
		canvas.setBounds(70, 33, 220, 2);
		frm.add(canvas);

		lblTitle = new JLabel("Book");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Magneto", Font.BOLD, 17));
		lblTitle.setBounds(160, 40, 204, 27);
		frm.add(lblTitle);

		barlabel = new JLabel();
		Image userimg = new ImageIcon(this.getClass().getResource("/images/emp_bar.png")).getImage();
		barlabel.setIcon(new ImageIcon(userimg));
		barlabel.setBounds(0, 0, 370, 70);
		frm.add(barlabel);

		lblName = new JLabel("BOOK NAME");
		lblName.setBounds(20, 80, 110, 30);
		frm.add(lblName);
		lblName.setFont(f);
		lblName.setForeground(Color.blue);

		cmbName = new JComboBox<String>();
		cmbName.setBounds(140, 80, 200, 30);
		cmbName.setBackground(Color.WHITE);
		cmbName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbName.setFont(f);
		frm.add(cmbName);

		lblAuthor = new JLabel("AUTHOR NAME");
		lblAuthor.setBounds(20, 120, 150, 30);
		frm.add(lblAuthor);
		lblAuthor.setFont(f);
		lblAuthor.setForeground(Color.blue);

		cmbAuthor = new JComboBox<String>();
		cmbAuthor.setBounds(140, 120, 200, 30);
		cmbAuthor.setBackground(Color.WHITE);
		frm.add(cmbAuthor);
		cmbAuthor.setFont(f);

		lblPublication = new JLabel("PUBLICATION");
		lblPublication.setBounds(20, 160, 150, 30);
		frm.add(lblPublication);
		lblPublication.setFont(f);
		lblPublication.setForeground(Color.blue);
		
		cmbPublication = new JComboBox<String>();
		cmbPublication.setBounds(140, 160, 200, 30);
		cmbPublication.setFont(f);
		cmbPublication.setBackground(Color.WHITE);
		frm.add(cmbPublication);
		
		btnPrice = new JButton("SHOW PRICE");
		btnPrice.setBounds(140, 200, 200, 30);
		btnPrice.setBackground(Color.WHITE);
		btnPrice.addActionListener(this);
		frm.add(btnPrice);
		
		lblPrice = new JLabel("PRICE");
		lblPrice.setBounds(20, 240, 80, 30);
		frm.add(lblPrice);
		lblPrice.setFont(f);
		lblPrice.setForeground(Color.blue);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(140, 240, 200, 30);
		txtPrice.setBackground(Color.WHITE);
		txtPrice.setEnabled(false);
		txtPrice.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtPrice);
		price = txtPrice.getText();
		txtPrice.setFont(f);
		
		lblNoOfBook = new JLabel("NO OF BOOK");
		lblNoOfBook.setBounds(20, 280, 80, 30);
		frm.add(lblNoOfBook);
		lblNoOfBook.setFont(f);
		lblNoOfBook.setForeground(Color.blue);

		cmbNoOfBook = new JComboBox<String>(quantity);
		cmbNoOfBook.setBounds(140, 280, 200, 30);
		cmbNoOfBook.setBackground(Color.WHITE);
		cmbNoOfBook.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		noofbook = cmbNoOfBook.getSelectedItem().toString();
		frm.add(cmbNoOfBook);
		cmbNoOfBook.addItemListener(this);
		cmbNoOfBook.setFont(f);

		lblTotalPrice = new JLabel("TOTAL PRICE");
		lblTotalPrice.setBounds(20, 320, 120, 30);
		frm.add(lblTotalPrice);
		lblTotalPrice.setFont(f);
		lblTotalPrice.setForeground(Color.blue);
		
		txtTotalPrice = new JTextField();
		txtTotalPrice.setBounds(140, 320, 200, 30);
		txtTotalPrice.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtTotalPrice);
		txtTotalPrice.setFont(f);

		btnADD = new JButton("ADD");
		btnADD.setBounds(10, 360, 160, 35);
		btnADD.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(btnADD);
		btnADD.addActionListener(this);
		btnADD.setBackground(Color.decode("#88d2f6"));

		btncancle = new JButton("CANCEL");
		btncancle.setBounds(185, 360, 160, 35);
		btncancle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(btncancle);
		btncancle.addActionListener(this);
		btncancle.setBackground(Color.decode("#88d2f6"));
		disabled();
		frm.setVisible(true);
	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource().equals(btnADD)) 
		{
			addbook = "    "+cmbName.getSelectedItem().toString() +"\t\t"+cmbAuthor.getSelectedItem().toString() +"\t" +txtPrice.getText().toUpperCase()+"\t"+ cmbNoOfBook.getSelectedItem().toString() + "\t" +txtTotalPrice.getText().toUpperCase() + "\n";
			super.Book = addbook;
			String temp = txtTotalPrice.getText();	
			super.tprice = Integer.parseInt(temp);
			frm.dispose();
		} 
		else if (e.getSource().equals(btncancle))
		{
			frm.dispose();
		}
		else if(e.getSource().equals(btnPrice))
		{
			price(cmbName.getSelectedItem().toString(),cmbAuthor.getSelectedItem().toString(),cmbPublication.getSelectedItem().toString());
		}
	}
	
	public void price(String name, String author, String publisher)
	{
		String query = "select PRICE from BOOK_DETAILS WHERE NAME='" + name + "' AND AUTHOR='" + author + "' AND PUBLICATION = '" + publisher + "'";
		conn = Connections.getConnections();
		try 
		{
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next())
			{
				txtPrice.setText(rs.getString(1));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		txtPrice.getText();
	}
	
	public void calculatePrice(String prices, String noofbook)
	{
		int price = Integer.parseInt(prices);
		int no_of_book = Integer.parseInt(noofbook);
		int totalprice = price*no_of_book;
		String totalPrice = Integer.toString(totalprice);
		txtTotalPrice.setText(totalPrice);
	}
	
	public void srccour()
	{
		String sqlqry = "select NAME from BOOK_DETAILS";
		conn = Connections.getConnections();
		try 
		{
			ps = conn.prepareStatement(sqlqry);
			rs = ps.executeQuery();
			while (rs.next()) 
			{
				cmbName.addItem(rs.getString(1));
				cmbName.setSelectedIndex(-1);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		String Author_sqlqry = "select NAME from AUTHOR_DETAILS";
		try 
		{
			ps = conn.prepareStatement(Author_sqlqry);
			rs = ps.executeQuery();
			while (rs.next()) 
			{
				cmbAuthor.addItem(rs.getString(1));
				cmbAuthor.setSelectedIndex(-1);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		String Pubs_sqlqry = "select NAME from PUBLISHER_DETAILS";
		try 
		{
			ps = conn.prepareStatement(Pubs_sqlqry);
			rs = ps.executeQuery();
			while (rs.next()) 
			{
				cmbPublication.addItem(rs.getString(1));
				cmbPublication.setSelectedIndex(-1);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void enabled()
	{
		cmbName.setEnabled(true);
		cmbAuthor.setEnabled(true);
		cmbPublication.setEnabled(true);
		cmbNoOfBook.setEnabled(true);
		srccour();
	}
	public void disabled()
	{
		cmbName.setEnabled(false);
		cmbAuthor.setEnabled(false);
		cmbPublication.setEnabled(false);
		cmbNoOfBook.setEnabled(false);
	}

	@Override
	public void itemStateChanged(ItemEvent evt) 
	{
		if(evt.getSource().equals(cmbNoOfBook))
		{
			calculatePrice(txtPrice.getText(),cmbNoOfBook.getSelectedItem().toString());
		}
		
	}

}
public class Add_Book 
{
	//public static void main(String args[])
	//{
		AddBook adbook = new AddBook();
		//adbook.enabled();
	//}
}
