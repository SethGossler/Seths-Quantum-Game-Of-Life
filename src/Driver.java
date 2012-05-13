
import java.util.Scanner;
import javax.swing.SwingUtilities;


/**
 * Quantum Conway's Game of Life
 * @author Seth Gossler
 *
 */



public class Driver 
{
	public static long currentLife = 0;
	public static long generations = 0;
	public static Scanner kb = new Scanner(System.in);
	public static boolean Start = false;
	public static int speed = 1;
	
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				GridData theGrid = new GridData(400, 400);
								
				LifeChecker gameTick = new LifeChecker(theGrid);

				@SuppressWarnings("unused")
				Main mainFrameInit = new Main(gameTick, theGrid);
				
			}
			
		});
	}
	
}