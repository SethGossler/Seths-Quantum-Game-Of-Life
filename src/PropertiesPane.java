import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;


public class PropertiesPane extends JFrame
{
	private static final long serialVersionUID = 1L;
	boolean changed = false;
	JComboBox resolution;
	public static JFrame thisFrame;
	JPanel gameSpeed;
	JTextArea gameSpeedText;
	
	GridData lifeData;
	GameCanvas gamePane;
	Main mainFrame;
	public PropertiesPane(GridData theDataGrid, GameCanvas theGridPane, Main thisFrame)
	{
		
		lifeData = theDataGrid;
		gamePane = theGridPane;
		mainFrame = thisFrame;
		
		mainFrame.pauseGame();
		
		changed = false;
		
		this.setBounds(200, 200, 450, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(null);
		
		this.setBackground(Color.blue);
		
		JPanel layoutPanel = new JPanel();
		layoutPanel.setBounds(0, 0, 450, 300);
		layoutPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		String[] resolutions = {"800, 800", "1000,1000", "1280, 1024", "1366, 768", "1920, 1080"}; 
		resolution = new JComboBox(resolutions);
		resolution.setSelectedIndex(0);
		TitledBorder title = BorderFactory.createTitledBorder("Resolution");
		resolution.setBorder(title);
		resolution.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				changed = true;
			}
			
		});
		c.gridx = 0;
		c.gridy = 0;
		layoutPanel.add(resolution,c);
		
		gameSpeed = new JPanel();
		gameSpeed.setLayout(null);
		gameSpeed.setPreferredSize(new Dimension(70,50));
		gameSpeedText = new JTextArea("" + Driver.speed);
		gameSpeedText.setBounds(15, 22, 30, 20);
		title = BorderFactory.createTitledBorder("Speed(ms)");
		gameSpeed.setBorder(title);
		gameSpeed.add(gameSpeedText);
		c.gridx = 1;
		c.gridy = 0;
		layoutPanel.add(gameSpeed,c);
	
		
		Button save = new Button("Save");	
		save.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if(changed || Integer.parseInt(gameSpeedText.getText())!=Driver.speed)
				{
					int selection = resolution.getSelectedIndex();
					switch (selection) {
						case 0: mainFrame.changeFrameSize(800, 800);
								lifeData.changeGridSize(new int[400][400]);	
								gamePane.setScale(2);
								break;
						case 1: mainFrame.changeFrameSize(1000, 1000);
								lifeData.changeGridSize(new int[500][500]);	
								gamePane.setScale(2);
								break;
						case 2: mainFrame.changeFrameSize(1280, 1024);
								lifeData.changeGridSize(new int[1280][1024]);	
								gamePane.setScale(2);
								break;
						case 3: mainFrame.changeFrameSize(1366, 768);
								lifeData.changeGridSize(new int[1366][768]);	
								gamePane.setScale(2);
								break;
						case 4: mainFrame.changeFrameSize(1900, 1000);
								lifeData.changeGridSize(new int[960][500]);	
								gamePane.setScale(2);
								break;
						default:
								break;
					}
						mainFrame.startGame();
					
				}
			}
			
		});
		c.gridx = 0;
		c.gridy = 1;
		layoutPanel.add(save,c);
		

		
		this.add(layoutPanel);
		

	}
}
