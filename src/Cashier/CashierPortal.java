package Cashier;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Main.*;
import net.proteanit.sql.DbUtils;
public class CashierPortal extends Base implements ActionListener,WindowListener
{
	public JFrame frm;
	public JLabel Fullimg, userImage, idlabel, namelabel, lblupdateprofile, user_Label, lblHeading, lblPortalName, barlabel,lblBookTableH,lblBookSearch,lblsaleTableH;
	public JButton appbtn, btnSale, empbtn, drtimebtn, drugbtn, vaccbtn, servbtn, app1btn, btnlok, btnlogof, btnExit;
	public DefaultTableModel jtable, saleTable,Booktable;
	public JTable tabview, sale_tabview,Book_tabview;
	public JScrollPane js, jsAddress;
	public JTextField txtBookSearch;
	Font heading = new Font("Bookman Old Style", Font.BOLD, 18);
	Font f = new Font("Agency FB", Font.BOLD, 20);
	public CashierPortal() 
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
		
		user_Label = new JLabel("");
		Image adminimg = new ImageIcon(this.getClass().getResource("/images/doc_icon.png")).getImage();
		user_Label.setIcon(new ImageIcon(adminimg));
		user_Label.setBounds(0, 5, 128, 128);
		frm.add(user_Label);

		lblHeading = new JLabel("Book Shop Management");
		lblHeading.setFont(new Font("Algerian", Font.BOLD, 34));
		lblHeading.setForeground(Color.WHITE);
		lblHeading.setBounds(500, 22, 632, 49);
		frm.add(lblHeading);

		Canvas canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setForeground(Color.RED);
		canvas.setBounds(498, 71, 435, 4);
		frm.add(canvas);

		lblPortalName = new JLabel("Cashier Portal");
		lblPortalName.setForeground(Color.WHITE);
		lblPortalName.setFont(new Font("Magneto", Font.BOLD, 27));
		lblPortalName.setBounds(610, 88, 300, 27);
		frm.add(lblPortalName);

		userImage = new JLabel();
		Image userimg = new ImageIcon(this.getClass().getResource("/images/usernew.png")).getImage();
		userImage.setIcon(new ImageIcon(userimg));
		userImage.setBounds(30, 145, 200, 200);
		frm.add(userImage);

		namelabel = new JLabel();
		namelabel.setFont(new Font("Agency FB", Font.BOLD, 32));
		namelabel.setBounds(250, 160, 300, 60);
		frm.add(namelabel);

		idlabel = new JLabel();

		userImage = new JLabel("CASHIER");
		userImage.setFont(new Font("Agency FB", Font.BOLD, 32));
		userImage.setBounds(250, 220, 200, 40);
		frm.add(userImage);

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
				ResultSet rs = ro.cash_sec_recordupdate(idlabel.getText());
				try
				{
					if (rs.next()) 
					{
						CashierUpdate ru = new CashierUpdate();
						ru.lblId.setText(rs.getString(1));
						ru.lblName.setText(rs.getString(2));
						try 
						{
							Date date = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(3));
							ru.dateChooser.setDate(date);
						}
						catch (ParseException e1) 
						{
							e1.printStackTrace();
						}
						if (rs.getString(4).equals("MALE"))
						{
							ru.cmbSecQueston.setSelectedIndex(0);
						} 
						else if (rs.getString(4).equals("FEMALE"))
						{
							ru.cmbSecQueston.setSelectedIndex(1);
						}
						ru.cmbGender.setSelectedItem(rs.getString(4));
						ru.txtMob_No.setText(rs.getString(5));
						ru.txtUser_Id.setText(rs.getString(6));
						if (rs.getString(7).equals("NICK NAME"))
						{
							ru.cmbSecQueston.setSelectedIndex(0);
						}
						else if (rs.getString(7).equals("FAVOURITE COLOR"))
						{
							ru.cmbSecQueston.setSelectedIndex(1);
						} 
						else if (rs.getString(7).equals("FAVOURITE FOOD"))
						{
							ru.cmbSecQueston.setSelectedIndex(2);
						}
						else if (rs.getString(7).equals("FAVOURITE PLACE"))
						{
							ru.cmbSecQueston.setSelectedIndex(3);
						}
						ru.cmbSecQueston.setSelectedItem(rs.getString(7));
						ru.txtsAnswer.setText(rs.getString(8));
						ru.txtPASS.setText(rs.getString(9));
						ru.txt_ConfPASS.setText(rs.getString(9));
						ru.txtEmailId.setText(rs.getString(10));
						ru.txtFull_Address.setText(rs.getString(11));
					}
				} 
				catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		frm.add(lblupdateprofile);

		Canvas canvaschpass = new Canvas();
		canvaschpass.setBackground(new Color(128, 0, 0));
		canvaschpass.setBounds(250, 325, 150, 4);
		frm.add(canvaschpass);

		barlabel = new JLabel("");
		Image barimg = new ImageIcon(this.getClass().getResource("/images/doc_bar.png")).getImage();
		barlabel.setIcon(new ImageIcon(barimg));
		barlabel.setBounds(91, 19, 1293, 102);
		frm.add(barlabel);

		btnSale = new JButton("GENERATE RECIEPT");
		//Image recimg = new ImageIcon(this.getClass().getResource("/images/treatmentdoc.png")).getImage();
		//btnSale.setIcon(new ImageIcon(recimg));
		btnSale.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
		btnSale.setBounds(30, 480, 370, 60);
		btnSale.addActionListener(this);
		frm.add(btnSale);
		
		lblsaleTableH = new JLabel("Sale Table");
		lblsaleTableH.setBounds(750, 160, 300, 30);
		lblsaleTableH.setForeground(new Color(128, 0, 0));
		lblsaleTableH.setFont(new Font("Castellar", Font.BOLD, 27));
		frm.add(lblsaleTableH);
		
		saleTable = new DefaultTableModel();
		sale_tabview = new JTable(saleTable);
		js = new JScrollPane();
		js.setBounds(500, 210, 820, 215);
		sale_tabview.setFont(f);
		sale_tabview.setForeground(Color.blue);
		sale_tabview.setBackground(Color.WHITE);
		Object[] columns = { "Id", "CUSTOMER", "MOBILE_NO", "ADDRESS", "BOOK", "TOTAL PRICE", "DATE" };
		saleTable.setColumnIdentifiers(columns);
		sale_tabview.setModel(saleTable);
		sale_tabview.setRowHeight(30);
		sale_tabview.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		saleTable();
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
		frm.add(js);
		
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

		Fullimg = new JLabel();
		Image img5 = new ImageIcon(this.getClass().getResource("/images/cashier.jpg")).getImage();
		Fullimg.setIcon(new ImageIcon(img5));
		Fullimg.setBounds(0, 0, framewidth, frameheigth);

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

		frm.add(Fullimg);

		frm.setVisible(true);
	}
	public void saleTable() 
	{
		String sqlqry = "SELECT ID,CUSTOMER,MOBILE_NO,ADDRESS,BOOK,TOTAL_PRICE,SALE_DATE FROM SALE_DETAILS";
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
		if (e.getSource().equals(btnSale)) 
		{
			//String curdate = new SimpleDateFormat("dd/MMM/yyyy").format(Calendar.getInstance().getTime());
			Sale sl = new Sale();
			sl.lblName.setText(namelabel.getText());
			//sl.saleTable(curdate, namelabel.getText());
		}
		else if (e.getSource().equals(btnExit)) 
		{
			System.exit(0);
		} 
		else if (e.getSource().equals(btnlogof)) 
		{
			new Login();
			frm.dispose();
		}
	}
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		saleTable();
		
	}
	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		saleTable();
	}
	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
