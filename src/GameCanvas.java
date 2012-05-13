import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;


public class GameCanvas extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private GridData theGrid;
	private int xScale = 2;
	private int yScale = 2;
	private int refresh = 16;
	private Timer myTimer;
	
	public GameCanvas(GridData curGrid){
		this.theGrid = curGrid;
		myTimer = new Timer(refresh, this);
	}
	
	public GameCanvas(){
		myTimer = new Timer(refresh, this);
	}
	
	@Override
	public void paintComponent(Graphics g){
		int[][] tempGrid = theGrid.getPrevGrid().clone();
		g.setColor(new Color(0, 24, 181));
		g.fillRect(0, 0, tempGrid.length*this.getxScale(),tempGrid.length*this.getyScale());
		Main.currentLife.setText("Current Life: " + Driver.currentLife + "  Generations: " + Driver.generations);
		
		//drawing on the canvas with where everything is on the grid.
		for(int x = 0; x < tempGrid.length-1; x++)
		{
			for(int y = 0; y < tempGrid[x].length-1; y++)
			{
				if(tempGrid[x][y] == 1)
				{
					g.setColor(new Color(255, 198, 74));
					g.fillRect(x*this.xScale, y*this.yScale, this.xScale, this.yScale);
				}
			}
		}
	}


	public int getyScale() {
		return yScale;
	}

	public int getxScale() {
		return xScale;
	}
	
	public Timer getTimer(){
		return this.myTimer;
	}
	
	public void setScale(int scale)
	{
		 xScale = scale;
		 yScale = scale;
	}
	
	public void setyScale(int yScale) {
		this.yScale = yScale;
	}
	
	public void setxScale(int xScale) {
		this.xScale = xScale;
	}
	
	public void setRefresh(int thatRefresh){
		this.refresh = thatRefresh;
		this.myTimer = new Timer(refresh, this);
	}

	public void addGridData(GridData thisGrid){
		this.theGrid = thisGrid;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		super.repaint();
		
	}
	

}
