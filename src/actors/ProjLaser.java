package actors;

import mayflower.Actor;
import mayflower.World;

public class ProjLaser extends Actor
{
	private int speed;
	private int damage;
	
	private World world;
	
	public ProjLaser(int s, int d)
	{
		speed = s;
		damage = d;
		setImage("assets/img/actors/laser.gif");
	}
	
	public void act()
	{
		if ( world == null )
			world = getWorld();
		
		move(speed);
		
		for ( Grunt e : world.getObjects(Grunt.class) )
			if ( intersects(e) )
			{
				e.damage( damage );
				die();
			}
	}
	
	private void die()
	{		
			for ( int i = 0; i < 32; i++ )			
				world.addObject( new LaserParticle(), getX()-2, getY() );

			/*
			try
			{ this.finalize(); }
			catch(Throwable e)
			{ e.printStackTrace(); }
			*/
			
			world.removeObject(this);
	}
}
