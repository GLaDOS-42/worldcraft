package support;

import java.util.ArrayList;

import core.Cell;

public class Path 
{
	public ArrayList<Cell> path;
	
	public Path(ArrayList<Cell> path)
	{
		this.path = path;
	}
	
	public Cell getCell(int index)
	{
		if(index > path.size())
		{
			return path.get(path.size());
		}
		return path.get(index);
	}
	
	public int getSteps()
	{
		return path.size();
	}
}
