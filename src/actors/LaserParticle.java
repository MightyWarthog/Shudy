package actors;

import java.util.ArrayList;

import mayflower.Actor;

public class LaserParticle extends Actor
{
	ArrayList<Integer> randomNumbers;
	
	int moved;
	
	public LaserParticle()
	{
		final String[] images = { "1x1red.gif", "1x1red.gif", "1x1red.gif", "1x1red.gif", "3x3red.gif", "3x3red.gif", "4x4red.gif", "4x4red.gif" };
		
		setImage("assets/img/actors/"+images[ (int) (Math.random() * images.length) ]);
		
		randomNumbers = new ArrayList<Integer>();
		for ( int i = 0; i < 360; i++ )
			randomNumbers.add(i);
		
		int random = randomNumbers.get( (int) (Math.random() * randomNumbers.size()) );
		randomNumbers.remove(random);
		
		setRotation( random );
		moved = 0;
	}

	@Override
	public void act()
	{
		double move = Math.random() * 3;
		
		move( move );
		moved += (int) move+0.5;
		
		if (moved >= 12)
		{
			/*
			try
			{ this.finalize(); }
			catch(Throwable e)
			{ e.printStackTrace(); }
			*/
			
			getWorld().removeObject(this);
		}
	}
}
