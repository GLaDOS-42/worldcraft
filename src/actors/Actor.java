package actors;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import core.Cell;
import core.Game;
import support.AStarPathFinder;
import support.Path;
import support.Utility;
import support.Values;

public class Actor 
{
	protected Cell owner;
	protected Image image;
	
	protected float vel;	//time it takes to move to a new cell
	protected int w = Values.CELL_SIZE;
	protected int h = Values.CELL_SIZE;
	
	protected float health = 100;
	protected boolean canRemove = false;
	
	protected int stepCount = 0;
	protected boolean hasPath;
	
	protected AStarPathFinder pathFinder;
	protected Path path;
	
	protected enum Direction
	{
		up, down, left, right, none;
		
		public static Direction getRandomDir() {
            return values()[Utility.random(5)];
        }
	}
	protected Direction dir;
	
	public Actor(Cell owner)
	{
		this.owner = owner;
		owner.setActor(this);
		setImage("res/defaultImage.png");
	}
	
	public void setImage(String filepath)
	{
		try
		{
			image = new Image(filepath);
		}
		catch(SlickException e)		
		{
			System.out.println("Image not found!");
		}
	}
	
	protected String coordinates()
	{
		String x = "x: " + owner.getX();
		x += " y: " + owner.getY();
		return x;
	}
	
	public boolean getCanRemove()
	{
		return canRemove;
	}
	
	//gets which cell to move to based upon the current direction
	public Cell getTargetCell(Direction dir)
	{
		switch(dir) 
		{
		case up:
			return Game.world.cells[owner.getX()][owner.getY() - 1];
		case down:
			return Game.world.cells[owner.getX()][owner.getY() + 1];
		case right:
			return Game.world.cells[owner.getX() + 1][owner.getY()];
		case left:
			return Game.world.cells[owner.getX() - 1][owner.getY()];
		default:
			return Game.world.cells[owner.getX()][owner.getY()];
		}
	}
	
	public void moveTo(Cell target)
	{
		basicPathFinding(target);
	}
	
	public void render(Graphics g)
	{
		image.draw((owner.getX() - Game.world.getCameraX()) * Values.CELL_SIZE,
				   (owner.getY() - Game.world.getCameraY()) * Values.CELL_SIZE, w, h);
		
		if(Game.debugMode)
		{
			g.setColor(Color.white);
			g.drawString(coordinates() + "\nhealth: " + health, 
					    (owner.getX() - Game.world.getCameraX()) * Values.CELL_SIZE,
					    (owner.getY() - Game.world.getCameraY()) * Values.CELL_SIZE);
		}
	}
	
	public void update()
	{
		if(health == 0) { canRemove = true; }
		
		Cell target = getTargetCell(dir);
		if((int)(Game.time % vel) == 0 && Utility.inBounds(target))
		{
			owner.setActor(null);
			owner = target;
			owner.setActor(this);
		}
	}
	
	public void aStarPathFinding(Cell target)
	{
		pathFinder = new AStarPathFinder(owner, target);
		path = pathFinder.findPath();
	}
	
	//very simple pathfinding - will just run into a tree if it is in the way
	public void basicPathFinding(Cell target) {
		if(owner.getX() < target.getX()) 
		{
			dir = Direction.right;
		} 
		else if(owner.getX() > target.getX())
		{
			dir = Direction.left;
		}
		if(owner.getY() < target.getY()) 
		{
			dir = Direction.down;
		} 
		else if(owner.getY() > target.getY()) 
		{
			dir = Direction.up;
		}
	}

}