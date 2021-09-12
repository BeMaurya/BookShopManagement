//Change the  Table
package Admin;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import net.proteanit.sql.DbUtils;
import Main.*;

class Publisher extends ValidateForm implements ActionListener
{
	public JFrame frm;
	public PreparedStatement ps;
	public ResultSet rs;
	public Connection conn;
	public JLabel lblid, lblHeading, lblTitle, lblSearch, lblName, barlabel;
	public JTextField txtid, txtName, txtSearch;
	public JButton btnNew, btnClear, btnEDIT;
	public DefaultTableModel jtable;
	public JTable tabview;
	public TableRowSorter<TableModel> rowSorter;

	Font f = new Font("consolas", Font.BOLD, 14);

	Publisher() 
	{
		frm = new JFrame("Publisher Details");
		frm.setSize(700, 350);
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
		lblHeading.setBounds(250, 12, 370, 20);
		frm.add(lblHeading);

		Canvas canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setForeground(Color.RED);
		canvas.setBounds(250, 33, 220, 2);
		frm.add(canvas);

		lblTitle = new JLabel("Publication Details");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Magneto", Font.BOLD, 17));
		lblTitle.setBounds(280, 40, 204, 27);
		frm.add(lblTitle);

		barlabel = new JLabel();
		Image userimg = new ImageIcon(this.getClass().getResource("/images/emp_bar.png")).getImage();
		barlabel.setIcon(new ImageIcon(userimg));
		barlabel.setBounds(0, 0, 700, 70);
		frm.add(barlabel);

		lblid = new JLabel("PUBLISHER ID");
		lblid.setBounds(20, 80, 110, 30);
		frm.add(lblid);
		lblid.setFont(f);
		lblid.setForeground(Color.blue);

		txtid = new JTextField();
		txtid.setBounds(125, 80, 200, 30);
		txtid.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtid);
		txtid.setBackground(Color.WHITE);
		txtid.setFont(f);

		lblName = new JLabel("PUBLISHER NAME");
		lblName.setBounds(335, 80, 150, 30);
		frm.add(lblName);
		lblName.setFont(f);
		lblName.setForeground(Color.blue);

		txtName = new JTextField();
		txtName.setBounds(460, 80, 200, 30);
		txtName.setBackground(Color.WHITE);
		txtName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtName);
		txtName.setFont(f);

		btnNew = new JButton("NEW");
		btnNew.setBounds(20, 120, 190, 30);
		btnNew.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNew.setBackground(Color.decode("#88d2f6"));
		btnNew.addActionListener(this);
		frm.add(btnNew);

		btnEDIT = new JButton("EDIT");
		btnEDIT.setBounds(248, 120, 190, 30);
		btnEDIT.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEDIT.setBackground(Color.decode("#88d2f6"));
		btnEDIT.addActionListener(this);
		frm.add(btnEDIT);

		btnClear = new JButton("CLEAR");
		btnClear.setBounds(470, 120, 190, 30);
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
		js.setBounds(20, 200, 645, 100);
		tabview.setFont(f);
		tabview.setForeground(Color.blue);
		tabview.setBackground(Color.WHITE);
		Object[] columns = { "Id", "PUBLICATION" };
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
				txtName.setText(model.getValueAt(i, 1).toString());
				txtName.grabFocus();
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
					BookOperations bo = new BookOperations(txtid.getText().toUpperCase(), txtName.getText().toUpperCase());
					if (bo.Publisher_userValidate())
					{
						if (bo.Publisher_saveRecord() == 1) 
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
			if (txtName.getText().equals("")) 
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
						BookOperations bo = new BookOperations(txtid.getText().toUpperCase(), txtName.getText().toUpperCase());
						if (bo.Publisher_update() == 1)
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
		if (ValidateForm.validateAnyString(txtName.getText()) == false) 
		{
			JOptionPane.showMessageDialog(null, "Enter Publisher Name ");
			txtName.grabFocus();
			flag = false;
		}
		return flag;
	}

	public void getid() 
	{
		BookOperations mo = new BookOperations();
		txtid.setText(mo.Publisher_getId());
	}

	public void ShowTable() 
	{
		String sqlqry = "SELECT ID,NAME FROM PUBLISHER_DETAILS";
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
		txtName.setText("");
	}

	public void disabled()
	{
		txtid.setEditable(false);
		txtName.setEditable(false);
	}

	public void enabled()
	{
		txtName.setEditable(true);
	}
}
public class Add_Pulblisher 
{
	//public static void main(String args[])
	//{
		Publisher pb = new Publisher();
	//}
}
