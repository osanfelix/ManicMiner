package manicminer;
import java.awt.GridLayout;
import java.awt.Label;
import javax.swing.*;
import static java.lang.Thread.sleep;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class ManicMiner
{
	// CONSTANTS
	public static final int NUM_PEASANTS = 10;
	public static final int NUM_BUILDERS = 4;
	
	// GUI Variables
	static JLabel[] labelPeasantsName		= new JLabel[NUM_PEASANTS];
	static JLabel[] labelPeasantsState		= new JLabel[NUM_PEASANTS];
	static JLabel[] labelPeasantsResources	= new JLabel[NUM_PEASANTS];
	
	static JLabel[] labelBuildersName		= new JLabel[NUM_BUILDERS];
	static JLabel[] labelBuildersState		= new JLabel[NUM_BUILDERS];
	
	static JLabel labelMineInfo			= new JLabel();
	static JLabel labelForestInfo		= new JLabel();
	static JLabel labelCastleInfoGold	= new JLabel();
	static JLabel labelCastleInfoWood	= new JLabel();
	static JLabel labelBarracksInfo		= new JLabel();
	
	
	// Auxiliar name generator variables
	static Queue<String> namesQueue;
	
	
	public static void main(String[] args)
	{
		createGUI();
		
		// Create the resources
		Mine goldMine = new Mine(250);
		Forest forest = new Forest(500);
		
		// Create the castle
		Castle castle = new Castle();
		
		// Create Peasants
		Peasant[] peasants = new Peasant[NUM_PEASANTS];
		for(int i = 0; i < NUM_PEASANTS / 2; i++)
		{
			peasants[i] = new Peasant(randomName(), goldMine, castle);
			peasants[i].start();
		}
		for(int i = NUM_PEASANTS / 2; i < NUM_PEASANTS; i++)
		{
			peasants[i] = new Peasant(randomName(), forest, castle);
			peasants[i].start();
		}
		
		// Create Builders
		Builder[] builders = new Builder[NUM_BUILDERS];
		for(int i = 0; i < NUM_BUILDERS; i++)
		{
			builders[i] = new Builder(randomName(), castle);
			builders[i].start();
		}
		
		
		// Update GUI
		while(true)
		{
			try {
				sleep(500);
				
				// Update Pesants GUI labels
				for(int i = 0; i < NUM_PEASANTS; i++)
				{
					labelPeasantsName[i].setText(peasants[i].getFullName());
					labelPeasantsState[i].setText(peasants[i].getStateString());
					labelPeasantsResources[i].setText(Integer.toString(peasants[i].getAmount()));
				}
				
				for(int i = 0; i < NUM_BUILDERS; i++)
				{
					labelBuildersName[i].setText(builders[i].getFullName());
					labelBuildersState[i].setText(builders[i].getStateString());
				}
				
				// Update Reources & Buildings
				labelMineInfo.setText("Mine gold:" + goldMine.getAmount());
				labelForestInfo.setText("Forest wood: " + forest.getAmount());
				labelBarracksInfo.setText("Barracks: " + castle.barracks);
				labelCastleInfoGold.setText("Castle gold: " + castle.gold);
				labelCastleInfoWood.setText("Castle wood: " + castle.wood);
				
			} catch (InterruptedException ex)
			{
				System.out.println("Interrupted...");
				break;
			}
		}
	}
	
	 private static void createGUI()
	 {
		 generateNames();
		 
		 //Create and set up the window.
        JFrame frame = new JFrame("Fils");
		// Get the main panel
		JPanel panelLabels = (JPanel) frame.getContentPane();
		// Create Layout
		GridLayout layout = new GridLayout(NUM_PEASANTS + NUM_BUILDERS + 6, 3);
		panelLabels.setLayout(layout);

  		for(int i = 0; i < NUM_PEASANTS; i++)
		{
			labelPeasantsName[i]		= new JLabel();
			labelPeasantsState[i]		= new JLabel();
			labelPeasantsResources[i]	= new JLabel();
			
			panelLabels.add(labelPeasantsName[i]);
			panelLabels.add(labelPeasantsState[i]);
			panelLabels.add(labelPeasantsResources[i]);
		}
		panelLabels.add(new Label("--------------"));
		panelLabels.add(new Label("--------------"));
		panelLabels.add(new Label("--------------"));
		
		for(int i = 0; i < NUM_BUILDERS; i++)
		{
			labelBuildersName[i]		= new JLabel();
			labelBuildersState[i]		= new JLabel();
			
			panelLabels.add(labelBuildersName[i]);
			panelLabels.add(labelBuildersState[i]);
			panelLabels.add(new Label(""));
		}
		
		panelLabels.add(new Label("--------------"));
		panelLabels.add(new Label("--------------"));
		panelLabels.add(new Label("--------------"));
		
		panelLabels.add(labelMineInfo);
		panelLabels.add(labelForestInfo);
		panelLabels.add(labelBarracksInfo);
		panelLabels.add(labelCastleInfoGold);
		panelLabels.add(labelCastleInfoWood);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
	 
	 
	 private static void generateNames()
	{	
		List<String> names = new ArrayList<>();
		names.add("Mauricio");
		names.add("Joselito");
		names.add("Eduardo");
		names.add("Alexis");
		names.add("Miguel");
		names.add("Juanma");
		names.add("Ruben");
		names.add("Victor");
		names.add("Marcos");
		names.add("Luis");
		names.add("Pancho");
		names.add("Guillermo");
		names.add("Conan");
		names.add("Daniel");
		names.add("Carles");
		names.add("Pedro");
		names.add("Abelino");
		names.add("Alejandro");
		names.add("Baltasar");
		names.add("Victor");
		names.add("Benedicto");
		names.add("Benjamin");
		names.add("Bryan");
		names.add("Borja");
		names.add("Edgar");
		names.add("Elbio");
		names.add("John");
		names.add("Joaquin");
		names.add("Ricardo");
		names.add("Diego");
		names.add("Nicolas");
		names.add("Napoleon");
		names.add("Teodorico");
		names.add("Teseo");
		names.add("Zacarias");
		
		Collections.shuffle(names);
		namesQueue = new ArrayDeque<>(names);
	}
	
	private static String randomName()
	{
		if(namesQueue.size() > 0)
			return namesQueue.remove();
		else
			return "Peasant";
	}
}