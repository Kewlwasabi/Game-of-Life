import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Screen extends JPanel implements Runnable{

	Thread thread;
	private boolean running = false;
	
	final int NUM_CELL_ROW = 100;
	final int NUM_CELL_COL = 100;
	
	int gameSpeed = 0;
	
	int pixelSize = 8;
	
	boolean[][] cellLogic = new boolean[NUM_CELL_ROW][NUM_CELL_COL];
	ArrayList<Cell> liveCells = new ArrayList<Cell>();
	
	private int ticks = 0;
	
	
	public Screen(String init, int speed) {
		
		setLayout(new GridLayout(20,20));
		
		gameSpeed = speed;
		initSelect(init);
		
		//inits cells
		for(int i = 0; i < NUM_CELL_ROW; i++){
			for(int j = 0; j < NUM_CELL_COL; j++) {
				if(cellLogic[i][j]) {
					liveCells.add(new Cell(j,i,pixelSize));
				}
			}
		}
		
		
		initGame();
		
	}
	
	public void initGame() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void tick() {
		ticks++;
		
		if(ticks > gameSpeed) {
			
			updateCellLogic();
			updateLiveCells();
			
			ticks = 0;
		}
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		
		//draw grid lines
		for(int i = 0; i < NUM_CELL_ROW; i++) {
			g.drawLine(i*pixelSize, 0, i*pixelSize, NUM_CELL_ROW*pixelSize);
		}
		
		for(int i = 0; i < NUM_CELL_COL; i++) {
			g.drawLine(0, i*pixelSize, NUM_CELL_COL*pixelSize, i*pixelSize);
		}
		
		//draw cells
		for(Cell a : liveCells){
			a.draw(g);
		}
		
	}
	
	public void run() {
		while(running) {
			tick();
			repaint();
		}
	}
	
	public int checkNeighbor(int x, int y, boolean[][] array) {
		int count = 0;
		System.out.println();
		if(array[x-1][y-1] == true) {
			count++;
		}
		if(array[x][y-1] == true) {
			count++;
		}
		if(array[x+1][y-1] == true) {
			count++;
		}
		if(array[x-1][y] == true) {
			count++;
		}
		if(array[x][y+1] == true) {
			count++;
		}
		if(array[x+1][y] == true) {
			count++;
		}
		if(array[x-1][y+1] == true) {
			count++;
		}
		if(array[x+1][y+1] == true) {
			count++;
		}
		
		return count;
	}
	
	public void initSelect(String init) {
		if(init.equals("Glider")) {
			cellLogic[44][45] = true;
			cellLogic[44][46] = true;
			cellLogic[45][45] = true;
			cellLogic[45][44] = true;
			cellLogic[43][44] = true;
		}
		if(init.equals("R-Pentomino")) {
			cellLogic[44][45] = true;
			cellLogic[44][46] = true;
			cellLogic[45][45] = true;
			cellLogic[45][44] = true;
			cellLogic[46][45] = true;
		}
		if(init.equals("Glider Gun")) {
			cellLogic[25][21] = true;
			cellLogic[26][21] = true;
			cellLogic[25][22] = true;
			cellLogic[26][22] = true;
			
			cellLogic[25][31] = true;
			cellLogic[26][31] = true;
			cellLogic[27][31] = true;
			cellLogic[24][32] = true;
			cellLogic[28][32] = true;
			cellLogic[23][33] = true;
			cellLogic[29][33] = true;
			cellLogic[23][34] = true;
			cellLogic[29][34] = true;
			cellLogic[26][35] = true;
			cellLogic[24][36] = true;
			cellLogic[28][36] = true;
			cellLogic[25][37] = true;
			cellLogic[26][37] = true;
			cellLogic[27][37] = true;
			cellLogic[26][38] = true;
			
			cellLogic[23][41] = true;
			cellLogic[24][41] = true;
			cellLogic[25][41] = true;
			cellLogic[23][42] = true;
			cellLogic[24][42] = true;
			cellLogic[25][42] = true;
			cellLogic[22][43] = true;
			cellLogic[26][43] = true;
			cellLogic[21][45] = true;
			cellLogic[22][45] = true;
			cellLogic[26][45] = true;
			cellLogic[27][45] = true;
			
			cellLogic[23][55] = true;
			cellLogic[24][55] = true;
			cellLogic[23][56] = true;
			cellLogic[24][56] = true;
		}
		if(init.equals("Random")) {
			//inits cellLogic randomly
			
			for(int i = 0; i < NUM_CELL_ROW; i++) {
				for(int j = 0; j < NUM_CELL_COL; j++) {
					int bol = (int)(Math.random()*10);
					cellLogic[i][j] = (bol == 0) ? true : false;
				}
			}
		}
	}
	
	public void updateCellLogic() {
		boolean[][] temp = new boolean[NUM_CELL_ROW][NUM_CELL_COL];
		for(int i = 0; i < NUM_CELL_ROW; i++) {
			for(int j = 0; j < NUM_CELL_COL; j++) {
				temp[i][j] = cellLogic[i][j];
			}
		}
		
		//checks for overpopulation and underpopulation
		for(int i = 0; i < NUM_CELL_ROW; i++) {
			for(int j = 0; j < NUM_CELL_COL; j++) {
				if(temp[i][j] && (i != 0 && i != NUM_CELL_ROW-1) && (j != 0 && j != NUM_CELL_COL - 1)) {
					int a = checkNeighbor(i, j, temp);
					if(a < 2 || a > 3) {
						cellLogic[i][j] = false;
					}
				}
			}
		}
		
		//check dead cells for reproduction
		for(int i = 0; i < NUM_CELL_ROW; i++) {
			for(int j = 0; j < NUM_CELL_COL; j++) {
				if(!temp[i][j] && (i != 0 && i != NUM_CELL_ROW-1) && (j != 0 && j != NUM_CELL_COL - 1)) {
					int a = checkNeighbor(i, j, temp);
					if(a == 3) {
						cellLogic[i][j] = true;
					}
				}
			}
		}
		
		//kill borders
		for(int i = 0; i < NUM_CELL_ROW; i++) {
			for(int j = 0; j < NUM_CELL_COL; j++) {
				if(temp[i][j] && (i == 0 || i == NUM_CELL_ROW-1) && (j == 0 || j == NUM_CELL_COL - 1)) {
					cellLogic[i][j] = false;
				}
			}
		}
		
	}
	
	public void updateLiveCells() {
		liveCells.clear();
		for(int i = 0; i < NUM_CELL_ROW; i++) {
			for(int j = 0; j < NUM_CELL_COL; j++) {
				if(cellLogic[i][j]) {
					liveCells.add(new Cell(i, j, pixelSize));
				}
			}
		}
	}
	
	
	
}
