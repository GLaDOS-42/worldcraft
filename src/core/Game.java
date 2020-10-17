// added a comment

package core;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import actors.Actor;
import support.SimplexNoise;
import support.Values;

public class Game extends BasicGameState 
{	
	public static GameContainer gc;
	private int id;
	public static int time;
	public static boolean debugMode = true;
	
	public static World world;
	
	Game(int id) 
	{
		this.id = id;
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		time = 0;
		this.gc = gc;
		this.gc.setVSync(true);
		world = new World();
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		world.render(g);
		g.setColor(Color.black);
		g.fillRect(900, 900, 120, 42);
		g.setColor(Color.gray);
		g.fillRect(902, 902, 116, 38);
		g.setColor(Color.white);
		g.drawString("Generate", 924, 910);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	
		time++;
		world.update();
	}

	public void keyPressed(int key, char c)
	{
		
	}
	
	public void mousePressed(int button, int x, int y)
	{
//		if(world.player.targetCell != null)
//		{
//			world.player.targetCell.setIsTargetCell(false);
//		}
//		int cellX = (int)(x / Values.CELL_SIZE + world.getCameraX());
//		int cellY = (int)(y / Values.CELL_SIZE + world.getCameraY());
//		world.player.setTargetCell(world.cells[cellX][cellY]);
//		world.cells[cellX][cellY].setIsPlayerPath(true);
		
		if(x > 902 && x < (902 + 116) && y > 902 && y < (902 + 38))
		{
			world = new World();
		}
	}
	
	public int getID() 
	{
		return id;
	}


}
