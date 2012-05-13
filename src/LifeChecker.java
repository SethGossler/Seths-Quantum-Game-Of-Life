import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;


public class LifeChecker implements ActionListener
{
	private GridData theGridData;
	public boolean done;
	private Timer myTimer;
	private int SPEED = 1;
	
	public LifeChecker(GridData thisGridData)
	{
		this.theGridData = thisGridData;
		myTimer = new Timer(SPEED, this);
	}
	
	public LifeChecker() {
		myTimer = new Timer(SPEED, this);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		//Setting up the NextGrid to be "zeroed" out
		int[][] currentGrid = theGridData.getGrid();
		int[][] nextGrid = new int[currentGrid.length][currentGrid[0].length];
		      
		//Going through the grid
		for(int x = 0; x < currentGrid.length-1; x++)
		{
			for(int y = 0; y < currentGrid[x].length-1; y++)
			{
				/*
				 * Find how many neighbors are nearby
				 * Considering the pixel at the current x,y is the "origin" (0,0)...
				 * And then reducing the size of the grid around the borders of it, so not to have an out of bounds exception
				 */
				if(x != 0 && y != 0 && x != nextGrid.length && y != currentGrid[x].length)
				{
					int localNeighbors = 0;
					done=false;
					
					if(currentGrid[x-1][y+1] == 1)//(-1,1)
						localNeighbors++;
					if(currentGrid[x-1][y] == 1)//(-1, 0)
						localNeighbors++;
					if(currentGrid[x-1][y-1] == 1)//(-1,-1)
						localNeighbors++;
					if(currentGrid[x][y+1] == 1)//(0,1)
						localNeighbors++;
					if(currentGrid[x][y-1] == 1)//(0,-1)
						localNeighbors++;
					if(currentGrid[x+1][y+1] == 1)//(1,1)
						localNeighbors++;
					if(currentGrid[x+1][y] == 1)//(1,0)
						localNeighbors++;
					if(currentGrid[x+1][y-1] == 1)//(1, -1)
						localNeighbors++;
					
					/*
					 * end of finding the neighbors
					 */
					/*
					 * checking for life, birth, or death
					 */
					//if the pixel is alive
					switch(currentGrid[x][y])
					{
					//if the pixel is alive
						case 1:
							switch(localNeighbors)
							{
							//there are two or 3 neighbors, it lives on
							case 2:
								nextGrid[x][y] = 1;
								//Current life stays the same count
								break;
							case 3:
								nextGrid[x][y] = 1;
								//Current life stays the same count
								break;
							//starvation or over population
							default:
								nextGrid[x][y] = 0;
								Driver.currentLife--;
								break;
							}
							break;
						//if the pixel is dead
						case 0:
							switch(localNeighbors)
							{
								case 3:
									nextGrid[x][y] = 1;
									Driver.currentLife++;
									break;
								case 2:
									if(quantumCheck())
									{
										nextGrid[x][y] = 1;
										Driver.currentLife++;
									}
									break;
							}
							break;
					}

				}
			}
		}	
		//setting the current grid to the next grid
		Driver.generations++;
		theGridData.setPrevGrid(currentGrid);
		theGridData.setGrid(nextGrid);
		
	}
	
	public void setGridData(GridData thatGridData){
		this.theGridData = thatGridData;
	}
	
	public Timer getTimer(){
		return myTimer;
	}
	
	public void setTimer(Timer thatTimer){
		this.myTimer = thatTimer;
	}
	
	public boolean quantumCheck()
	{
		Random quantum = new Random();
		int temp = Math.abs(quantum.nextInt()%10000);
				
		if(temp < 1)
			return true;
		else
			return false;
		
		
	}
	
}
