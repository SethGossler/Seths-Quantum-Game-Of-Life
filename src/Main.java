import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Main
{
	public JFrame mainFrame;
	public static JTextArea currentLife; 
	public static Random drawRandom = new Random();
	private static final long serialVersionUID = 1L;
	public static JPanel bottomPane;
	public static int Resolution[] = {0, 0};
	private GridData gridData;
	private GameCanvas gamePane;
	private LifeChecker gameTick;
	
	public Main(LifeChecker thatGameTick, GridData thatGridData)
	{

		/*Setup passed in references*/
		this.gameTick = thatGameTick;
		this.gridData = thatGridData;
		
		/*Setting Up the MainFrame*/
		mainFrame = new JFrame();
		mainFrame.setTitle("Quantum Game of Life");
		Resolution[0] = 800; Resolution[1] = 800;
		changeFrameSize(Resolution[0], Resolution[1]);
		mainFrame.setResizable(false);
		mainFrame.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*Setting up the GameCavas*/
		gamePane = new GameCanvas();
		gamePane.setBounds(0, 0, 800, 800);
		gamePane.setPreferredSize(new Dimension(800,800));
		gamePane.setScale(2);
		gamePane.addGridData(this.gridData);
		
		/*Setting up the bottom pane, where the buttons should be*/
		bottomPane = new JPanel();
		bottomPane.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		bottomPane.setBounds(0, 800, 800, 90);
		bottomPane.setPreferredSize(new Dimension(800, 63));
		bottomPane.setBackground(Color.black);
		
		/*Start Button*/
		Button start = new Button("Start");
		start.setBackground(Color.white);
		start.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				gameTick.getTimer().start();
				gamePane.getTimer().start();
			}
			
		});
		c.insets = new Insets(0, 10, 1, 0);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 2;
		c.gridx = 0;
		bottomPane.add(start, c);
		
		/*Pause button*/
		Button pause = new Button("Pause");
		pause.setBackground(Color.white);
		pause.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				gameTick.getTimer().stop();
			}
			
		});
		c.gridx = 0;
		c.gridy = 1;
		bottomPane.add(pause, c);
		
		/*Noise Button*/
		Button addNoise = new Button("Add Noise");
		addNoise.setBackground(Color.white);
		addNoise.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				for(int x = 0; x < gridData.getGrid().length-1; x++)
				{
					for(int y = 0; y < gridData.getGrid()[x].length-1; y++)
					{
						if(drawRandom.nextInt() > 2000000000)
						{
							gridData.getGrid()[x][y] = 1;
							Driver.currentLife++;
						}
					}
				}
				mainFrame.repaint();
			}			
		});
		c.gridx = 1;
		c.gridy = 0;
		bottomPane.add(addNoise, c);
		
		/*Restart Button*/
		Button restart = new Button("Restart");
		restart.setBackground(Color.white);
		restart.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				gridData.setGrid(new int[gridData.getGrid().length][gridData.getGrid()[0].length]);
				Driver.currentLife = 0;
				Driver.generations = 0;
				mainFrame.repaint();
				
			}
		});
		c.gridx = 1;
		c.gridy = 1;
		bottomPane.add(restart, c);
		
		/*Current Life TextPane*/
		JPanel textPane = new JPanel();
		textPane.setSize(100, 100);
		textPane.setBackground(Color.white);
		textPane.setMinimumSize(textPane.getSize());
		currentLife = new JTextArea();
		currentLife.setFocusable(false);
		currentLife.setEditable(false);
		currentLife.setBounds(0, 0, 250, 100);
		currentLife.setText("Current Life: " + Driver.currentLife + "  Generations: " + Driver.generations);
		textPane.setLayout(null);
		textPane.add(currentLife);
		c.insets = new Insets(0, 10, 1, 10);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 0;
		bottomPane.add(textPane, c);
		
		/*Properties Button*/
		Button properties = new Button("Properties");
		properties.setBackground(Color.white);
		properties.setEnabled(false);
		properties.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				
			}
		});
		c.insets = new Insets(0, 10, 0, 10);
		c.gridy = 1;
		bottomPane.add(properties, c);
		
		/*Adding all the Panes and setting the main visible*/
		mainFrame.addMouseListener(new MyMouseListener(gridData, gamePane));
		mainFrame.addMouseMotionListener(new MyMouseListener(gridData, gamePane));
		mainFrame.add(gamePane);
		mainFrame.add(bottomPane);
		mainFrame.setVisible(true);
		gamePane.getTimer().start();

	}
	
	public void pauseGame()
	{
		gameTick.getTimer().stop();
	}
	
	public void startGame()
	{	
		gameTick.getTimer().start();
	}
	
	
	public void changeFrameSize(int x, int y)
	{
		mainFrame.setBounds(10, 10, x, y+90);//Add 90 for the bottompane
	}
	
}
