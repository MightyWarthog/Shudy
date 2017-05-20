package items;

import actors.LaserParticle;
import actors.MenuRobot;
import actors.ShudyActor;
import engine.Settings;

import mayflower.Actor;
import mayflower.Mayflower;
import mayflower.Timer;
import mayflower.World;

public class LaserBlaster extends Actor implements Weapon
{

	private ShudyActor actor;
	
	private World world;
	
	private Timer cooldown;
	
	private int ammo;
	
	public LaserBlaster(ShudyActor a)
	{
		actor = a;
		cooldown = new Timer();
		ammo = 24;
	}

	@Override
	public void fire()
	{
		if ( world == null )
			world = actor.getWorld();
		
		if ( ammo > 0 && cooldown.isDone() )
		{
			for (byte i = 0; i < 12; i++)
				world.addObject( new Pellet(), actor.getCenterX() - actor.getImage().getWidth() / 2 + (int) ( Math.random() * 72 - 36 ), actor.getCenterY() + (int) ( Math.random() * 72 - 36 ) );
			
			cooldown.set(1000);
			
			if ( !( actor instanceof MenuRobot) && Settings.SOUND )
				Mayflower.playSound( "assets/snd/blaster_" + (int) (Math.random() * 4 + 1) + ".ogg" );
			
			ammo--;
		}
		else if ( ammo <= 0 )
			actor.equip( new SemiLaser(actor) );
	}

	@Override
	public void act()
	{ }

	@Override
	public int getAmmo()
	{ return ammo; }
	
	@Override
	public void setAmmo(int a)
	{ ammo = a; }
	
	private class Pellet extends Actor
	{
		private int moved;
		
		private Pellet()
		{
			setImage("assets/img/actors/shotgun_ammo.gif");
			setRotation( actor.getRotation() );
			moved = 0;
		}

		@Override
		public void act()
		{
			ShudyActor a = getOneIntersectingObject( ShudyActor.class );
			if ( a != null && !a.equals(actor) )
			{
				a.damage( 6 );
				die();
			}
			
			move( 15 );
			moved += 15;
			
			if (moved >= 384)
				world.removeObject(this);
		}
		
		private void die()
		{
			for ( int i = 0; i < 32; i++ )			
				world.addObject( new LaserParticle(), getX()-2, getY() );
			
			world.removeObject(this);
		}
	}
}
