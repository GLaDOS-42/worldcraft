package core;

import org.newdawn.slick.Color;

public class Water extends Terrain
{
	double depth;
	
	public Water(Cell owner, double percent)
	{
		super(owner);
		depth = percent;
		accessible = false;
		if(depth > .58)
		{
			color = new Color(0, (int)(1 / (.66 - depth) * 6), (int)(1 / (depth - .57) * 8));
		}
		else if(depth < .4)
		{
			color = new Color(0, 30, 150);
		}
		else
		{
			color = new Color(0, (int)(1 / (.56 - depth) * 5), (int)(1 / (.56 - depth) * 25));
		}
	}
	
	public void update()
	{
		
	}

}
