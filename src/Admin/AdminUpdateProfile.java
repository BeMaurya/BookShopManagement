package Admin;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Main.*;
class AdminUpdate implements ActionListener
{
	public JFrame frm;
	public JButton btnUploade, btnEdit, btnCancel;
	public JLabel lblHeading, lblTitle, barlabel, lbluser, lblsAnswer, lblpass, lblconfpass, lblsQueston, lblName;
	public JTextField txtUser_Id, txtsAnswer, txtName;
	public JComboBox<Object> cmbSecQueston;
	public JPasswordField txtPASS, txt_ConfPASS;
	public Color hexa1;
	String arr1[] = { "NICK NAME", "FAVOURITE COLOR", "FAVOURITE FOOD", "FAVOURITE PLACE" };
	Font f = new Font("Consolas", Font.BOLD, 13);

	AdminUpdate() 
	{
		frm = new JFrame();
		frm.setSize(430, 465);

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
		frm.setTitle("UPDATE PROFILE");

		hexa1 = Color.decode("#82efff");

		lblHeading = new JLabel("Book Shop Management");
		lblHeading.setFont(new Font("Algerian", Font.BOLD, 20));
		lblHeading.setForeground(Color.WHITE);
		lblHeading.setBounds(90, 5, 635, 30);
		frm.add(lblHeading);

		Canvas canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setForeground(Color.RED);
		canvas.setBounds(89, 35, 245, 3);
		frm.add(canvas);

		lblTitle = new JLabel("Update Profile");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Magneto", Font.BOLD, 16));
		lblTitle.setBounds(150, 42, 300, 27);
		frm.add(lblTitle);

		barlabel = new JLabel();
		Image userimg = new ImageIcon(this.getClass().getResource("/images/BarNew.png")).getImage();
		barlabel.setIcon(new ImageIcon(userimg));
		barlabel.setBounds(0, 0, 1300, 70);
		frm.add(barlabel);

		lbluser = new JLabel("USER ID");
		lbluser.setBounds(20, 80, 80, 30);
		lbluser.setFont(f);
		lbluser.setForeground(Color.BLUE);
		frm.add(lbluser);

		txtUser_Id = new JTextField();
		txtUser_Id.setBounds(150, 80, 250, 30);
		txtUser_Id.setFont(f);
		txtUser_Id.setBackground(Color.WHITE);
		txtUser_Id.setForeground(Color.BLACK);
		txtUser_Id.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtUser_Id);

		lblName = new JLabel("NAME");
		lblName.setBounds(20, 130, 100, 30);
		lblName.setFont(f);
		lblName.setForeground(Color.BLUE);
		frm.add(lblName);

		txtName = new JTextField();
		txtName.setBounds(150, 130, 250, 30);
		txtName.setFont(f);
		txtName.setBackground(Color.WHITE);
		txtName.setForeground(Color.BLACK);
		txtName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtName);

		lblsQueston = new JLabel("SECURITY QUESTION");
		lblsQueston.setBounds(20, 180, 150, 30);
		lblsQueston.setFont(f);
		lblsQueston.setForeground(Color.BLUE);
		frm.add(lblsQueston);

		cmbSecQueston = new JComboBox<>(arr1);
		cmbSecQueston.setBounds(150, 180, 250, 30);
		cmbSecQueston.setFont(f);
		cmbSecQueston.setSelectedIndex(-1);
		cmbSecQueston.setBackground(Color.WHITE);
		cmbSecQueston.setForeground(Color.BLACK);
		cmbSecQueston.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(cmbSecQueston);

		lblsAnswer = new JLabel("ANSWER");
		lblsAnswer.setBounds(20, 230, 150, 30);
		lblsAnswer.setFont(f);
		lblsAnswer.setForeground(Color.BLUE);
		frm.add(lblsAnswer);

		txtsAnswer = new JTextField();
		txtsAnswer.setBounds(150, 230, 250, 30);
		txtsAnswer.setFont(f);
		txtsAnswer.setBackground(Color.WHITE);
		txtsAnswer.setForeground(Color.BLACK);
		txtsAnswer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtsAnswer);

		lblpass = new JLabel("PASSWORD");
		lblpass.setBounds(20, 280, 80, 30);
		lblpass.setFont(f);
		lblpass.setForeground(Color.BLUE);
		frm.add(lblpass);

		txtPASS = new JPasswordField();
		txtPASS.setBounds(150, 280, 250, 30);
		txtPASS.setFont(f);
		txtPASS.setBackground(Color.WHITE);
		txtPASS.setForeground(Color.BLACK);
		txtPASS.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtPASS);

		lblconfpass = new JLabel("CONFIRM PASSWORD");
		lblconfpass.setBounds(20, 330, 150, 30);
		lblconfpass.setFont(f);
		lblconfpass.setForeground(Color.BLUE);
		frm.add(lblconfpass);

		txt_ConfPASS = new JPasswordField();
		txt_ConfPASS.setBounds(150, 330, 250, 30);
		txt_ConfPASS.setFont(f);
		txt_ConfPASS.setBackground(Color.WHITE);
		txt_ConfPASS.setForeground(Color.BLACK);
		txt_ConfPASS.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txt_ConfPASS);

		btnEdit = new JButton("EDIT");
		btnEdit.setBounds(20, 380, 180, 35);
		btnEdit.setFont(f);
		btnEdit.setBackground(hexa1);
		btnEdit.setForeground(Color.BLUE);
		btnEdit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(btnEdit);
		btnEdit.addActionListener(this);

		btnCancel = new JButton("CANCEL");
		btnCancel.setBounds(220, 380, 180, 35);
		btnCancel.setFont(f);
		btnCancel.setForeground(Color.BLUE);
		btnCancel.setBackground(hexa1);
		btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(btnCancel);
		btnCancel.addActionListener(this);

		disabledForm();
		frm.setVisible(true);
	}

	public void enabledForm() 
	{
		txtName.setEditable(true);
		txtUser_Id.setEditable(true);
		cmbSecQueston.setEnabled(true);
		cmbSecQueston.setEditable(true);
		txtsAnswer.setEditable(true);
		txtPASS.setEditable(true);
		txt_ConfPASS.setEditable(true);
	}

	public void disabledForm() 
	{
		txtName.setEditable(false);
		txtUser_Id.setEditable(false);
		cmbSecQueston.setEditable(false);
		cmbSecQueston.setEnabled(false);
		txtsAnswer.setEditable(false);
		txtPASS.setEditable(false);
		txt_ConfPASS.setEditable(false);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource().equals(btnEdit))
		{
			if (e.getActionCommand().equalsIgnoreCase("EDIT")) 
			{
				btnEdit.disable();
				enabledForm();
				btnEdit.setText("UPDATE");
			}
			else if (e.getActionCommand().equalsIgnoreCase("UPDATE")) 
			{
				if (frmvalidate())
				{
					RegisterOperation dro = new RegisterOperation(txtUser_Id.getText(), txtName.getText().toUpperCase(), cmbSecQueston.getSelectedItem().toString(), txtsAnswer.getText().toUpperCase(), txtPASS.getText());
					if (strongPassword(txtPASS.getText()))
					{
						if (dro.admin_update() == 1) 
						{
							JOptionPane.showMessageDialog(null, "SUCESSFULLY UPDATED");
							btnEdit.setText("EDIT");
							btnEdit.setEnabled(true);
							frm.dispose();
						}
						else 
						{
							JOptionPane.showMessageDialog(null, "NOT UPDATED");
						}
					} 
					else 
					{
						JOptionPane.showMessageDialog(null,"Week Password!Password Must Contain Capital Letter,Special Symbol & Digit[N/0/@]");
						txtPASS.setText("");
						txtPASS.grabFocus();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Record Already Inserted");
				}
			}

		}
		else if (e.getSource().equals(btnCancel)) 
		{
			frm.dispose();
		}
	}

	@SuppressWarnings("deprecation")
	public boolean frmvalidate()
	{
		boolean flag = true;
		if (ValidateForm.validateAnyString(txtName.getText()) == false)
		{
			JOptionPane.showMessageDialog(null, "Enter Nname ");
			txtName.grabFocus();
			flag = false;
		}
		else if (txtUser_Id.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Enter UserId");
			txtUser_Id.grabFocus();
			flag = false;
		}
		else if (cmbSecQueston.getSelectedIndex() == -1)
		{
			JOptionPane.showMessageDialog(null, "Choose Security Question");
			cmbSecQueston.grabFocus();
			flag = false;
		}
		else if (txtPASS.getText().length() == 0) 
		{
			JOptionPane.showMessageDialog(null, "ENTER PASSWORD");
			txtPASS.grabFocus();
			flag = false;
		} 
		else if (txt_ConfPASS.getText().length() == 0)
		{
			JOptionPane.showMessageDialog(null, "Enter Confirm Password");
			txt_ConfPASS.grabFocus();
			flag = false;
		} 
		else if (!txtPASS.getText().equals(txt_ConfPASS.getText())) 
		{
			JOptionPane.showMessageDialog(null, "PASSWORD AND CONFIRM PASSWORD DO NOT MATCH");
			txtPASS.grabFocus();
			flag = false;
		}
		return flag;
	}

	public boolean strongPassword(String pass)
	{
		boolean sflag = false;
		int spacecount, digitcount, capcount;
		spacecount = digitcount = capcount = 0;
		char[] ch = pass.toCharArray();
		for (char c : ch) 
		{
			if (c >= 65 && c <= 90)
			{
				capcount++;
			}
			if (c > 48 && c <= 57) 
			{
				digitcount++;
			}
			if (!(c >= 48 && c <= 57) && !(c >= 65 && c <= 90) && !(c >= 97 && c <= 122))
			{
				spacecount++;
			}
			if (spacecount > 0 && digitcount > 0 && capcount > 0) 
			{
				sflag = true;
			}
		}
		return sflag;
	}

}
public class AdminUpdateProfile 
{
    public static void main(String args[])
    {
    	new AdminUpdate();
    }
}
