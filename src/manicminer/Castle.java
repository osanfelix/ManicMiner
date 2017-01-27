package manicminer;

public class Castle
{
	public int gold;
	public int wood;
	public int barracks = 0;
	
	// TO OFFER NEW JOB
	protected Resource mine;
	protected Resource forest;

	public void setMine(Resource mine) {
		this.mine = mine;
	}

	public void setForest(Resource forest) {
		this.forest = forest;
	}
	
	public void DepositResources(int amount, Resource.Type type) 
	{
		synchronized (this)
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
			notifyAll();
		}
	}
	
	public int retrieveResources(int amount, Resource.Type type) throws InterruptedException 
	{
		switch(type)
		{
			case GOLD:
			synchronized(this)
			{
				while(gold < amount)
				{
					wait();
				}
				gold -= amount;
			}
			break;
		case WOOD:
			synchronized(this)
			{
				while(wood < amount)
				{
					wait();
				}
				wood -= amount;
			}
			break;
		}
		return amount;
	}

	Resource getOtherJob(Resource.Type type) // To offer other job
	{
		Resource job = null;
		switch(type)
		{
			case GOLD:
				job = forest;
				break;
			case WOOD:
				job = mine;
				break;
		}
		return job;
	}
}