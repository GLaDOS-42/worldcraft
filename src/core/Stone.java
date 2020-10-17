package core;

import org.newdawn.slick.Color;

public class Stone extends Terrain
{
	double stoneFactor;
	
	public Stone(Cell owner, double percent)
	{
		super(owner);
		stoneFactor = percent;
		color = new Color((int)(stoneFactor * 420 - 288), 42, 42);
		accessible = false;
	}
	
	public void update()
	{
		
	}

}
