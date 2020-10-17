package core;

import org.newdawn.slick.Color;

public class Dirt extends Terrain
{
	float growthFactor;
	float grassMax = 32;
	float grassLevel;
	
	public Dirt(Cell owner, float percent)
	{
		super(owner);
		grassLevel = grassMax;
		growthFactor = percent;
		color = new Color(0, (int) (144 / growthFactor - 54), 0);
		color = new Color(0,(int)(144 / growthFactor - 54 - grassLevel * 2), 0);
	}
	
	public void update()
	{
		if(grassLevel < grassMax) { grassLevel += .15f; }
		color = new Color(0,(int)(144 / growthFactor - 54 - grassLevel * 2), 0);
	}

}
