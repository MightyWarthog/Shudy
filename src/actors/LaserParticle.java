package actors;

import mayflower.Actor;
import mayflower.MayflowerImage;

public class LaserParticle extends Actor
{	
	private int moved;
	private static final MayflowerImage[] IMAGES = { new MayflowerImage("assets/img/actors/1x1red.gif"), new MayflowerImage("assets/img/actors/1x1red.gif"), new MayflowerImage("assets/img/actors/1x1red.gif"), new MayflowerImage("assets/img/actors/1x1red.gif"), new MayflowerImage("assets/img/actors/3x3red.gif"), new MayflowerImage("assets/img/actors/3x3red.gif"), new MayflowerImage("assets/img/actors/4x4red.gif"), new MayflowerImage("assets/img/actors/4x4red.gif") }; 
	
	public LaserParticle()
	{		
		setImage(IMAGES[ (int) (Math.random() * IMAGES.length) ]);
		
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
