package actors;

import mayflower.Actor;

public abstract class ShudyActor extends Actor
{
	protected int health;
	protected int speed;
	
	@Override
	public void act()
	{
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
