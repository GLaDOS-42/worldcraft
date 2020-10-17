package support;

import java.util.ArrayList;

import core.Cell;
import core.Game;
import core.Water;

public class Utility 
{

	public static int random(int max) 
	{
		return (int) (Math.random() * max);
	}

	public static int random(int min, int max) 
	{
		return (int) (Math.random() * (max - min + 1)) + min;

	}

	public static float random(double min, double max) 
	{
		return (float) (min + (Math.random() * (max - min)));
	}

	//checks if target cell can be accessed - can't move across water, mountains or off the map
	//Also can't move into a cell if there is already an actor there
	public static boolean inBounds(Cell target)
	{
		if(!target.getAccessible())
		{
			return false;
		}
		if(target.getX() <= 0 || 
		   target.getX() >= Values.WORLD_WIDTH - 1 ||
		   target.getY() <= 0 ||
		   target.getY() >= Values.WORLD_HEIGHT - 1)
		{
			return false;
		}
		if(!(target.getActor() == null))
		{
			return false;
		}
		return true;
	}
	
	public static int getDistance(Cell source, Cell target)
	{
		return Math.abs(source.getX() - target.getX()) + Math.abs(source.getY() - target.getY());
	}
	
	public static boolean getNearWater(Cell pos)
	{
		return Game.world.cells[pos.getX() + 1][pos.getY()].getTerrain() instanceof Water ||
			   Game.world.cells[pos.getX() - 1][pos.getY()].getTerrain() instanceof Water ||
			   Game.world.cells[pos.getX()][pos.getY() + 1].getTerrain() instanceof Water ||
			   Game.world.cells[pos.getX()][pos.getY() - 1].getTerrain() instanceof Water;
	}
	
	public static ArrayList<Cell> getNeighbors(Cell pos)
	{
		ArrayList<Cell> result = new ArrayList<Cell>();
		
		//left
		if(pos.getX() > 1 && inBounds(Game.world.cells[pos.getX() - 1][pos.getY()]))
		{
			result.add(Game.world.cells[pos.getX() - 1][pos.getY()]);
		}
		
		//right
		if(pos.getX() + 1 < Values.WORLD_WIDTH && inBounds(Game.world.cells[pos.getX() + 1][pos.getY()]))
		{
			result.add(Game.world.cells[pos.getX() + 1][pos.getY()]);
		}
		
		//above
		if(pos.getY() > 1 && inBounds(Game.world.cells[pos.getX()][pos.getY() - 1]))
		{
			result.add(Game.world.cells[pos.getX()][pos.getY() - 1]);
		}
		
		//below
		if(pos.getY() + 1 < Values.WORLD_HEIGHT && inBounds(Game.world.cells[pos.getX()][pos.getY() + 1]))
		{
			result.add(Game.world.cells[pos.getX()][pos.getY() + 1]);
		}
		
		return result;
	}
}
