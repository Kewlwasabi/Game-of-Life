import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame{

	int height = 800;
	int width = 800;
	
	Screen s;
	
	public Frame(String init, int speed) {
		super("Game of Life");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setPreferredSize(new Dimension(height, width));
		
		s = new Screen(init, speed);
		
		add(s);
		
		pack();
		//setVisible(true);
		
		
		
	}
	
	/*public static void main(String[] args) {
		new Frame();
	}*/
}
