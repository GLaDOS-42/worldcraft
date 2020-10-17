package support;

import java.util.ArrayList;

import core.Cell;
import core.Game;

public class AStarPathFinder 
{
	private Cell source;
	private Cell target;
	
	private ArrayList<Node> open;
	private ArrayList<Node> closed;
	private boolean hasPath = false;
	private Node current;
	
	public AStarPathFinder(Cell source, Cell target)
	{
		this.source = source;
		this.target = target;
		open   = new ArrayList<Node>();
		closed = new ArrayList<Node>();
	}
	
	private int gCost(Cell cell, Node parent)
	{
		if(cell == source)
		{
			return 0;
		}
		
		return Utility.getDistance(cell, parent.cell) + parent.gCost;
	}
	
	private int hCost(Cell cell)
	{
		return Utility.getDistance(cell, target);
	}
	
	public Path findPath()
	{
		int searchDepth = 0;
		if(Utility.getNeighbors(source).size() == 0) { return null; }
		if(Utility.getNeighbors(target).size() == 0) { return null; }
		
		ArrayList<Cell> stepList = new ArrayList<Cell>();
		open.add(new Node(source, null, 0, hCost(source)));
		
		while(!hasPath)
		{
			searchDepth++;
			current = findLowest();
			open.remove(current);
			closed.add(current);
			
			if(current.cell == target)
			{
				hasPath = true;
				break;
			}
			
			if(!Utility.inBounds(target) && Utility.getNeighbors(target).contains(current.cell))
			{
				hasPath = true;
				break;
			}
			
			if(searchDepth >= Values.MAX_SEARCH_DEPTH)
			{
				return null;
			}
			
			for(Cell neighborCell : Utility.getNeighbors(current.cell))
			{
				Node b = new Node(neighborCell, current.cell, gCost(neighborCell, current), hCost(neighborCell));
				boolean inOpenSet = false;
				boolean inClosedSet = false;		
				for(Node openNode : open)
				{
					if(openNode.cell == neighborCell)
					{
						inOpenSet = true;
						if(b.fCost() < openNode.fCost())
						{	
							open.set(open.indexOf(openNode), b);
						}
						break;
					}
				}
				
				for(Node closedNode : closed)
				{
					if(closedNode.cell == neighborCell)
					{
						inClosedSet = true;
						if(b.hCost < closedNode.hCost)
						{
							closed.set(closed.indexOf(closedNode), b);
						}
						break;
					}
				}		
				if(!inOpenSet && !inClosedSet) { open.add(b); }
			}
		}	
		stepList = retrace();
		return new Path(stepList);
	}
	
	private ArrayList<Cell> retrace()
	{
		boolean hasPath = false;
		Node currentNode = findLowest();
		ArrayList<Cell> reversePath = new ArrayList<Cell>();
		ArrayList<Cell> path = new ArrayList<Cell>();	
		for(Node a : closed)
		{
			if(a.cell == target || Utility.getNeighbors(target).contains(a.cell))
			{
				currentNode = a;
			}
		}	
		while(!hasPath)
		{
			for(Node a : closed)
			{
				if(a.cell == currentNode.parent )
				{
					currentNode = a;
					reversePath.add(a.cell);
				}	
				
				if(currentNode.cell == source)
				{
					hasPath = true;
					break;
				}
			}
		}
		
		for(int i = reversePath.size() - 1; i >= 0; i--)
		{
			path.add(reversePath.get(i));
		}		
		return path;
	}
	
	private Node findLowest()
	{
		int lowestFCost = 99999;
		Node lowestNode = new Node(target, target, 99999, 99999);	
		for(Node a : open)
		{
			if(a.fCost() < lowestFCost || a.fCost() == lowestFCost && a.hCost < lowestNode.hCost)
			{
				lowestFCost = a.fCost();
				lowestNode = a;
			}
		}
		return lowestNode;
	}
	
}
