
package manicminer;

public class Resource
{
	public enum Type{GOLD, WOOD}
	
	protected Type type;
	protected int amount;
	
	public Type getType()
	{
		return type;
	}

	public int getAmount()
	{
		return amount;
	}
	
	public synchronized int PerformExtraction()
	{
		// extract an unit
		amount--;
		return 1;
	}
}