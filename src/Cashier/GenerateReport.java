package Cashier;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.print.PrinterException;
import javax.swing.*;
//import Main.*;
class Report
{
	public JFrame frm;
	public JPanel contentPane;
	public JFileChooser dialog;
	public JLabel Heading,Normal1,Normal2,Normal3,Rx,details;
	public JTextArea textArea,textArea1,namearea,contentarea,pricearea;
	String filePath;
	Font f = new Font("Times New Roman", Font.BOLD, 15);
	public Report()
	{
		frm = new JFrame("Reciept");
		frm.setSize(570,740);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int framewidth = frm.getSize().width;
		int frameheight = frm.getSize().height;

		int framelocationX = (dim.width - framewidth) / 2;
		int frmlocationY = (dim.height - frameheight) / 2;

		frm.setLocation(framelocationX, frmlocationY);
		frm.setResizable(false);
		frm.setForeground(Color.WHITE);
		frm.setBackground(Color.WHITE);
		frm.getContentPane().setEnabled(false);
		frm.getContentPane().setLayout(null);

		frm.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png")));
		
		Heading = new JLabel("               \t                     BOOK SHOP(Shop Name)        ");
		Normal1 = new JLabel("               \t                                 Address 1        ");
		Normal2 = new JLabel("               \t                                 Address 1        ");
		Normal3 = new JLabel("               \t                                 Address 1        ");

		textArea = new JTextArea();
		textArea.setBounds(0, 0, 560, 135);
		textArea.setEditable(false);
		frm.getContentPane().add(textArea);
		textArea.append("    ******************************************************************\n"+Heading.getText()+"\n    ******************************************************************\n"+Normal1.getText()+"\n"+Normal2.getText()+"\n"+Normal3.getText()+"\n    --------------------------------------------------------------------------------------------------------\n");
		textArea.setFont(f);
		
		namearea = new JTextArea();
		namearea.setBounds(0, 135, 560, 60);
		frm.getContentPane().add(namearea);
		namearea.setFont(f);
		//namearea.setText("    NAME : SHUBHAM KUMAR\t\t      DATE: 23-10-2020 \n    --------------------------------------------------------------------------------------------------------");
		
		contentarea = new JTextArea();
		contentarea.setBounds(0, 195, 560, 40);
		frm.getContentPane().add(contentarea);
		contentarea.setFont(f);
		contentarea.setText("    Book\t\tAuthor\tPrice\tQuantity\tAmount");
		
		textArea1 = new JTextArea();
		textArea1.setBounds(0, 235, 560, 395);
		frm.getContentPane().add(textArea1);
		textArea1.setFont(f);
		//textArea1.append("");
		
		pricearea = new JTextArea();
		pricearea.setBounds(0, 630, 560, 30);
		frm.getContentPane().add(pricearea);
		pricearea.setFont(f);
		//pricearea.append("  Total\t\t\t\t\t ");
		
		JButton btnPrint = new JButton("Print");
		btnPrint.setBackground(Color.WHITE);
		btnPrint.setForeground(new Color(0, 128, 0));
		btnPrint.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				try {
					textArea.print();
				} catch (PrinterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnPrint.setBounds(120, 663, 300, 30);
		btnPrint.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent e) 
			{
				btnPrint.setBackground(new Color(0, 128, 0));
				btnPrint.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				btnPrint.setBackground(Color.WHITE);
				btnPrint.setForeground(new Color(0, 128, 0));
			}
		});
		btnPrint.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.getContentPane().add(btnPrint);
		
		frm.setVisible(true);
	}
}
public class GenerateReport 
{
	//public static void main(String args[])
	//{
		Report rpt = new Report();
	//}
}