package actors;

import mayflower.Mayflower;
import worlds.ShudyWorld;

public class WorldChangeButton<T> extends Label
{

	private Class<T> world;
	
	public WorldChangeButton(String img, Class<T> world)
	{
		super(img);
		this.world = world;
	}

	@Override
	public void act()
	{
		super.act();
		
		if ( Mayflower.mouseClicked(this) )
			try
			{ Mayflower.setWorld( (ShudyWorld) world.newInstance()  ); }
			catch(InstantiationException | IllegalAccessException e)
			{ e.printStackTrace(); }
	}
}
