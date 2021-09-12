package Admin;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import com.toedter.calendar.JDateChooser;
import Main.*;
import net.proteanit.sql.DbUtils;
public class AdminPortal extends Base implements ActionListener 
{
	public JFrame frm;
	public JLabel Fullimg, lblHeading, lblTitle, barlabel, userlabel, namelabel, idlabel, lblupdateprofile, adminlabel, lbldocicon, lblrecicon, lblEmpSearch, lblBookSearch, lblsaleTableH, lblBookTableH;
	public JTextField txtBookSearch, txtEmpSearch;
	public JButton cshbtn, bookbtn, btnlogof,btnExit;
	public JDateChooser dateChooser;
	public DefaultTableModel saleTable, Booktable;
	public JTable sale_tabview, Book_tabview;
	public JScrollPane js, jsAddress;
	public TableRowSorter<TableModel> rowSorter;
	Font f = new Font("Agency FB", Font.BOLD, 20);
	public AdminPortal() 
	{
		frm = new JFrame();
		frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Toolkit toolkit = frm.getToolkit();
		Dimension size = toolkit.getScreenSize();
		frm.setSize(size.width, size.height);
		frm.setLayout(null);
		frm.setResizable(false);
		frm.setUndecorated(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int framewidth = frm.getSize().width;
		int frameheigth = frm.getSize().height;
		int framelocationX = (dim.width - framewidth) / 2;
		int framelocationY = (dim.height - frameheigth) / 2;
		frm.setLocation(framelocationX, framelocationY);
		frm.getContentPane().setBackground(Color.WHITE);

		frm.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png")));
		
		adminlabel = new JLabel("");
		Image adminimg = new ImageIcon(this.getClass().getResource("/images/admin.png")).getImage();
		adminlabel.setIcon(new ImageIcon( adminimg));
		adminlabel.setBounds(0, 5, 128, 128);
		frm.add(adminlabel);

		lblHeading = new JLabel("Book Shop Management");
		lblHeading.setFont(new Font("Algerian", Font.BOLD, 34));
		lblHeading.setForeground(Color.WHITE);
		lblHeading.setBounds(480, 22, 632, 49);
		frm.add(lblHeading);

		Canvas canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setForeground(Color.RED);
		canvas.setBounds(480, 71, 430, 4);
		frm.add(canvas);

		lblTitle = new JLabel("Admin Portal");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Magneto", Font.BOLD, 27));
		lblTitle.setBounds(593, 88, 204, 27);
		frm.add(lblTitle);

		userlabel = new JLabel();
		Image userimg = new ImageIcon(this.getClass().getResource("/images/usernew.png")).getImage();
		userlabel.setIcon(new ImageIcon(userimg));
		userlabel.setBounds(30, 145, 200, 200);
		frm.add(userlabel);

		namelabel = new JLabel();
		namelabel.setFont(new Font("Agency FB", Font.BOLD, 32));
		namelabel.setBounds(250, 160, 300, 60);
		frm.add(namelabel);

		idlabel = new JLabel();

		userlabel = new JLabel("Admin");
		userlabel.setFont(new Font("Agency FB", Font.BOLD, 32));
		userlabel.setBounds(250, 230, 200, 40);
		frm.add(userlabel);

		lblupdateprofile = new JLabel("Update Profile");
		lblupdateprofile.setForeground(new Color(128, 0, 0));
		lblupdateprofile.setFont(new Font("Agency FB", Font.BOLD, 32));
		lblupdateprofile.setBounds(250, 280, 200, 40);
		lblupdateprofile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblupdateprofile.addMouseListener(new MouseAdapter()
		{
            @Override
			public void mouseClicked(MouseEvent e) 
            {
				RegisterOperation ro = new RegisterOperation();
				ResultSet rs = ro.admin_sec_record((idlabel.getText()));
				try 
				{
					if (rs.next()) 
					{
						AdminUpdate au = new AdminUpdate();
						au.txtUser_Id.setText(rs.getString(1));
						au.txtName.setText(rs.getString(2));
						switch (rs.getString(3))
						{
						case "NICK NAME":
							au.cmbSecQueston.setSelectedIndex(0);
							break;
						case "FAVOURITE COLOR":
							au.cmbSecQueston.setSelectedIndex(1);
							break;
						case "FAVOURITE FOOD":
							au.cmbSecQueston.setSelectedIndex(2);
							break;
						case "FAVOURITE PLACE":
							au.cmbSecQueston.setSelectedIndex(3);
							break;
						default:
							break;
						}
						au.cmbSecQueston.setSelectedItem(rs.getString(3));
						au.txtsAnswer.setText(rs.getString(4));
						au.txtPASS.setText(rs.getString(5));
						au.txt_ConfPASS.setText(rs.getString(5));
					}
				} 
				catch (SQLException e1)
				{
				}
			}
		});
		frm.add(lblupdateprofile);

		Canvas canvaschpass = new Canvas();
		canvaschpass.setBackground(new Color(128, 0, 0));
		canvaschpass.setBounds(250, 325, 150, 4);
		frm.add(canvaschpass);

		barlabel = new JLabel("");
		Image barimg = new ImageIcon(this.getClass().getResource("/images/BarNew.png")).getImage();
		barlabel.setIcon(new ImageIcon(barimg));
		barlabel.setBounds(91, 19, 1293, 102);
		frm.add(barlabel);

		cshbtn = new JButton("Register");
		cshbtn.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
		cshbtn.setBounds(40, 450, 400, 60);
		cshbtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cshbtn.addActionListener(this);
		frm.add(cshbtn);
		
		bookbtn = new JButton("Book");
		bookbtn.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
		bookbtn.setBounds(40, 530, 400, 60);
		bookbtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bookbtn.addActionListener(this);
		frm.add(bookbtn);
		
		//Consolas
		lblsaleTableH = new JLabel("Sale Table");
		lblsaleTableH.setBounds(750, 130, 300, 30);
		lblsaleTableH.setForeground(new Color(128, 0, 0));
		lblsaleTableH.setFont(new Font("Castellar", Font.BOLD, 27));
		//lblsaleTableH.setBackground(Color.BLUE);
		frm.add(lblsaleTableH);
		
		lblEmpSearch = new JLabel("SEARCH");
		lblEmpSearch.setBounds(720, 175, 100, 30);
		lblEmpSearch.setFont(f);
		frm.add(lblEmpSearch);

		saleTable = new DefaultTableModel();
		sale_tabview = new JTable(saleTable);
		js = new JScrollPane();
		js.setBounds(500, 210, 820, 215);
		sale_tabview.setFont(f);
		sale_tabview.setForeground(Color.blue);
		sale_tabview.setBackground(Color.WHITE);
		Object[] columns = { "Id", "CUSTOMER", "MOBILE_NO", "ADDRESS", "BOOK", "TOTAL PRICE", "SALEBY", "DATE" };
		saleTable.setColumnIdentifiers(columns);
		sale_tabview.setModel(saleTable);
		sale_tabview.setRowHeight(30);
		sale_tabview.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		saleTable();
		txtEmpSearch = new JTextField();
		txtEmpSearch.setBounds(810, 175, 230, 30);
		txtEmpSearch.setFont(f);
		txtEmpSearch.setBackground(Color.WHITE);
		txtEmpSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtEmpSearch.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				DefaultTableModel table = (DefaultTableModel) sale_tabview.getModel();
				String search = txtEmpSearch.getText();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
				sale_tabview.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter(search));
			}
		});
		frm.add(txtEmpSearch);
		js.setViewportView(sale_tabview);
		frm.add(js);
		
		lblBookTableH = new JLabel("Book Table");
		lblBookTableH.setBounds(780, 430, 250, 30);
		lblBookTableH.setForeground(new Color(128, 0, 0));
		lblBookTableH.setFont(new Font("Castellar", Font.BOLD, 27));
		lblBookTableH.setBackground(Color.BLUE);
		frm.add(lblBookTableH);
		
		lblBookSearch = new JLabel("SEARCH");
		lblBookSearch.setBounds(720, 475, 125, 30);
		lblBookSearch.setFont(f);
		frm.add(lblBookSearch);

		Booktable = new DefaultTableModel();
		Book_tabview = new JTable(Booktable);
		js = new JScrollPane(Book_tabview);
		js.setBounds(500, 510, 820, 205);
		Book_tabview.setFont(f);
		Book_tabview.setForeground(Color.blue);
		Book_tabview.setBackground(Color.WHITE);
		Object[] Book_columns = { "Id", "NAME", "AUTHOR", "PUBLICATION", "TYPE", "STOCK", "PRICE" };
		Booktable.setColumnIdentifiers(Book_columns);
		Book_tabview.setModel(Booktable);
		Book_tabview.setRowHeight(30);
		Book_tabview.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BookTable();
		
		txtBookSearch = new JTextField();
		txtBookSearch.setBounds(810, 475, 230, 30);
		txtBookSearch.setFont(f);
		txtBookSearch.setBackground(Color.WHITE);
		txtBookSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtBookSearch.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				DefaultTableModel table = (DefaultTableModel) Book_tabview.getModel();
				String search = txtBookSearch.getText();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
				Book_tabview.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter(search));
			}

		});
		frm.add(txtBookSearch);
		frm.add(js);

		Fullimg = new JLabel();
		Image img5 = new ImageIcon(this.getClass().getResource("/images/admin_new.jpg")).getImage();
		Fullimg.setIcon(new ImageIcon(img5));
		Fullimg.setBounds(0, 0, framewidth, frameheigth);
		frm.add(Fullimg);

		btnlogof = new JButton();
		Image logoffimg = new ImageIcon(this.getClass().getResource("/images/logog.jpg")).getImage();
		btnlogof.setBounds(1280, 725, 35, 35);
		btnlogof.setIcon(new ImageIcon(logoffimg));
		Fullimg.add(btnlogof);
		btnlogof.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnlogof.addActionListener(this);

		btnExit = new JButton();
		Image exitimg = new ImageIcon(this.getClass().getResource("/images/Exit.jpg")).getImage();
		btnExit.setBounds(1322, 725, 35, 35);
		btnExit.setIcon(new ImageIcon(exitimg));
		Fullimg.add(btnExit);
		btnExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExit.addActionListener(this);

		frm.setVisible(true);

	}
	public void saleTable() 
	{
		String sqlqry = "SELECT ID,CUSTOMER,MOBILE_NO,ADDRESS,BOOK,TOTAL_PRICE,SALEBY FROM SALE_DETAILS";
		conn = Connections.getConnections();
		try
		{
			ps = conn.prepareStatement(sqlqry);
			rs = ps.executeQuery();
			sale_tabview.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void BookTable() 
	{
		String sqlqry = "SELECT ID,NAME,AUTHOR,PUBLICATION,TYPE,STOCK,PRICE FROM BOOK_DETAILS ORDER BY ID ASC";
		conn = Connections.getConnections();
		try
		{
			ps = conn.prepareStatement(sqlqry);
			rs = ps.executeQuery();
			Book_tabview.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource().equals(cshbtn)) 
		{
			new Cashier();
		}
		else if (e.getSource().equals(bookbtn))
		{
			new Book();
		}
		else if (e.getSource().equals(btnlogof)) 
		{
			new Login();
			frm.dispose();
		} 
		else if (e.getSource().equals(btnExit))
		{
			System.exit(0);
		}
	}

}
//public class AdminPortal 
//{
	//public static void main(String args[])
	//{
		//Admin ad = new Admin();
	//}
		
//}
