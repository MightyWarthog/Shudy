package actors;

import java.util.List;

import items.SemiLaser;
import mayflower.MayflowerImage;
import worlds.Level1;

public class Elite extends Grunt
{
	private int range;
	
	public Elite(int h, int s, int r, MayflowerImage img)
	{
		super(h, s, img);
		range = r;
		weapon = new SemiLaser(this, 5, 500);
	}
	
	@Override
	public void act()
	{
		if ( world == null )
			world = getWorld();
		
		if ( health <= 0)
			die();
		
		List<Player> players = world.getObjects( Player.class );
		
		if ( !players.isEmpty() )
		{
			turnTowards( players.get(0) );
			if ( !getObjectsInRange(range, Player.class).isEmpty() )
				weapon.fire();
			else
				move(speed);
		}
	}
	
	@Override
	protected void die()
	{
		((Level1) world).addPoints(100);
		super.die();
	}
}
