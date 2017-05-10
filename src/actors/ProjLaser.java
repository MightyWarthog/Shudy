package actors;

import java.util.List;

import mayflower.Actor;
import mayflower.World;

public class ProjLaser extends Actor
{
	private int speed;
	
	public ProjLaser(int s)
	{
		speed = s;
		setImage("assets/img/actors/laser.gif");
	}
	
	public void act()
	{
		move(speed);
		
		List<Grunt> enemies = getWorld().getObjects(Grunt.class);
		
		for ( Grunt e : enemies )
			if ( intersects(e) )
			{
				e.damage(5);
				die();
			}
		if ( this.isAtEdge() )
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
	
	private void die()
	{
		int x = getX();
		int y = getY();
		World w = getWorld();
		
		if( w != null )
		{
			for ( int i = 0; i < 32; i++ )			
				w.addObject( new LaserParticle(), x-2, y );

			try
			{ this.finalize(); }
			catch(Throwable e)
			{ e.printStackTrace(); }
			
			getWorld().removeObject(this);
		}
	}
}
