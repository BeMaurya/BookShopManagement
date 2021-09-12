package Cashier;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import Main.*;
class CashierUpdate implements ActionListener 
{
	public JFrame frm;
	public JButton btnUploade, btnEdit, btnCANCEL;
	public JLabel lblHeading, lblUpdateProfile, barlabel, lblId, lbluser, lblsAnswer, lblpass, lblconfpass, lblimg, lblsQueston, lblEmailId, lblSpez, lblName, lblDOB, lblQualifaction, lblGender, lblFull_Address, lblMob_No, lblPhoto, lbllogo;
	public JTextField txtId, txtUser_Id, txtsAnswer, txtName, txtDOB, txtQualifaction, txtMob_No, txtEmailId, txtimg;
	public JComboBox<Object> cmbGender, cmbSecQueston;
	public JTextArea txtFull_Address;
	public JPasswordField txtPASS, txt_ConfPASS;
	public Color hexa1;
	public JDateChooser dateChooser;
	String arr[] = { "MALE", "FEMALE" };
	String arr1[] = { "Nick Name", "Favourite Color", "Favourite Food", "Favourite Place" };

	Font f = new Font("Consolas", Font.BOLD, 13);

	JFileChooser fch = new JFileChooser();
	String path = "";
	String filename = "";
	String picname = "";

	CashierUpdate()
	{
		frm = new JFrame();
		frm.setSize(805, 620);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int framewidth = frm.getSize().width;
		int frameheight = frm.getSize().height;

		int framelocationX = (dim.width - framewidth) / 2;
		int frmlocationY = (dim.height - frameheight) / 2;

		frm.setLocation(framelocationX, frmlocationY);

		frm.setResizable(false);
		Color hexa = Color.decode("#BBF2FA");
		frm.getContentPane().setBackground(hexa);
		frm.setLayout(null);
		frm.setTitle("UPDATE PROFILE");

		frm.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png")));
		
		hexa1 = Color.decode("#82efff");

		lblHeading = new JLabel("Book Shop Management");
		lblHeading.setFont(new Font("Algerian", Font.BOLD, 34));
		lblHeading.setForeground(Color.WHITE);
		lblHeading.setBounds(200, 12, 650, 30);
		frm.add(lblHeading);

		Canvas canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setForeground(Color.RED);
		canvas.setBounds(200, 40, 430, 5);
		frm.add(canvas);

		lblUpdateProfile = new JLabel("Update Profile");
		lblUpdateProfile.setForeground(Color.WHITE);
		lblUpdateProfile.setFont(new Font("Magneto", Font.BOLD, 23));
		lblUpdateProfile.setBounds(320, 60, 204, 27);
		frm.add(lblUpdateProfile);

		barlabel = new JLabel();
		Image userimg = new ImageIcon(this.getClass().getResource("/images/doc_bar.png")).getImage();
		barlabel.setIcon(new ImageIcon(userimg));
		barlabel.setBounds(0, 0, 805, 105);
		frm.add(barlabel);

		lblId = new JLabel("CSH- ");
		lblId.setBounds(10, 110, 200, 30);
		lblId.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		lblId.setForeground(Color.RED);
		frm.add(lblId);

		lblName = new JLabel("Cashier Name");
		lblName.setBounds(570, 110, 200, 30);
		lblName.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		lblName.setForeground(Color.RED);
		frm.add(lblName);

		lblDOB = new JLabel("DATE OF BIRTH");
		lblDOB.setBounds(20, 160, 100, 30);
		lblDOB.setFont(f);
		lblDOB.setForeground(Color.BLUE);
		frm.add(lblDOB);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(150, 160, 220, 30);
		dateChooser.setFont(f);
		dateChooser.setBackground(Color.WHITE);
		dateChooser.setForeground(Color.BLACK);
		dateChooser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(dateChooser);

		lbluser = new JLabel("USER ID");
		lbluser.setBounds(420, 160, 80, 30);
		lbluser.setFont(f);
		lbluser.setForeground(Color.BLUE);
		frm.add(lbluser);

		txtUser_Id = new JTextField();
		txtUser_Id.setBounds(550, 160, 220, 30);
		txtUser_Id.setFont(f);
		txtUser_Id.setBackground(Color.WHITE);
		txtUser_Id.setForeground(Color.BLACK);
		txtUser_Id.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtUser_Id);

		lblGender = new JLabel("GENDER");
		lblGender.setBounds(20, 210, 100, 30);
		lblGender.setFont(f);
		lblGender.setForeground(Color.BLUE);
		frm.add(lblGender);

		cmbGender = new JComboBox<Object>(arr);
		cmbGender.setBounds(150, 210, 220, 30);
		cmbGender.setFont(f);
		cmbGender.setSelectedIndex(-1);
		cmbGender.setBackground(Color.WHITE);
		cmbGender.setForeground(Color.BLACK);
		cmbGender.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(cmbGender);

		lblsQueston = new JLabel("SECURITY QUESTION");
		lblsQueston.setBounds(420, 210, 150, 30);
		lblsQueston.setFont(f);
		lblsQueston.setForeground(Color.BLUE);
		frm.add(lblsQueston);

		cmbSecQueston = new JComboBox<Object>(arr1);
		cmbSecQueston.setBounds(550, 210, 220, 30);
		cmbSecQueston.setFont(f);
		cmbSecQueston.setSelectedIndex(-1);
		cmbSecQueston.setBackground(Color.WHITE);
		cmbSecQueston.setForeground(Color.BLACK);
		cmbSecQueston.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(cmbSecQueston);

		lblMob_No = new JLabel("MOBILE NO.");
		lblMob_No.setBounds(20, 260, 100, 30);
		lblMob_No.setFont(f);
		lblMob_No.setForeground(Color.BLUE);
		frm.add(lblMob_No);

		txtMob_No = new JTextField();
		txtMob_No.setBounds(150, 260, 220, 30);
		txtMob_No.setFont(f);
		txtMob_No.setBackground(Color.WHITE);
		txtMob_No.setForeground(Color.BLACK);
		txtMob_No.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtMob_No);

		lblsAnswer = new JLabel("ANSWER");
		lblsAnswer.setBounds(420, 260, 150, 30);
		lblsAnswer.setFont(f);
		lblsAnswer.setForeground(Color.BLUE);
		frm.add(lblsAnswer);

		txtsAnswer = new JTextField();
		txtsAnswer.setBounds(550, 260, 220, 30);
		txtsAnswer.setFont(f);
		txtsAnswer.setBackground(Color.WHITE);
		txtsAnswer.setForeground(Color.BLACK);
		txtsAnswer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtsAnswer);

		lblpass = new JLabel("PASSWORD");
		lblpass.setBounds(20, 310, 100, 30);
		lblpass.setFont(f);
		lblpass.setForeground(Color.BLUE);
		frm.add(lblpass);

		txtPASS = new JPasswordField();
		txtPASS.setBounds(150, 310, 220, 30);
		txtPASS.setFont(f);
		txtPASS.setBackground(Color.WHITE);
		;
		txtPASS.setForeground(Color.BLACK);
		txtPASS.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtPASS);

		lblconfpass = new JLabel("CONFIRM PASSWORD");
		lblconfpass.setBounds(420, 310, 150, 30);
		lblconfpass.setFont(f);
		lblconfpass.setForeground(Color.BLUE);
		frm.add(lblconfpass);

		txt_ConfPASS = new JPasswordField();
		txt_ConfPASS.setBounds(550, 310, 220, 30);
		txt_ConfPASS.setFont(f);
		txt_ConfPASS.setBackground(Color.WHITE);
		txt_ConfPASS.setForeground(Color.BLACK);
		txt_ConfPASS.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txt_ConfPASS);

		lblimg = new JLabel("IMAGE");
		lblimg.setBounds(20, 360, 100, 30);
		lblimg.setFont(f);
		lblimg.setForeground(Color.BLUE);
		frm.add(lblimg);

		txtimg = new JTextField();
		txtimg.setBounds(150, 360, 450, 30);
		txtimg.setFont(f);
		txtimg.setBackground(Color.WHITE);
		txtimg.setForeground(Color.BLACK);
		txtimg.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtimg);

		btnUploade = new JButton("UPLOAD");
		btnUploade.setBounds(610, 360, 160, 30);
		btnUploade.setFont(f);
		btnUploade.setForeground(Color.BLUE);
		btnUploade.setBackground(hexa1);
		btnUploade.setToolTipText("Temporirly,The button doesnot work");
		btnUploade.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(btnUploade);
		btnUploade.setEnabled(false);

		lblEmailId = new JLabel("E-Mail ID");
		lblEmailId.setBounds(20, 410, 100, 30);
		lblEmailId.setFont(f);
		lblEmailId.setForeground(Color.BLUE);
		frm.add(lblEmailId);

		txtEmailId = new JTextField();
		txtEmailId.setBounds(150, 410, 620, 30);
		txtEmailId.setFont(f);
		txtEmailId.setBackground(Color.WHITE);
		txtEmailId.setForeground(Color.BLACK);
		txtEmailId.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtEmailId);

		lblFull_Address = new JLabel("FULL ADDRESS");
		lblFull_Address.setBounds(20, 460, 100, 30);
		lblFull_Address.setFont(f);
		lblFull_Address.setForeground(Color.BLUE);
		frm.add(lblFull_Address);

		txtFull_Address = new JTextArea();
		txtFull_Address.setBounds(150, 460, 620, 60);
		txtFull_Address.setFont(f);
		txtFull_Address.setBackground(Color.WHITE);
		txtFull_Address.setForeground(Color.BLACK);
		txtFull_Address.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtFull_Address);

		btnEdit = new JButton("EDIT");
		btnEdit.setBounds(20, 540, 365, 35);
		btnEdit.setFont(f);
		btnEdit.setBackground(hexa1);
		btnEdit.setForeground(Color.BLUE);
		btnEdit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(btnEdit);
		btnEdit.addActionListener(this);

		btnCANCEL = new JButton("CANCEL");
		btnCANCEL.setBounds(405, 540, 365, 35);
		btnCANCEL.setFont(f);
		btnCANCEL.setForeground(Color.BLUE);
		btnCANCEL.setBackground(hexa1);
		btnCANCEL.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(btnCANCEL);
		btnCANCEL.addActionListener(this);

		disabled();

		frm.setVisible(true);
	}

	public void disabled()
	{
		dateChooser.setEnabled(false);
		txtUser_Id.setEditable(false);
		txtMob_No.setEditable(false);
		txtEmailId.setEditable(false);
		txtimg.setEditable(false);
		cmbGender.setEnabled(false);
		cmbGender.setEditable(false);
		cmbSecQueston.setEnabled(false);
		cmbSecQueston.setEditable(false);
		txtsAnswer.setEditable(false);
		txtPASS.setEditable(false);
		txt_ConfPASS.setEditable(false);
		txtFull_Address.setEditable(false);
	}

	public void enabled()
	{
		dateChooser.setEnabled(true);
		txtUser_Id.setEditable(true);
		txtMob_No.setEditable(true);
		txtEmailId.setEditable(true);
		txtimg.setEditable(true);
		cmbGender.setEnabled(true);
		cmbGender.setEditable(true);
		cmbSecQueston.setEnabled(true);
		cmbSecQueston.setEditable(true);
		txtsAnswer.setEditable(true);
		txtPASS.setEditable(true);
		txt_ConfPASS.setEditable(true);
		txtFull_Address.setEditable(true);
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
				enabled();
				btnEdit.setText("UPDATE");
			} 
			else if (e.getActionCommand().equalsIgnoreCase("UPDATE")) 
			{
				if (frmvalidate()) 
				{
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
					String ddate = dateFormat.format(dateChooser.getDate());
					if (frmvalidate()) 
					{
						RegisterOperation dro = new RegisterOperation(lblId.getText(), lblName.getText(), ddate, cmbGender.getSelectedItem().toString(), txtMob_No.getText(), txtEmailId.getText().toLowerCase(), txtFull_Address.getText(), txtUser_Id.getText(), cmbSecQueston.getSelectedItem().toString(), txtsAnswer.getText().toUpperCase(), txtPASS.getText());
						if (strongPassword(txtPASS.getText()))
						{
							if (dro.cash_update() == 1)
							{
								JOptionPane.showMessageDialog(null, "SUCESSFULLY UPDATED");
								btnEdit.setText("EDIT");
								btnEdit.setEnabled(true);
								frm.dispose();
								new Login();
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
		} else if (e.getSource().equals(btnCANCEL)) 
		{
			frm.dispose();
		}
	}

	@SuppressWarnings("deprecation")
	public boolean frmvalidate() 
	{
		boolean flag = true;
		if (cmbGender.getSelectedIndex() == -1) 
		{
			JOptionPane.showMessageDialog(null, "Choose Your Gender");
			cmbGender.grabFocus();
			flag = false;
		} 
		else if (ValidateForm.validateMobileNo(txtMob_No.getText()) == false)
		{
			JOptionPane.showMessageDialog(null, "Enter Correct phone no..");
			txtMob_No.grabFocus();
			flag = false;
		} 
		else if (ValidateForm.validateEmail(txtEmailId.getText()) == false) 
		{
			JOptionPane.showMessageDialog(null, "Enter email id..");
			txtEmailId.grabFocus();
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
		else if (cmbSecQueston.getSelectedIndex() == -1)
		{
			JOptionPane.showMessageDialog(null, "Choose Security Question");
			cmbSecQueston.grabFocus();
			flag = false;
		} 
		else if (txtFull_Address.getText().length() == 0)
		{
			JOptionPane.showMessageDialog(null, "Enter Address");
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
		for (char c : ch) {
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
public class Cashier_Update 
{
	public static void main(String args[])
	{
		new CashierUpdate();
	}
}
