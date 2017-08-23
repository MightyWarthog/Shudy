package actors;

import mayflower.Actor;
import mayflower.World;

public class ProjLaser extends Actor
{
	private int speed;
	private int damage;
	
	private ShudyActor immune;
	
	private World world;
	
	public ProjLaser(int s, int d, ShudyActor a)
	{
		speed = s;
		damage = d;
		setImage("assets/img/actors/laser.gif");
		immune = a;
	}
	
	public void act()
	{
		if ( world == null )
			world = getWorld();
		
		move(speed);
		
		ShudyActor a = getOneIntersectingObject( ShudyActor.class );
		if ( a != null && !a.equals(immune) )
		{
			a.damage( damage );
			die();
		}
	}
	
	private void die()
	{
		for ( int i = 0; i < 32; i++ )			
			world.addObject( new LaserParticle(), getX()-2, getY() );

		world.removeObject(this);
	}
}
