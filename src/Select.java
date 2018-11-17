import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class Select extends JFrame implements ActionListener{
	
	
	JPanel p = new JPanel();
	JLabel title;
	JComboBox initOptions;
	JComboBox gameSpeed;
	JButton start;
	
	String[] inits = {"Glider", "Glider Gun", "R-Pentomino", "Random"};
	String[] speeds = {"1x", "2x", "5x", "10x"};
	
	String frameInit;
	int speed;
	
	
	public Select() {
		super("Game of Life Options");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		title = new JLabel("Game of Life Options");
		initOptions = new JComboBox(inits);
		gameSpeed = new JComboBox(speeds);
		start = new JButton("Start");
		
		start.addActionListener(this);
		initOptions.addActionListener(this);
		gameSpeed.addActionListener(this);
		
		p.add(title);
		p.add(initOptions);
		p.add(gameSpeed);
		p.add(start);
		
		add(p);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		
	}
	
	public void startGame() {
		setVisible(false);
		Frame f = new Frame(frameInit, speed);
		f.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		if(src == start) {
			String init = (String) initOptions.getSelectedItem();
			String speed = (String) gameSpeed.getSelectedItem();
			
			if(init.equals("Glider")) {
				frameInit = "Glider";
			} 
			if(init.equals("Glider Gun")) {
				frameInit = "Glider Gun";
			} 
			if(init.equals("R-Pentomino")) {
				frameInit = "R-Pentomino";
			}
			if(init.equals("Random")) {
				frameInit = "Random";
			}
			if(speed.equals("1x")) {
				this.speed = 500000;
			}
			if(speed.equals("2x")) {
				this.speed = 250000;
			}
			if(speed.equals("5x")) {
				this.speed = 100000;
			}
			if(speed.equals("10x")) {
				this.speed = 50000;
			}
			
			startGame();
			
		}
	}
	
	

	public static void main(String[] args) {
		new Select();

	}

}
