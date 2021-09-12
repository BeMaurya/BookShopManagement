package Admin;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import net.proteanit.sql.DbUtils;
import Main.*;
class Author extends ValidateForm implements ActionListener
{
	public JFrame frm;
	public PreparedStatement ps;
	public ResultSet rs;
	public Connection conn;
	public JLabel lblid, lblHeading, lblSearch, lblName, lblTitle, barlabel;
	public JTextField txtid, txtname, txtSearch;
	public JButton btnNew, btnClear, btnEDIT;
	public DefaultTableModel jtable;
	public JTable tabview;
	public TableRowSorter<TableModel> rowSorter;

	Font f = new Font("consolas", Font.BOLD, 14);

	Author() 
	{
		frm = new JFrame("Author Details");
		frm.setSize(665, 350);
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
		lblHeading.setBounds(230, 12, 370, 20);
		frm.add(lblHeading);

		Canvas canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setForeground(Color.RED);
		canvas.setBounds(230, 33, 220, 2);
		frm.add(canvas);

		lblTitle = new JLabel("Author Details");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Magneto", Font.BOLD, 17));
		lblTitle.setBounds(270, 40, 204, 27);
		frm.add(lblTitle);

		barlabel = new JLabel();
		Image userimg = new ImageIcon(this.getClass().getResource("/images/emp_bar.png")).getImage();
		barlabel.setIcon(new ImageIcon(userimg));
		barlabel.setBounds(0, 0, 700, 70);
		frm.add(barlabel);

		lblid = new JLabel("AUTHOR ID");
		lblid.setBounds(20, 80, 90, 30);
		frm.add(lblid);
		lblid.setFont(f);
		lblid.setForeground(Color.blue);

		txtid = new JTextField();
		txtid.setBounds(110, 80, 200, 30);
		txtid.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtid);
		txtid.setBackground(Color.WHITE);
		txtid.setFont(f);

		lblName = new JLabel("AUTHOR NAME");
		lblName.setBounds(330, 80, 110, 30);
		frm.add(lblName);
		lblName.setFont(f);
		lblName.setForeground(Color.blue);

		txtname = new JTextField();
		txtname.setBounds(430, 80, 200, 30);
		txtname.setBackground(Color.WHITE);
		txtname.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtname);
		txtname.setFont(f);

		btnNew = new JButton("NEW");
		btnNew.setBounds(20, 120, 180, 30);
		btnNew.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNew.setBackground(Color.decode("#88d2f6"));
		btnNew.addActionListener(this);
		frm.add(btnNew);

		btnEDIT = new JButton("EDIT");
		btnEDIT.setBounds(235, 120, 180, 30);
		btnEDIT.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEDIT.setBackground(Color.decode("#88d2f6"));
		btnEDIT.addActionListener(this);
		frm.add(btnEDIT);

		btnClear = new JButton("CLEAR");
		btnClear.setBounds(450, 120, 180, 30);
		btnClear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClear.setBackground(Color.decode("#88d2f6"));
		btnClear.addActionListener(this);
		frm.add(btnClear);

		lblSearch = new JLabel("SEARCH");
		lblSearch.setBounds(200, 160, 125, 30);
		lblSearch.setForeground(Color.BLUE);
		lblSearch.setFont(f);
		lblSearch.setBackground(Color.BLUE);
		frm.add(lblSearch);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(270, 160, 210, 30);
		txtSearch.setFont(f);
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
		
		jtable = new DefaultTableModel();
		tabview = new JTable(jtable);
		JScrollPane js = new JScrollPane(tabview);
		js.setBounds(20, 200, 615, 100);
		tabview.setFont(f);
		tabview.setForeground(Color.blue);
		tabview.setBackground(Color.WHITE);
		Object[] columns = { "Id", "NAME" };
		jtable.setColumnIdentifiers(columns);
		tabview.setModel(jtable);
		tabview.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ShowTable();
		tabview.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				int i = tabview.getSelectedRow();
				TableModel model = tabview.getModel();
				txtid.setText(model.getValueAt(i, 0).toString());
				txtname.setText(model.getValueAt(i, 1).toString()); 
				txtname.grabFocus();
			}
		});
		frm.add(js);

		disabled();
		frm.setVisible(true);
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
					BookOperations bo = new BookOperations(txtid.getText().toUpperCase(), txtname.getText().toUpperCase());
					if (bo.Author_userValidate())
					{
						if (bo.Author_saveRecord() == 1) 
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
			if (txtname.getText().equals("")) 
			{
				JOptionPane.showConfirmDialog(null, "First Select Value From Table");
			} 
			else 
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
						BookOperations bo = new BookOperations(txtid.getText().toUpperCase(), txtname.getText().toUpperCase());
						if (bo.Author_update() == 1)
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
		}
		else if (e.getSource().equals(btnClear)) 
		{
			clear();
		}

	}

	public boolean frmvalidate()
	{
		boolean flag = true;
		if (ValidateForm.validateAnyString(txtname.getText()) == false) 
		{
			JOptionPane.showMessageDialog(null, "Enter Author Name ");
			txtname.grabFocus();
			flag = false;
		}
		return flag;
	}

	public void getid() 
	{
		BookOperations mo = new BookOperations();
		txtid.setText(mo.Author_getId());
	}

	public void ShowTable() 
	{
		String sqlqry = "SELECT ID,NAME FROM AUTHOR_DETAILS";
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

	public void clear()
	{
		txtid.setText("");
		txtname.setText("");
	}

	public void disabled()
	{
		txtid.setEditable(false);
		txtname.setEditable(false);
	}

	public void enabled()
	{
		txtname.setEditable(true);
	}
}

public class Add_Author 
{
	//public static void main(String args[])
	//{
		Author ath = new Author();
	//}
}