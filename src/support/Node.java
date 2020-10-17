package support;

import core.Cell;

public class Node 
{
	public Cell cell;
	public Cell parent;
	public int gCost;
	public int hCost;
	
	public Node(Cell cell, Cell parent, int g, int h)
	{
		this.cell = cell;
		gCost = g;
		hCost = h;
		this.parent = parent;
	}
	
	public int fCost()
	{
		return gCost + hCost;
	}
}
