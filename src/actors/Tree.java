package actors;

import org.newdawn.slick.Graphics;

import core.Cell;
import core.Game;
import support.Values;

public class Tree extends Plant
{
	public Tree(Cell owner)
	{
		super(owner);
		dir = Direction.none;
		setImage("res/treeSprite.png");
		w = Values.CELL_SIZE + 10;
		h = Values.CELL_SIZE + 30;
	}
	
	public void render(Graphics g)
	{
		image.setImageColor(0, 255, 0, .8f);
		image.draw((owner.getX() - Game.world.getCameraX()) * Values.CELL_SIZE - 5,
				   (owner.getY() - Game.world.getCameraY()) * Values.CELL_SIZE - 25, w, h);
	}
}
