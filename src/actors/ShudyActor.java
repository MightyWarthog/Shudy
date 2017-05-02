package actors;

import mayflower.Actor;
import mayflower.Timer;

public abstract class ShudyActor extends Actor
{
	protected int health;
	protected int speed;
	protected Timer damageTimer;
	
	@Override
	public void act()
	{
		if ( health <= 0)
			die();
		
		speed();
		rotate();
		move();
	}
	
	private void die()
	{ getWorld().removeObject(this); }
	
	protected abstract void move();
	protected abstract void rotate();
	protected abstract void speed();
	
	protected void damage()
	{
		/*
		MayflowerImage img = getImage();
		for (int i = 0; i < 5; i++)
		{
			img.setTransparency(50);
			setImage(img);
			if ( damageTimer.isDone() )
			{
				damageTimer.reset();
				img.setTransparency(100);
				setImage(img);
			}
		}
		*/
		health -= 5;
	}
}
