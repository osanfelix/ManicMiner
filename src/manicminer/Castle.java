package manicminer;

public class Castle
{
	public int gold;
	public int wood;
	public int barracks = 0;
	
	
	public synchronized void DepositResources(int amount, Resource.Type type) 
	{
		switch(type)
		{
			case GOLD:
				gold += amount;
				break;
			case WOOD:
				wood += amount;
				break;
		}
		notify();
	}
	
	public synchronized int retrieveResources(int amount, Resource.Type type) throws InterruptedException 
	{
		switch(type)
		{
			case GOLD:
				while(gold < amount)
					wait();
				gold -= amount;
				break;
			case WOOD:
				while(wood < amount)
					wait();
				wood -= amount;
				break;
		}
		return amount;
	}
}