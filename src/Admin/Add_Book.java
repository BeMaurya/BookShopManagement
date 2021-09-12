package Admin;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import net.proteanit.sql.DbUtils;
import Main.*;
class Book extends Base implements ActionListener, WindowListener 
{
	public JFrame frm;
	public PreparedStatement ps;
	public ResultSet rs;
	public Connection conn;
	public JLabel lblId, lblHeading, lblName, lblSearch, lblTitle, lblAuthor, barlabel,lbltype, lblPublisher, lblStock, lblPrice;
	public JTextField txtId, txtName, txtdosremarks, txtcompny, txtSearch, txtPrice, txtStock;
	public JComboBox<String> cmbAuthor, cmbtype, cmbPublisher;
	public JButton btnPubs, btnNew, btnAuthor, btnEDIT, btnClear;
	public DefaultTableModel jtable;
	public JTable tabview;
	public String booktype[] = { "Textbook", "Novel", "Generic", "Reference" };
	public TableRowSorter<TableModel> rowSorter;
	public String strdrugs, strtype, strdosage;
	Font f = new Font("consolas", Font.BOLD, 14);
	Book()
	{
		frm = new JFrame();

		frm.setSize(820, 435);
		frm.setResizable(false);
		frm.setLayout(null);
		frm.setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int framewidth = frm.getSize().width;
		int frameheigth = frm.getSize().height;
		int framelocationX = (dim.width - framewidth) / 2;
		int framelocationY = (dim.height - frameheigth) / 2;
		frm.setLocation(framelocationX, framelocationY);
		frm.getContentPane().setBackground(Color.WHITE);
		frm.setTitle("Book Database");
		Color hexa = Color.decode("#BBF2FA");
		frm.getContentPane().setBackground(hexa);
		frm.addWindowListener(this);

		frm.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png")));
		
		lblHeading = new JLabel("Book Shop Management");
		lblHeading.setFont(new Font("Algerian", Font.BOLD, 18));
		lblHeading.setForeground(Color.WHITE);
		lblHeading.setBounds(300, 12, 370, 20);
		frm.add(lblHeading);

		Canvas canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setForeground(Color.RED);
		canvas.setBounds(300, 33, 220, 2);
		frm.add(canvas);

		lblTitle = new JLabel("Book Details");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Magneto", Font.BOLD, 17));
		lblTitle.setBounds(355, 40, 204, 27);
		frm.add(lblTitle);

		barlabel = new JLabel();
		Image userimg = new ImageIcon(this.getClass().getResource("/images/emp_bar.png")).getImage();
		barlabel.setIcon(new ImageIcon(userimg));
		barlabel.setBounds(0, 0, 830, 70);
		frm.add(barlabel);

		lblId = new JLabel("BOOK ID");
		lblId.setBounds(20, 80, 90, 30);
		frm.add(lblId);
		lblId.setFont(f);
		lblId.setForeground(Color.blue);

		txtId = new JTextField();
		txtId.setBounds(140, 80, 225, 30);
		frm.add(txtId);
		txtId.setEditable(false);
		txtId.setBackground(Color.WHITE);
		txtId.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtId.setFont(f);

		lblName = new JLabel("BOOK NAME");
		lblName.setBounds(20, 120, 110, 30);
		frm.add(lblName);
		lblName.setFont(f);
		lblName.setForeground(Color.blue);

		txtName = new JTextField();
		txtName.setBounds(140, 120, 225, 30);
		txtName.setBackground(Color.WHITE);
		txtName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtName);
		txtName.setFont(f);

		lblAuthor = new JLabel("AUTHOR");
		lblAuthor.setBounds(20, 160, 90, 30);
		frm.add(lblAuthor);
		lblAuthor.setFont(f);
		lblAuthor.setForeground(Color.blue);

		cmbAuthor = new JComboBox<String>();
		cmbAuthor.setBounds(140, 160, 110, 30);
		cmbAuthor.setBackground(Color.WHITE);
		cmbAuthor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(cmbAuthor);
		cmbAuthor.setFont(f);
		
		btnAuthor = new JButton("AUTHOR");
		btnAuthor.setBounds(255, 160, 110, 30);
		btnAuthor.setFont(f);
		btnAuthor.setBackground(Color.WHITE);
		frm.add(btnAuthor);
		btnAuthor.addActionListener(this);
		
		lblPublisher = new JLabel("PUBLISHER");
		lblPublisher.setBounds(20, 200, 100, 30);
		frm.add(lblPublisher);
		lblPublisher.setFont(f);
		lblPublisher.setForeground(Color.blue);

		cmbPublisher = new JComboBox<String>();
		cmbPublisher.setBounds(140, 200, 110, 30);
		cmbPublisher.setBackground(Color.WHITE);
		cmbPublisher.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(cmbPublisher);
		cmbPublisher.setFont(f);
		
		btnPubs = new JButton("Publisher");
		btnPubs.setBounds(255, 200, 110, 30);
		btnPubs.setFont(f);
		btnPubs.setBackground(Color.WHITE);
		btnPubs.addActionListener(this);
		frm.add(btnPubs);
		
		lbltype = new JLabel("TYPE");
		lbltype.setBounds(20, 240, 80, 30);
		frm.add(lbltype);
		lbltype.setFont(f);
		lbltype.setForeground(Color.blue);

		cmbtype = new JComboBox<String>(booktype);
		cmbtype.setBounds(140, 240, 225, 30);
		cmbtype.setBackground(Color.WHITE);
		cmbtype.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbtype.setSelectedIndex(-1);
		frm.add(cmbtype);
		cmbtype.setFont(f);
		
		lblStock = new JLabel("STOCK");
		lblStock.setBounds(20, 280, 80, 30);
		frm.add(lblStock);
		lblStock.setFont(f);
		lblStock.setForeground(Color.blue);
		
		txtStock = new JTextField();
		txtStock.setBounds(140, 280, 225, 30);
		frm.add(txtStock);
		txtStock.setFont(f);
		
		lblPrice = new JLabel("PRICE");
		lblPrice.setBounds(20, 320, 80, 30);
		frm.add(lblPrice);
		lblPrice.setFont(f);
		lblPrice.setForeground(Color.blue);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(140, 320, 225, 30);
		frm.add(txtPrice);
		txtPrice.setFont(f);

		btnNew = new JButton("NEW");
		btnNew.setBounds(20, 360, 108, 30);
		btnNew.setBackground(Color.decode("#88d2f6"));
		btnNew.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNew.addActionListener(this);
		frm.add(btnNew);

		btnEDIT = new JButton("EDIT");
		btnEDIT.setBounds(138, 360, 108, 30);
		btnEDIT.setBackground(Color.decode("#88d2f6"));
		btnEDIT.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEDIT.addActionListener(this);
		frm.add(btnEDIT);

		btnClear = new JButton("CLEAR");
		btnClear.setBounds(253, 360, 108, 30);
		btnClear.setBackground(Color.decode("#88d2f6"));
		btnClear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClear.addActionListener(this);
		frm.add(btnClear);

		lblSearch = new JLabel("SEARCH");
		lblSearch.setBounds(410, 80, 125, 30);
		lblSearch.setForeground(Color.BLUE);
		lblSearch.setFont(f);
		lblSearch.setBackground(Color.BLUE);
		frm.add(lblSearch);

		jtable = new DefaultTableModel();
		tabview = new JTable(jtable);
		JScrollPane js = new JScrollPane(tabview);
		js.setBounds(370, 120, 420, 270);
		tabview.setFont(f);
		tabview.setForeground(Color.blue);
		tabview.setBackground(Color.WHITE);
		Object[] columns = { "Id", "NAME", "AUTHOR", "PUBLICATION", "TYPE", "STOCK", "PRICE" };
		jtable.setColumnIdentifiers(columns);
		tabview.setModel(jtable);
		tabview.setRowHeight(30);
		tabview.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ShowTable();
		tabview.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				int i = tabview.getSelectedRow();
				TableModel model = tabview.getModel();
				txtId.setText(model.getValueAt(i, 0).toString());
				txtName.setText(model.getValueAt(i, 1).toString());
				cmbAuthor.setSelectedItem(model.getValueAt(i, 2));
				cmbPublisher.setSelectedItem(model.getValueAt(i, 3));
				cmbtype.setSelectedItem(model.getValueAt(i, 4));
				txtStock.setText(model.getValueAt(i, 5).toString());
				txtPrice.setText(model.getValueAt(i, 6).toString());
				txtName.grabFocus();
			}
		});
		txtSearch = new JTextField();
		txtSearch.setBounds(500, 80, 230, 30);
		txtSearch.setFont(f);
		txtSearch.setBackground(Color.WHITE);
		txtSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtSearch.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				DefaultTableModel table = (DefaultTableModel) tabview.getModel();
				String search = txtSearch.getText();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
				tabview.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter(search));
			}

		});
		frm.add(txtSearch);
		frm.add(js);

		disabled();

		frm.setVisible(true);

	}

	public void ShowTable() 
	{
		String sqlqry = "SELECT ID,NAME,AUTHOR,PUBLICATION,TYPE,STOCK,PRICE FROM BOOK_DETAILS ORDER BY ID ASC";
		conn = Connections.getConnections();
		try
		{
			ps = conn.prepareStatement(sqlqry);
			rs = ps.executeQuery();
			tabview.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource().equals(btnNew))
		{
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
					BookOperations bo = new BookOperations(txtId.getText().toUpperCase(),txtName.getText().toUpperCase(), cmbAuthor.getSelectedItem().toString().toUpperCase(),cmbPublisher.getSelectedItem().toString().toUpperCase(),cmbtype.getSelectedItem().toString().toUpperCase(), txtStock.getText().toUpperCase(), txtPrice.getText().toUpperCase());
					if (bo.Book_userValidate())
					{
						if (bo.Book_saveRecord() == 1)
						{
							JOptionPane.showMessageDialog(null, "ADD SUCCESSFULLY");
							ShowTable();
							clear();
							disabled();
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
		else if (e.getSource().equals(btnEDIT))
		{
			if (e.getActionCommand().equalsIgnoreCase("EDIT"))
			{
				btnEDIT.disable();
				enabled();
				btnEDIT.setText("UPDATE");
			} 
			else if (e.getActionCommand().equalsIgnoreCase("UPDATE")) 
			{
				if (frmvalidate())
				{
					BookOperations bo = new BookOperations(txtId.getText().toUpperCase(),txtName.getText().toUpperCase(), cmbAuthor.getSelectedItem().toString().toUpperCase(),cmbPublisher.getSelectedItem().toString().toUpperCase(),cmbtype.getSelectedItem().toString().toUpperCase(), txtStock.getText().toUpperCase(), txtPrice.getText().toUpperCase());
					if (bo.Book_update() == 1) 
					{
						JOptionPane.showMessageDialog(null, "SUCESSFULLY UPDATED");
						ShowTable();
						btnEDIT.setText("EDIT");
						btnEDIT.setEnabled(true);
						disabled();
						clear();
					} 
					else
					{
						JOptionPane.showMessageDialog(null, "NOT UPDATED");
					}
				}
			}
		} 
		else if (e.getSource().equals(btnClear))
		{
			clear();
		}
		else if (e.getSource().equals(btnAuthor))
		{
			new Add_Author();
		}
		else if (e.getSource().equals(btnPubs))
		{
			new Add_Pulblisher();
		}

	}

	public void clear() 
	{
		txtId.setText("");
		txtName.setText("");
		cmbAuthor.setSelectedIndex(-1);
		cmbPublisher.setSelectedIndex(-1);
		cmbtype.setSelectedIndex(-1);
		txtStock.setText("");
		txtPrice.setText("");
	}

	public void disabled() 
	{
		txtId.setEditable(false);
		txtName.setEditable(false);
		cmbAuthor.setEnabled(false);
		cmbPublisher.setEnabled(false);
		cmbtype.setEnabled(false);
		txtStock.setEditable(false);
		txtPrice.setEditable(false);
	}

	public void enabled() 
	{
		txtName.setEditable(true);
		cmbAuthor.setEnabled(true);
		cmbPublisher.setEnabled(true);
		cmbtype.setEnabled(true);
		txtStock.setEditable(true);
		txtPrice.setEditable(true);
	}

	public boolean frmvalidate() 
	{
		boolean flag = true;
		if (ValidateForm.validateAnyString(txtName.getText()) == false)
		{
			JOptionPane.showMessageDialog(null, "Enter Book Name ");
			txtName.grabFocus();
			flag = false;
		} 
		else if (cmbAuthor.getSelectedIndex() == -1) 
		{
			JOptionPane.showMessageDialog(null, "Choose Author");
			cmbAuthor.grabFocus();
			flag = false;
		} 
		else if (cmbPublisher.getSelectedIndex() == -1) 
		{
			JOptionPane.showMessageDialog(null, "Choose Publisher");
			cmbAuthor.grabFocus();
			flag = false;
		} 
		else if (cmbtype.getSelectedIndex() == -1)
		{
			JOptionPane.showMessageDialog(null, "Choose Type");
			cmbtype.grabFocus();
			flag = false;
		} 
		else if ((txtStock.getText()) == "") 
		{
			JOptionPane.showMessageDialog(null, "Enter Stock ");
			txtStock.grabFocus();
			flag = false;
		}
		else if ((txtPrice.getText()) == "") 
		{
			JOptionPane.showMessageDialog(null, "Enter Price ");
			txtPrice.grabFocus();
			flag = false;
		}
		return flag;
	}

	public void srccour() 
	{
		String sqlqry = "select NAME from AUTHOR_DETAILS";
		conn = Connections.getConnections();
		try 
		{
			ps = conn.prepareStatement(sqlqry);
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
		String sqlQuery = "select NAME from PUBLISHER_DETAILS";
		try 
		{
			ps = conn.prepareStatement(sqlQuery);
			rs = ps.executeQuery();
			while (rs.next()) 
			{
				cmbPublisher.addItem(rs.getString(1));
				cmbPublisher.setSelectedIndex(-1);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	public void getid()
	{
		BookOperations bo = new BookOperations();
		txtId.setText(bo.Book_getid());
	}

	@Override
	public void windowActivated(WindowEvent e) 
	{
		srccour();
	}

	@Override
	public void windowClosed(WindowEvent e) 
	{

	}

	@Override
	public void windowClosing(WindowEvent e) 
	{

	}

	@Override
	public void windowDeactivated(WindowEvent e) 
	{
		cmbAuthor.removeAllItems();
	}

	@Override
	public void windowDeiconified(WindowEvent e)
	{

	}

	@Override
	public void windowIconified(WindowEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) 
	{

	}
}
public class Add_Book 
{
	public static void main(String args[])
	{
		new Book();
	}
}
