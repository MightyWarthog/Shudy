package items;

import actors.Player;

import mayflower.Timer;

public class AutoLaser extends SemiLaser implements Weapon
{
	private Timer rate;
	
	public AutoLaser(Player p)
	{
		super( p, 9 );
		rate = new Timer();
	}

	@Override
	public void fire()
	{
		if ( rate.isDone() )
		{
			rate.set(133);
			super.fire();
		}
	}

}
