package Main;
import java.awt.*;
import javax.swing.*;

class SplScreen 
{
	public Label load_label;
	public JLabel name_label, ver_label, comp_label;
	public Frame frame;
	public int i;
	SplScreen()
	{
		frame = new Frame();
		frame.setSize(758, 360);
		frame.setBackground(Color.black);

        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png")));
		
        name_label = new JLabel("Book Shop Management");
		name_label.setFont(new Font("Script MT Bold", Font.BOLD, 44));
		name_label.setForeground(new Color(255, 255, 255));
		name_label.setBounds(85, 41, 635, 64);

		JLabel ver_label = new JLabel("Version 1.0");
		ver_label.setFont(new Font("Impact", Font.PLAIN, 18));
		ver_label.setForeground(new Color(255, 255, 255));
		ver_label.setBounds(95, 116, 124, 38);

		load_label = new Label();
		load_label.setForeground(Color.white);
		load_label.setFont(new Font("Consolas", Font.BOLD, 22));
		load_label.setBounds(570, 261, 225, 38);

		JLabel comp_label = new JLabel("\u00A9 2020 Shubham Kumar : www.example.com");
		comp_label.setFont(new Font("Bookman Old Style", Font.BOLD, 13));
		comp_label.setForeground(new Color(255, 255, 255));
		comp_label.setBounds(385, 328, 358, 14);

		Canvas canvas = new Canvas();
		canvas.setBackground(new Color(0, 100, 0));
		canvas.setForeground(Color.GREEN);
		canvas.setBounds(60, 10, 5, 360);

		Canvas canvas_1 = new Canvas();
		canvas_1.setBackground(new Color(0, 100, 0));
		canvas_1.setBounds(0, 309, 732, 5);

		frame.setLayout(null);
		frame.setUndecorated(true);
		frame.setAlwaysOnTop(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int framewidth = frame.getSize().width;// get the width of the frame
		int frameheight = frame.getSize().height; // get the heigth of the frame
		int framelocationX = (dim.width - framewidth) / 2;
		int framelocationY = (dim.height - frameheight) / 2;
		frame.setLocation(framelocationX, framelocationY);
		frame.add(canvas);
		frame.add(canvas_1);
		frame.add(ver_label);
		frame.add(name_label);
		frame.add(load_label);
		frame.add(comp_label);
		frame.setVisible(true);
	}

	public void iterate()
	{
		int i = 0;
		while (i <= 100) 
		{
			load_label.setText("Loading    " + String.valueOf(i) + "%");
			i = i + 2;
			try
			{
				Thread.sleep(100);
			}
			catch (Exception e)
			{
			}
		}
		if (i == 102) 
		{
			new Login();
			frame.dispose();
		}
	}
}

public class SplashScreen
{
    public static void main(String[] args) 
    {
       SplScreen m = new SplScreen();
		m.iterate();
    }
    
}
