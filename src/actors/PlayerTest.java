package actors;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import actors.Actor.Direction;
import core.Cell;
import core.Game;
import core.World;
import support.AStarPathFinder;
import support.Utility;
import support.Values;

public class PlayerTest extends Actor 
{
	public Cell targetCell;
	
	public PlayerTest(Cell owner)
	{
		super(owner);
		w = Values.CELL_SIZE;
		h = Values.CELL_SIZE;
		vel = 4;
		dir = Direction.none;
	}
	
	public Cell getPlayerCell()
	{
		return owner;
	}
	
	public void setTargetCell(Cell target)
	{
		targetCell = target;
	}
	
	public void controls()
	{
		if(Game.gc.getInput().isKeyDown(Game.gc.getInput().KEY_UP))
		{
			dir = Direction.up;
		}
		if(Game.gc.getInput().isKeyDown(Game.gc.getInput().KEY_DOWN))
		{
			dir = Direction.down;
		}
		if(Game.gc.getInput().isKeyDown(Game.gc.getInput().KEY_RIGHT))
		{
			dir = Direction.right;
		}
		if(Game.gc.getInput().isKeyDown(Game.gc.getInput().KEY_LEFT))
		{
			dir = Direction.left;
		}
		if(Game.gc.getInput().isKeyDown(Game.gc.getInput().KEY_SPACE))
		{
			dir = Direction.none;
		}
	}
	
	public void render(Graphics g)
	{
		super.render(g);
		g.setColor(Color.white);
		g.drawString(coordinates(), 10, 60);
	}
	
	public void update()
	{
		controls();
		super.update();
		if(targetCell != null)
		{
			pathFinder = new AStarPathFinder(owner, targetCell);
			path = pathFinder.findPath();
			if(path != null)
			{
				for(int i = 0; i != Values.WORLD_WIDTH; i++)
				{
					for(int j = 0; j != Values.WORLD_HEIGHT; j++)
					{
						if(path.path.contains(World.cells[i][j]) || World.cells[i][j] == targetCell)
						{
							World.cells[i][j].setIsPlayerPath(true);
						}
						else
						{
							World.cells[i][j].setIsPlayerPath(false);
						}
					}
				}
			}
			
			targetCell = null;
		}
	}
}
