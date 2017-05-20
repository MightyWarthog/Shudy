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
	{ world.removeObject(this); }
	
	public void damage(int d)
	{ health -= d; }
	
	public void equip(Weapon w)
	{ weapon = w; }
}
