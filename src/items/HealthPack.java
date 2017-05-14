package items;

import actors.LaserParticle;
import actors.Player;
import mayflower.Actor;
import mayflower.World;

public class HealthPack extends Actor
{
	private World world;
	
	public HealthPack()
	{ setImage( "assets/img/actors/first_aid.gif" ); }

	@Override
	public void act()
	{
		if ( world == null )
			world = getWorld();
		
		Player p = getOneIntersectingObject(Player.class);
		if ( p == null )
			return;
		
		p.heal(25);
		
		for (byte i = 0; i < 3; i++)
			for ( int k = 0; k < 32; k++ )			
				world.addObject( new LaserParticle(), getX() + (int) ( Math.random() * 64 - 32 ), getY() + (int) ( Math.random() * 64 - 32 ) );
		
		world.removeObject(this);
	}

}
