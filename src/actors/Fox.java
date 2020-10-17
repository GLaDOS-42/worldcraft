package actors;

import core.Cell;
import support.Values;

public class Fox extends Animal
{
	public Fox(Cell owner)
	{
		super(owner);
		setImage("res/defaultImage.png");
		w = Values.CELL_SIZE;
		h = Values.CELL_SIZE;
		vision = Values.FOX_VISION;
		vel = 3;
	}
}
