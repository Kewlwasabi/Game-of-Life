import java.awt.Color;
import java.awt.Graphics;

public class Cell {
	
	private int xCoor;
	private int yCoor;
	private int pixelSize;
	
	public Cell(int xCoord, int yCoord, int pixelSized) {
		xCoor = xCoord;
		yCoor = yCoord;
		pixelSize = pixelSized;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(xCoor*pixelSize, yCoor*pixelSize, pixelSize, pixelSize);
		
	}
	
	public int getxCoor() {
		return xCoor;
	}
	
	public int getyCoor() {
		return yCoor;
	}
	
	
}
