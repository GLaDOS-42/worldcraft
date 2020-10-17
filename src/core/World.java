package core;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;

import actors.Actor;
import actors.Fox;
import actors.PlayerTest;
import actors.Tree;
import support.PerlinNoise;
import support.SimplexNoise;
import support.Utility;
import support.Values;

public class World 
{	
	public static Cell[][] cells;
	
	//public static ArrayList<Actor> actors;
	//public static PlayerTest player;
	
	SimplexNoise simpleNoise;
	SimplexNoise simpleNoise1;
	SimplexNoise simpleNoise2;
	PerlinNoise perlinNoise;
	
	private int cameraX = 0;
	private int cameraY = 0;
	
	public int getCameraX()
	{
		return cameraX;
	}
	
	public int getCameraY()
	{
		return cameraY;
	}
	
	World()
	{		
		simpleNoise  = new SimplexNoise(Utility.random(1000));
		simpleNoise1 = new SimplexNoise(Utility.random(1000));
		simpleNoise2 = new SimplexNoise(Utility.random(1000));
		perlinNoise = new PerlinNoise();
		cells = new Cell[Values.WORLD_WIDTH][Values.WORLD_HEIGHT];		
		initializeCells();
		setElevation();
		addActors();
	}
	
	public void initializeCells()
	{
		for(int x = 0; x < Values.WORLD_WIDTH; x++)
		{
			for(int y = 0; y < Values.WORLD_HEIGHT; y++)
			{
				cells[x][y] = new Cell(x, y);
			}
		}
	}
	
	//lacunarity: frequency of noise, how "squirrely" the octave is
	//amplitude: how much influence the octave has on the overall map
	public float octave(int x, int y, float lac, float amp, SimplexNoise noise)
	{
		return (float)(amp * noise.eval(x * lac, y * lac));
	}
	
	public void setElevation()
	{
		for(int x = 0; x < Values.WORLD_WIDTH; x++)
		{
			for(int y = 0; y < Values.WORLD_HEIGHT; y++)
			{
				float octave0 = octave(x, y, .01f, 1.2f, simpleNoise);
				float octave1 = octave(x, y, .01f, .8f, simpleNoise1);
				float octave2 = octave(x, y, .02f, .3f, simpleNoise2);
				float octave3 = octave(x, y, .08f, .2f, simpleNoise2);
				float octave4 = octave(x, y, .3f, .08f, simpleNoise1);
				cells[x][y].setElevation(octave0 * octave1 + octave2 + octave3 + octave4 + .04f);
			}
		}
	}
	
	public void addActors()
	{
//		actors = new ArrayList<Actor>();
//		player = new PlayerTest(cells[5][5]);
//		actors.add(player);
//		
//		for(int i = 0; i != Values.TREE_AMOUNT; i++)
//		{
//			actors.add(new Tree(getRandomCell()));
//		}
		
//		for(int i = 0; i != 40; i++)
//		{
//			actors.add(new Fox(getRandomCell()));
//		}
	}
	
	public Cell getRandomCell()
	{
		int x = Utility.random(Values.WORLD_WIDTH);
		int y = Utility.random(Values.WORLD_HEIGHT);
		if (!Utility.inBounds(cells[x][y]) || cells[x][y].getTerrain() instanceof Beach)
		{
			return getRandomCell();
		}
		return cells[x][y];
	}
	
	public void render(Graphics g)
	{
		for(int x = cameraX; x < cameraX + Values.CAMERA_WIDTH; x++)
		{
			for(int y = cameraY; y < cameraY + Values.CAMERA_HEIGHT; y++)
			{
				cells[x][y].render(g);
			}
		}
		
//		for(Actor a : actors)
//		{
//			a.render(g);
//		}
	}
	
	public void update()
	{
		controls();
//		for(int x = 0; x < Values.WORLD_WIDTH; x++)
//		{
//			for(int y = 0; y < Values.WORLD_HEIGHT; y++)
//			{
//				cells[x][y].update();
//			}
//		}
		
//		for(int i = 0; i != actors.size(); i++) 
//		{
//			actors.get(i).update();
//			if(actors.get(i).getCanRemove())
//			{
//				actors.remove(actors.get(i));
//				i--;
//			}
//		}
	}
	
	public void controls()
	{
		if(Game.gc.getInput().isKeyDown(Game.gc.getInput().KEY_D) && cameraX + Values.CAMERA_WIDTH < Values.WORLD_WIDTH)
		{
			cameraX += 2;
		}
		if(Game.gc.getInput().isKeyDown(Game.gc.getInput().KEY_A) && cameraX > 0)
		{
			cameraX -= 2;
		}
		if(Game.gc.getInput().isKeyDown(Game.gc.getInput().KEY_W) && cameraY > 0)
		{
			cameraY -= 2;
		}
		if(Game.gc.getInput().isKeyDown(Game.gc.getInput().KEY_S) && cameraY + Values.CAMERA_HEIGHT < Values.WORLD_HEIGHT)
		{
			cameraY += 2;
		}
	}

}
