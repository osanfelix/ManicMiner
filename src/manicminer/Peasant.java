package manicminer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Peasant extends Thread
{
	public static final int Capacity = 10;
	
	public enum State{IDLE, GOING_WORK, RETURNING_CASTLE, EXTRACTING_RESOURCE}
	
	String name;
	Resource job;
	Castle castle;
	State state = State.IDLE;
	int gold,wood;
	
	public Peasant(String name, Resource job, Castle castle)
	{
		this.name	= name;
		this.job	= job;
		this.castle	= castle;
	}
	
	@Override
	public void run()
	{
		try {
			while(true)
			{
				// Go to work place
				state = State.GOING_WORK;
				if(job.getType() == Resource.Type.GOLD)					
					sleep(2000);
				else
					sleep(3000);
				
				// Extract the gold / wood
				state = State.EXTRACTING_RESOURCE;
				for(int i = 0; i < Capacity; i++)
				{
					if(job.getAmount() == 0)		// No resource available
						break;
					
					if(job.getType() == Resource.Type.GOLD)
					{
						gold += job.PerformExtraction();
						sleep(2000 + (long)(Math.random() * 1000 ) );
					}
					else
					{
						wood += job.PerformExtraction();
						sleep(1000 + (long)(Math.random() * 1000 ) );
					}
				}
				
				// Go to castle if full or if no resources available
				state = State.RETURNING_CASTLE;
				sleep(2000);
					
				// deposite resources
				castle.DepositResources(gold, Resource.Type.GOLD);
				castle.DepositResources(wood, Resource.Type.WOOD);
					
				gold = 0;
				wood = 0;
				
				
				// Check Job is finish
				if(job.getAmount() == 0)
					break;
			}
			state = State.IDLE;
		}
		catch (InterruptedException ex)
		{
				Logger.getLogger(Peasant.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public String getStateString()
	{
		return state.toString();
	}
	public String getFullName()
	{
		return (job.type == Resource.Type.GOLD)? name + "  (miner)" : name + "  (forest)";
	}
	
	public int getAmount()
	{
		return wood + gold;
	}
}
