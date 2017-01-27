/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manicminer;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oscar
 */
public class Builder extends Thread
{
	public static final int BARRACK_GOLD = 5;
	public static final int BARRACK_WOOD = 10;
	
	public enum State{IDLE, BUILDING}
	
	String name;
	
	Castle castle;
	State state = State.IDLE;
	
	int gold,wood;
	
	public Builder(String name, Castle castle)
	{
		this.name = name;
		this.castle = castle;
	}
	
	@Override
	public void run()
	{
		
		try
		{
			while(true)
			{
				// BUILD!!!	
				state = State.IDLE;
				gold = castle.retrieveResources(BARRACK_GOLD, Resource.Type.GOLD);
				wood = castle.retrieveResources(BARRACK_WOOD, Resource.Type.WOOD);
				state = State.BUILDING;
				sleep(20000);
				gold = 0; wood = 0;
				synchronized(castle)
				{
					castle.barracks++;
				}
			}
		}	
		catch (InterruptedException ex)
		{
			Logger.getLogger(Builder.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public String getStateString() {
		return state.toString();
	}

	public String getFullName() {
		return name + "  (builder)";
	}
	
	public String printAmount()
	{
		return "Gold: " + gold + ". Wood: " + wood;
	}
}
