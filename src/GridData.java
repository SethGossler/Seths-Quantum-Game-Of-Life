
public class GridData
{
	private int[][] Grid;
	private int[][] prevGrid;
	
	public GridData(int xSize, int ySize)
	{
		this.Grid = new int[xSize][ySize];
		prevGrid = this.Grid.clone();
	}
	
	public void changeGridSize(int[][] Grid)
	{
		if(this.Grid.length > Grid.length)
		{
			for(int x = 0; x < Grid.length-1; x++)
			{
				for(int y = 0; y < Grid[x].length-1; y++)
				{
					Grid[x][y] = this.Grid[x][y];
				}
			}
		}
		else
		{
			if(this.Grid[0].length > Grid[0].length)
			{
				for(int x = 0; x < this.Grid.length-1; x++)
				{
					for(int y = 0; y < Grid[0].length-1; y++)
					{
						Grid[x][y] = this.Grid[x][y];
					}
				}
			}
			else
			{
				for(int x = 0; x < this.Grid.length-1; x++)
				{
					for(int y = 0; y < this.Grid[0].length-1; y++)
					{
						Grid[x][y] = this.Grid[x][y];
					}
				}
				
			}

		}
		
		this.Grid = Grid;
		this.prevGrid = this.Grid;

	}


	public int[][] getGrid()
	{
		return this.Grid;
	}

	public void setGrid(int[][] newGrid)
	{
		this.Grid = newGrid;
	}


	public int[][] getPrevGrid() {
		return prevGrid;
	}

	public void setPrevGrid(int[][] prevGrid) {
		this.prevGrid = prevGrid;
	}
	
}
