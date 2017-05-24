package actors;

import mayflower.Actor;

public class LaserParticle extends Actor
{	
	private int moved;
	
	public LaserParticle()
	{
		final String[] images = { "1x1red.gif", "1x1red.gif", "1x1red.gif", "1x1red.gif", "3x3red.gif", "3x3red.gif", "4x4red.gif", "4x4red.gif" };
		
		setImage("assets/img/actors/"+images[ (int) (Math.random() * images.length) ]);
		
		setRotation( (int) ( Math.random() * 360 + 1 ) );
		moved = 0;
	}

	@Override
	public void act()
	{
		double move = Math.random() * 3;
		
		move( move );
		moved += (int) move+0.5;
		
		if (moved >= 12)
			getWorld().removeObject(this);
	}
}
