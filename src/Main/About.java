package Main;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
class abt
{
	public JFrame frm;
	public JPanel contentPane;
	public JTextArea txtrThisIsAn ;
	public JScrollPane scrollPane;
	abt()
	{
		frm = new JFrame("About");
		frm.setSize(450, 305);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int framewidth = frm.getSize().width;
		int frameheight = frm.getSize().height;

		int framelocationX = (dim.width - framewidth) / 2;
		int frmlocationY = (dim.height - frameheight) / 2;

		frm.setLocation(framelocationX, frmlocationY);

		frm.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png")));
		
		frm.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frm.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel();
		Image userimg = new ImageIcon(this.getClass().getResource("/images/about.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(userimg));
		lblNewLabel.setBounds(10, 11, 100, 250);
		contentPane.add(lblNewLabel);
		
		JLabel lblHeading = new JLabel("Book Shop Management");
		lblHeading.setBounds(120, 20, 197, 14);
		contentPane.add(lblHeading);
		
		JLabel lblVersion = new JLabel("Version 1.0");
		lblVersion.setBounds(120, 45, 82, 14);
		contentPane.add(lblVersion);
		
		JLabel lblCopyrightShubhamKumar = new JLabel("Copyright: Shubham Kumar");
		lblCopyrightShubhamKumar.setBounds(120, 70, 180, 14);
		contentPane.add(lblCopyrightShubhamKumar);
		
		JLabel lblCompanyShubhtechno = new JLabel("Company: shubham");
		lblCompanyShubhtechno.setBounds(120, 95, 180, 14);
		contentPane.add(lblCompanyShubhtechno);
		
		JScrollPane scrollPane = new JScrollPane();
		JTextArea textArea = new JTextArea();
		textArea.setText("Description:\r\n\tThis is an attractive and user friendly \r\nsystem, which can reduce the work load and provide\r\nspecified  jobs. It is wholly a computerized managem\r\n-ent system in which all the jobs performed is being\r\ndone and managed  by computer.");
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		scrollPane.setBounds(120, 120, 300, 100);
		scrollPane.setEnabled(false);
		contentPane.add(scrollPane);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBackground(Color.WHITE);
		btnOk.setForeground(new Color(0, 128, 0));
		btnOk.setFont(new Font("Algerian", Font.BOLD, 14));
		btnOk.setBounds(320, 230, 96, 29);
		btnOk.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				frm.dispose();
			}
		});
		btnOk.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent e) 
			{
				btnOk.setBackground(new Color(0, 128, 0));
				btnOk.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				btnOk.setBackground(Color.WHITE);
				btnOk.setForeground(new Color(0, 128, 0));
			}
		});
		btnOk.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(btnOk);
		
		frm.setVisible(true);
	}
}
public class About 
{
	abt a = new abt();
}
