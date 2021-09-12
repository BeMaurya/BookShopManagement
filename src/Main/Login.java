package Main;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.TimerTask;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import Cashier.*;
import Admin.*;

class Log extends Base implements ActionListener 
{
	public JFrame frm;
	public JPanel contentPane,pnl_bg,panel,login,panel_1,loader;
	public JTextField txt_userid;
	public JSeparator separator,separator_1;
	public JPasswordField passwordField;
	public JLabel label,label_1,lblX,lblLogin,lblForgetPassword,label_2,lblNewLabel,lblLoggingIn,lblUser, lblUserId,lblPassword,lblAbout;
	public JButton btnLogin;
	public Canvas canvas;
	public JComboBox<Object> comboBox;
	String arr[] = { "Cashier" };
	boolean flag;
	String text,pwd;
	Log() 
	{
		frm = new JFrame();
		frm.setSize(635, 324);
		frm.setLayout(null);
		frm.setUndecorated(true);
		frm.setAlwaysOnTop(false);
		frm.setBackground(Color.WHITE);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int framewidth = frm.getSize().width;
		int frameheight = frm.getSize().height;
		int framelocationX = (dim.width - framewidth) / 2;
		int framelocationY = (dim.height - frameheight) / 2;
		frm.setLocation(framelocationX, framelocationY);
		frm.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png")));
		frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frm.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pnl_bg = new JPanel();
		pnl_bg.setBackground(Color.WHITE);
		pnl_bg.setBounds(0, 0, 635, 324);
		contentPane.add(pnl_bg);
		pnl_bg.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 635, 324);
		pnl_bg.add(panel);
		panel.setLayout(new CardLayout(0, 0));
		
		login = new JPanel();
		login.setBackground(Color.WHITE);
		panel.add(login, "name_1246115112899");
		login.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(601, 0, 34, 30);
		login.add(panel_1);
		panel_1.setLayout(null);
		
		lblX = new JLabel("   X  ");
		lblX.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent arg0)
			{
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e)
			{

				panel_1.setBackground(Color.RED);
				lblX.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				panel_1.setBackground(Color.WHITE);
				lblX.setForeground(Color.BLACK);
			}
		});
		lblX.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 10));
		lblX.setForeground(Color.BLACK);
		lblX.setBackground(Color.WHITE);
		lblX.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblX.setBounds(0, 0, 29, 30);
		panel_1.add(lblX);
		
		lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("AR JULIAN", Font.BOLD, 32));
		lblLogin.setBounds(35, 32, 107, 40);
		login.add(lblLogin);
		
		lblUser = new JLabel("User");
		lblUser.setForeground(new Color(0, 128, 0));
		lblUser.setFont(new Font("Algerian", Font.PLAIN, 18));
		lblUser.setBounds(120, 100, 69, 30);
		login.add(lblUser);
		
		comboBox = new JComboBox<Object>(arr);
		comboBox.setBackground(new Color(255, 255, 255));
		comboBox.setForeground(new Color(128, 128, 128));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox.setSelectedIndex(-1);
		comboBox.setBorder(null);
		comboBox.setBounds(250, 100, 338, 30);
		comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		login.add(comboBox);
		
		label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(Login.class.getResource("/images/contacts_18px.png")));
		label_2.setBounds(220, 100, 18, 30);
		login.add(label_2);
		
		lblUserId = new JLabel("User Id");
		lblUserId.setForeground(new Color(0, 128, 0));
		lblUserId.setFont(new Font("Algerian", Font.PLAIN, 18));
		lblUserId.setBounds(120, 140, 69, 30);
		login.add(lblUserId);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(Login.class.getResource("/images/contacts_18px.png")));
		label.setBounds(220, 143, 18, 28);
		login.add(label);
		
		txt_userid = new JTextField();
		txt_userid.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txt_userid.setForeground(new Color(102, 102, 102));
		txt_userid.setBackground(Color.WHITE);
		txt_userid.setText("UserId");
		txt_userid.setBounds(250, 140, 333, 30);
		txt_userid.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txt_userid.setBorder(null);
		txt_userid.addFocusListener(new FocusAdapter() 
		{
			@Override
			public void focusGained(FocusEvent arg0) 
			{
				txt_userid.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) 
			{
				if(txt_userid.getText().equals(""))
				{
					txt_userid.setText("UserId");
				}
			}
		});
		login.add(txt_userid);
		txt_userid.setColumns(10);
		
		separator = new JSeparator();
		separator.setForeground(new Color(0, 128, 0));
		separator.setBackground(new Color(0, 128, 0));
		separator.setBounds(250, 175, 333, 14);
		login.add(separator);
		
		lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(0, 128, 0));
		lblPassword.setFont(new Font("Algerian", Font.PLAIN, 18));
		lblPassword.setBounds(120, 181, 93, 30);
		login.add(lblPassword);
		
		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Login.class.getResource("/images/unlock_18px.png")));
		label_1.setBounds(220, 181, 18, 28);
		login.add(label_1);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordField.setForeground(new Color(102, 102, 102));
		passwordField.setBackground(Color.WHITE);
		passwordField.setBounds(250, 181, 333, 30);
		passwordField.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		passwordField.addFocusListener(new FocusAdapter() 
		{
			@Override
			public void focusGained(FocusEvent arg0) 
			{
				passwordField.setText("");
			}
			@SuppressWarnings("deprecation")
			@Override
			public void focusLost(FocusEvent e) 
			{
				if(passwordField.getText().equals(""))
				{
					passwordField.setText("Password");
				}
			}
		});
		passwordField.setBorder(null);
		passwordField.setText("Password");
		passwordField.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					text = txt_userid.getText();
					pwd= passwordField.getText();
					if (frmvalidate()) 
					{
						if (comboBox.getSelectedIndex() == -1) 
						{
							LoginOperation ad = new LoginOperation(txt_userid.getText(), passwordField.getText());
							if (ad.admin_loginValidate())
							{
								loader.show();
								login.hide();
								new java.util.Timer().schedule(new TimerTask() 
								{
									@Override
									public void run()
									{
										RegisterOperation ro = new RegisterOperation();
										ResultSet rs = ro.admin_sec_record((txt_userid.getText()));
										try 
										{
											if (rs.next())
											{
												AdminPortal a = new AdminPortal();
												a.idlabel.setText(rs.getString(1));
												a.namelabel.setText(rs.getString(2));
											}
										}
										catch (SQLException e1)
										{
											e1.printStackTrace();
										}
										frm.dispose();
									}
								},1000*5);
							} 
							else 
							{
								JOptionPane.showMessageDialog(null, "UserId and Password Mismatch");
								loader.hide();
								login.show();
								txt_userid.setText(text);
								passwordField.setText(pwd);
								txt_userid.grabFocus();	
							}
						}
						else if (comboBox.getSelectedItem().equals("Cashier"))
						{
							LoginOperation dl = new LoginOperation(txt_userid.getText(), passwordField.getText());
							if (dl.cash_loginValidate()) 
							{
								loader.show();
								login.hide();
								new java.util.Timer().schedule(new TimerTask() 
								{
									@Override
									public void run()
									{
										RegisterOperation ro = new RegisterOperation();
										ResultSet rs = ro.cash_sec_record((txt_userid.getText()));
										try 
										{
											if (rs.next())
											{
												CashierPortal cp = new CashierPortal();
												cp.idlabel.setText(rs.getString(1));
												cp.namelabel.setText(rs.getString(2));
											}
										}
										catch (SQLException e1)
										{
											e1.printStackTrace();
										}
										frm.dispose();
									}
								},1000*5);
							}	 
							else 
							{
								JOptionPane.showMessageDialog(null, "UserId and Password Mismatch");
								loader.hide();
								login.show();
								txt_userid.setText(text);
								passwordField.setText(pwd);
							}
						} 
					}
				}
			}
		});
		login.add(passwordField);
		
		separator_1 = new JSeparator();
		separator_1.setForeground(new Color(0, 128, 0));
		separator_1.setBackground(new Color(0, 128, 0));
		separator_1.setBounds(250, 214, 333, 20);
		login.add(separator_1);
		
		lblForgetPassword = new JLabel("Forget Password?");
		lblForgetPassword.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				lblForgetPassword.setForeground(Color.RED);
				canvas.setBackground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				lblForgetPassword.setForeground(UIManager.getColor("CheckBox.darkShadow"));
				canvas.setBackground(new Color(0, 128, 0));
			}
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				//new ForgetPassword();
				frm.dispose();
			}
		});
		lblForgetPassword.setFont(new Font("Ebrima", Font.BOLD, 16));
		lblForgetPassword.setForeground(UIManager.getColor("CheckBox.darkShadow"));
		lblForgetPassword.setBounds(250, 252, 159, 22);
		lblForgetPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		login.add(lblForgetPassword);
		
		canvas = new Canvas();
		canvas.setBackground(new Color(0, 128, 0));
		canvas.setBounds(250, 275, 133, 2);
		login.add(canvas);
		
		btnLogin = new JButton("LOGIN");
		btnLogin.setForeground(new Color(0, 128, 0));
		btnLogin.setFont(new Font("Algerian", Font.BOLD, 23));
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.addActionListener(this);
		btnLogin.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent e) 
			{
				btnLogin.setBackground(new Color(0, 128, 0));
				btnLogin.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				btnLogin.setBackground(Color.WHITE);
				btnLogin.setForeground(new Color(0, 128, 0));
			}
		});
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setBounds(425, 250, 164, 40);
		login.add(btnLogin);
		
		lblAbout = new JLabel("About");
		lblAbout.setBackground(new Color(192, 192, 192));
		lblAbout.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseEntered(MouseEvent arg0) 
			{
				lblAbout.setForeground(new Color(128, 128, 128));
			}
			@Override
			public void mouseExited(MouseEvent arg0) 
			{
				lblAbout.setForeground(new Color(192, 192, 192));
			}
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				new About();
			}
		});
		lblAbout.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAbout.setForeground(new Color(192, 192, 192));
		lblAbout.setBounds(585, 301, 46, 14);
		lblAbout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		login.add(lblAbout);
		
		loader = new JPanel();
		loader.setBackground(Color.WHITE);
		panel.add(loader, "name_1277481775937");
		loader.setLayout(null);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/images/ring.gif")));
		lblNewLabel.setBounds(258, 63, 123, 119);
		loader.add(lblNewLabel);
		
		lblLoggingIn = new JLabel("Logging In...");
		lblLoggingIn.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblLoggingIn.setForeground(new Color(0, 128, 0));
		lblLoggingIn.setBounds(271, 209, 110, 32);
		loader.add(lblLoggingIn);
		
		frm.setVisible(true);
	}
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		text = txt_userid.getText();
		pwd= passwordField.getText();
		if(e.getSource().equals(btnLogin))
		{
			if (frmvalidate()) 
			{
				if (comboBox.getSelectedIndex() == -1) 
				{
					LoginOperation ad = new LoginOperation(txt_userid.getText(), passwordField.getText());
					if (ad.admin_loginValidate())
					{
						loader.show();
						login.hide();
						new java.util.Timer().schedule(new TimerTask() 
						{
							@Override
							public void run()
							{
								RegisterOperation ro = new RegisterOperation();
								ResultSet rs = ro.admin_sec_record((txt_userid.getText()));
								try 
								{
									if (rs.next())
									{
										AdminPortal a = new AdminPortal();
										a.idlabel.setText(rs.getString(1));
										a.namelabel.setText(rs.getString(2));
									}
								}
								catch (SQLException e1)
								{
									e1.printStackTrace();
								}
								frm.dispose();
							}
						},1000*5);
					} 
					else 
					{
						JOptionPane.showMessageDialog(null, "UserId and Password Mismatch");
						loader.hide();
						login.show();
						txt_userid.setText(text);
						passwordField.setText(pwd);
						txt_userid.grabFocus();	
					}
				}
				else if (comboBox.getSelectedItem().equals("Cashier"))
				{
					LoginOperation dl = new LoginOperation(txt_userid.getText(), passwordField.getText());
					if (dl.cash_loginValidate()) 
					{
						loader.show();
						login.hide();
						new java.util.Timer().schedule(new TimerTask() 
						{
							@Override
							public void run()
							{
								RegisterOperation ro = new RegisterOperation();
								ResultSet rs = ro.cash_sec_record((txt_userid.getText()));
								try 
								{
									if (rs.next())
									{
										CashierPortal cp = new CashierPortal();
										cp.idlabel.setText(rs.getString(1));
										cp.namelabel.setText(rs.getString(2));
									}
								}
								catch (SQLException e1)
								{
									e1.printStackTrace();
								}
								frm.dispose();
							}
						},1000*5);
					} 
					else 
					{
						JOptionPane.showMessageDialog(null, "UserId and Password Mismatch");
						loader.hide();
						login.show();
						txt_userid.setText(text);
						passwordField.setText(pwd);
					}
				} 
			}
		}
	}
	@SuppressWarnings("deprecation")
	public boolean frmvalidate() 
	{
		// TODO Auto-generated method stub
		flag = true;
		if (txt_userid.getText().equals("")) 
		{
			JOptionPane.showMessageDialog(null, "ENTER USER NAME");
			txt_userid.grabFocus();
			flag = false;
		} 
		else if (passwordField.getText().equals("")) 
		{
			JOptionPane.showMessageDialog(null, "ENTER USER PASSWORD");
			passwordField.grabFocus();
			flag = false;
		}
		return flag;
	}
}

public class Login 
{
    Log l = new Log();
}
