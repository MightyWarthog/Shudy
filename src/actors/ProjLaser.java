package actors;

import java.util.List;

import mayflower.Actor;

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
		
		//EnemyActor last;
		for ( Grunt e : enemies )
		{
			//last = e;
			if ( intersects(e) /*&& !e.equals(last)*/ )
			{
				e.damage();
				die();
				//getWorld().removeObject(this);
			}
		}
	}
	
	private void die()
	{
		final Actor[] particles = new Actor[32];
		
		for ( Actor a : particles )
		{
			a = new LaserParticle();
			getWorld().addObject(a, getX()-2, getY());
		}
		getWorld().removeObject(this);
	}
}
