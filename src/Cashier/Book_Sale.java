package Cashier;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.*;
import Main.*;
class Sale extends Base implements ActionListener, WindowListener 
{
	public static String abc = null, te_price = null;
	public static int temp_price = 0;
	public JFrame frm;
	public PreparedStatement ps;
	public ResultSet rs;
	public Connection conn;
	public JButton btnNew, btnBook, btnVaccin, btnUpdate, btnClear;
	public JLabel lblHeading, barlabel, lblTitle,lblSaleId, lblName,lblTime, lblMobile, lblCustName, lblTotalPrice, lblAddress, lblDate, lblTotalBook, lbllogo;
	public JTextField txtSaleId, txtCustName, txtMobile, txtDate, txtMob_No, txtTotalPrice;
	public JTextArea txtBook, txtAddress;
	public Color hexa1;
	public DefaultTableModel jtable;
	public JTable tabview;
	public boolean start = true;
	public JScrollPane JSBook, JSAddress;
	public static String date;

	Font heading = new Font("Bookman Old Style", Font.BOLD, 18);
	Font f = new Font("Consolas", Font.BOLD, 13);
	
	Sale() 
	{
		frm = new JFrame();
		frm.setSize(730, 600);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int framewidth = frm.getSize().width;
		int frameheight = frm.getSize().height;

		int framelocationX = (dim.width - framewidth) / 2;
		int frmlocationY = (dim.height - frameheight) / 2;

		frm.setLocation(framelocationX, frmlocationY);

		frm.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png")));
		
		frm.setResizable(false);
		Color hexa = Color.decode("#BBF2FA");
		frm.getContentPane().setBackground(hexa);
		frm.setLayout(null);
		frm.setTitle("RECIEPT FORM");
		hexa1 = Color.decode("#82efff");

		lblHeading = new JLabel("Book Shop Management");
		lblHeading.setFont(new Font("Algerian", Font.BOLD, 34));
		lblHeading.setForeground(Color.WHITE);
		lblHeading.setBounds(140, 12, 650, 30);
		frm.add(lblHeading);

		Canvas canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setForeground(Color.RED);
		canvas.setBounds(140, 40, 430, 5);
		frm.add(canvas);

		lblTitle = new JLabel("Generate Reciept");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Magneto", Font.BOLD, 23));
		lblTitle.setBounds(250, 60, 300, 27);
		frm.add(lblTitle);

		barlabel = new JLabel();
		Image userimg = new ImageIcon(this.getClass().getResource("/images/doc_bar.png")).getImage();
		barlabel.setIcon(new ImageIcon(userimg));
		barlabel.setBounds(0, 0, 1260, 105);
		frm.add(barlabel);

		lblName = new JLabel();
		lblName.setBounds(20, 120, 200, 30);
		lblName.setFont(heading);
		lblName.setForeground(Color.WHITE);
		frm.add(lblName);

		String curdate = new SimpleDateFormat("MMM dd, yyyy").format(Calendar.getInstance().getTime());
		String curtime = new SimpleDateFormat("hh:mm a").format(new Date()).toString();

		lblDate = new JLabel();
		lblDate.setBounds(475, 120, 150, 30);
		lblDate.setFont(heading);
		lblDate.setText(curdate);
		lblDate.setForeground(Color.WHITE);
		frm.add(lblDate);

		lblTime = new JLabel();
		lblTime.setBounds(605, 120, 150, 30);
		lblTime.setFont(heading);
		lblTime.setText(curtime);
		lblTime.setForeground(Color.WHITE);
		frm.add(lblTime);

		lblSaleId = new JLabel("SALE ID");
		lblSaleId.setBounds(20, 170, 150, 30);
		lblSaleId.setFont(f);
		lblSaleId.setForeground(Color.BLUE);
		frm.add(lblSaleId);

		txtSaleId = new JTextField();
		txtSaleId.setBounds(150, 170, 200, 30);
		txtSaleId.setFont(f);
		txtSaleId.setBackground(Color.WHITE);
		txtSaleId.setForeground(Color.BLACK);
		txtSaleId.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtSaleId.setEditable(false);
		frm.add(txtSaleId);

		lblCustName = new JLabel("CUSTOMER NAME");
		lblCustName.setBounds(380, 170, 150, 30);
		lblCustName.setFont(f);
		lblCustName.setForeground(Color.BLUE);
		frm.add(lblCustName);

		txtCustName = new JTextField();
		txtCustName.setBounds(500, 170, 200, 30);
		txtCustName.setFont(f);
		txtCustName.setForeground(Color.BLACK);
		txtCustName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtCustName);

		lblMobile = new JLabel("MOBILE NO.");
		lblMobile.setBounds(20, 220, 100, 30);
		lblMobile.setFont(f);
		lblMobile.setForeground(Color.BLUE);
		frm.add(lblMobile);

		txtMobile = new JTextField();
		txtMobile.setBounds(150, 220, 550, 30);
		txtMobile.setFont(f);
		txtMobile.setForeground(Color.BLACK);
		txtMobile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtMobile);

		lblAddress = new JLabel("ADDRESS");
		lblAddress.setBounds(20, 270, 150, 30);
		lblAddress.setFont(f);
		lblAddress.setForeground(Color.BLUE);
		frm.add(lblAddress);
		
		JSAddress = new JScrollPane();
		txtAddress = new JTextArea();
		txtAddress.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		JSAddress.setBounds(150, 270, 550, 80);
		txtAddress.setFont(f);
		txtAddress.setForeground(Color.BLACK);
		JSAddress.setViewportView(txtAddress);
		frm.add(JSAddress);

		lblTotalBook = new JLabel("TOTAL BOOK");
		lblTotalBook.setBounds(20, 370, 100, 30);
		lblTotalBook.setFont(f);
		lblTotalBook.setForeground(Color.BLUE);
		frm.add(lblTotalBook);

		JSBook = new JScrollPane();
		txtBook = new JTextArea();
		txtBook.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		JSBook.setBounds(150, 370, 350, 80);
		txtBook.setFont(f);
		txtBook.setForeground(Color.BLACK);
		JSBook.setViewportView(txtBook);
		frm.add(JSBook);

		btnBook = new JButton("ADD BOOK");
		btnBook.setBounds(510, 370, 190, 80);
		btnBook.setFont(f);
		btnBook.setForeground(Color.BLUE);
		btnBook.setBackground(hexa1);
		btnBook.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(btnBook);
		btnBook.addActionListener(this);

		lblTotalPrice = new JLabel("Total Price");
		lblTotalPrice.setBounds(20, 470, 100, 30);
		lblTotalPrice.setFont(f);
		lblTotalPrice.setForeground(Color.BLUE);
		frm.add(lblTotalPrice);
		
		txtTotalPrice = new JTextField();
		txtTotalPrice.setBounds(150, 470, 550, 30);
		txtTotalPrice.setFont(f);
		txtTotalPrice.setText("0");
		txtTotalPrice.setEnabled(false);
		frm.add(txtTotalPrice);
		
		btnNew = new JButton("NEW");
		btnNew.setBounds(20, 510, 330, 40);
		btnNew.setFont(f);
		btnNew.setForeground(Color.BLUE);
		btnNew.setBackground(hexa1);
		btnNew.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(btnNew);
		btnNew.addActionListener(this);

		btnClear = new JButton("CLEAR");
		btnClear.setBounds(370, 510, 330, 40);
		btnClear.setFont(f);
		btnClear.setForeground(Color.BLUE);
		btnClear.setBackground(hexa1);
		btnClear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(btnClear);
		btnClear.addActionListener(this);

		frm.setVisible(true);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource().equals(btnNew)) 
		{
			String curdate = new SimpleDateFormat("dd/MMM/yyyy").format(Calendar.getInstance().getTime());
			date = curdate;
			if (e.getActionCommand().equalsIgnoreCase("NEW")) 
			{
				btnNew.disable();
				enabled();
				getid();
				btnNew.setText("SAVE");
			}
			else if (e.getActionCommand().equalsIgnoreCase("SAVE"))
			{
				if (frmvalidate()) 
				{
					SaleOperations so = new SaleOperations(date,txtSaleId.getText().toUpperCase(), txtCustName.getText().toUpperCase(),txtMobile.getText(),txtAddress.getText().toUpperCase(),txtBook.getText().toUpperCase(), txtTotalPrice.getText().toUpperCase(),lblName.getText().toUpperCase());
					if (so.cust_userValidate())
					{
						if (so.sale_saverecord() == 1)
						{
							JOptionPane.showMessageDialog(null, "SAVED SUCCESSFULLY");
							GenerateReport gt = new GenerateReport();
							gt.rpt.namearea.append("    NAME : "+txtCustName.getText().toUpperCase()+"\t\t\t      DATE: "+date+"\n    Address : "+txtAddress.getText().toUpperCase()+"\t\t\t Mobile No. : "+txtMobile.getText()+"\n    --------------------------------------------------------------------------------------------------------");
							gt.rpt.textArea1.append(txtBook.getText());
							gt.rpt.pricearea.append("  Total\t\t\t\t\t "+txtTotalPrice.getText());
							frm.dispose();
							//clear();
							//disabled();
							btnNew.setText("NEW");
						}
					} 
					else 
					{
						JOptionPane.showMessageDialog(null, "Record Already Inserted");
					}
				}
			}
		}
		else if (e.getSource().equals(btnClear))
		{
			clearForm();
		} 
		else if (e.getSource().equals(btnBook)) 
		{
			Add_Book book = new Add_Book();
			book.adbook.enabled();
			frm.addWindowListener(this);
		}
	}

	public void clearForm() 
	{
		txtSaleId.setText("");
		txtCustName.setText("");
		txtMobile.setText("");
		txtAddress.setText("");
		txtBook.setText("");
		txtTotalPrice.setText("0");
		disabled();
	}
	
	public void disabled() 
	{
		txtSaleId.setEditable(false);
		txtCustName.setEditable(false);
		txtMobile.setEditable(false);
		txtAddress.setEditable(false);
		txtBook.setEnabled(false);
		txtTotalPrice.setEnabled(false);
		btnNew.setText("NEW");
	}
	
	public void enabled() 
	{
		txtCustName.setEditable(true);
		txtMobile.setEditable(true);
		txtAddress.setEditable(true);
	}

	public boolean frmvalidate()
	{
		boolean flag = true;
		if (txtCustName.getText().length() == 0) 
		{
			JOptionPane.showMessageDialog(null, "Enter Customer Name");
			txtCustName.grabFocus();
			flag = false;
		} 
		else if (txtMobile.getText().length() == 0) 
		{
			JOptionPane.showMessageDialog(null, "Enter Mobile");
			txtMobile.grabFocus();
			flag = false;
		} 
		else if (txtAddress.getText().length() == 0) 
		{
			JOptionPane.showMessageDialog(null, "Enter Address");
			txtAddress.grabFocus();
			flag = false;
		} 
		else if (txtBook.getText().length() == 0) 
		{
			JOptionPane.showMessageDialog(null, "Enter Book");
			txtBook.grabFocus();
			flag = false;
		}
		return flag;
	}

	public void getid() 
	{
		SaleOperations po = new SaleOperations();
		txtSaleId.setText(po.cust_getid());
	}

	@Override
	public void windowOpened(WindowEvent e)
	{
		
	}

	@Override
	public void windowClosing(WindowEvent e) 
	{

	}

	@Override
	public void windowClosed(WindowEvent e)
	{

	}

	@Override
	public void windowIconified(WindowEvent e)
	{

	}

	@Override
	public void windowDeiconified(WindowEvent e) 
	{

	}

	@SuppressWarnings("static-access")
	@Override
	public void windowActivated(WindowEvent e) 
	{
		String value = super.Book;
		txtBook.setText(abc + value);
		int pricet = temp_price+super.tprice;
		String temp1 = Integer.toString(pricet);
		txtTotalPrice.setText(temp1);
	}

	@Override
	public void windowDeactivated(WindowEvent e) 
	{
		abc = txtBook.getText();
		te_price = txtTotalPrice.getText();
		temp_price = Integer.parseInt(te_price);
	}
}
public class Book_Sale 
{
	//public static void main(String args[])
	//{
		//new Sale();
	//}
}
