import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;


public class MyMouseListener implements MouseListener, MouseInputListener
{
	private GameCanvas gameCanvas;
	private GridData gridData;
	
	MyMouseListener(GridData gridData, GameCanvas gameCanvas){
		this.gameCanvas = gameCanvas;
		this.gridData = gridData;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) 
	{
		//Nothing Happens
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) 
	{
		//Nothing Happens
	
	}

	@Override
	public void mouseExited(MouseEvent arg0) 
	{
		//Nothing Happens
	
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		int x = ((e.getX()/gameCanvas.getxScale()) - 2);
		int y = ((e.getY()/gameCanvas.getyScale()) - 12); 
		if(x<gridData.getGrid().length && y < gridData.getGrid()[0].length)
		{
			if(gridData.getGrid()[x][y]==0)
			{
				gridData.getGrid()[x][y] = 1;
				Driver.currentLife++;
			}
			if(gridData.getGrid()[x+1][y+1]==0)
			{
				gridData.getGrid()[x+1][y+1] = 1;
				Driver.currentLife++;
			}
			if(gridData.getGrid()[x][y+1] ==0)
			{
				gridData.getGrid()[x][y+1] = 1;
				Driver.currentLife++;
			}
			if(gridData.getGrid()[x+1][y]==0)
			{
				gridData.getGrid()[x+1][y] = 1;
				Driver.currentLife++;
			}

			if(Driver.Start == false)
			{
				gameCanvas.repaint();
			}
		}
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		int x = ((e.getX()/gameCanvas.getxScale()) - 2);
		int y = ((e.getY()/gameCanvas.getyScale()) - 12); 
		if(x<gridData.getGrid().length && y < gridData.getGrid()[0].length)
		{
			if(gridData.getGrid()[x][y]==0)
			{
				gridData.getGrid()[x][y] = 1;
				Driver.currentLife++;
			}
			if(gridData.getGrid()[x+1][y+1]==0)
			{
				gridData.getGrid()[x+1][y+1] = 1;
				Driver.currentLife++;
			}
			if(gridData.getGrid()[x][y+1] ==0)
			{
				gridData.getGrid()[x][y+1] = 1;
				Driver.currentLife++;
			}
			if(gridData.getGrid()[x+1][y]==0)
			{
				gridData.getGrid()[x+1][y] = 1;
				Driver.currentLife++;
			}

			if(Driver.Start == false)
			{
				gameCanvas.repaint();
			}
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		//Nothing Happens
	
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
		//Nothing Happens

	}
}
