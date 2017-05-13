package actors;

import items.Weapon;
import mayflower.Actor;
import mayflower.World;

public abstract class ShudyActor extends Actor
{
	protected int health;
	protected int speed;
	
	protected World world;
	
	protected Weapon weapon;
	
	@Override
	public void act()
	{
		if ( world == null )
			world = getWorld();
		
		if ( health <= 0)
			die();
	}
	
	protected void die()
	{
		/*
		try
		{ this.finalize(); }
		catch(Throwable e)
		{ e.printStackTrace(); }
		*/
		
		getWorld().removeObject(this);
	}
	
	protected void damage(int d)
	{ health -= d; }
}
