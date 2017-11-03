package items;

import actors.MenuRobot;
import actors.ProjLaser;
import actors.ShudyActor;
import engine.Settings;

import mayflower.Actor;
import mayflower.Mayflower;
import mayflower.MayflowerImage;
import mayflower.Timer;
import mayflower.World;

public class SemiLaser extends Actor implements Weapon
{	
	protected World world;
	
	protected ShudyActor actor;
	
	protected Timer cooldown;
	
	protected int cooldownInMilis;
	
	private int damage;
	
	public SemiLaser( ShudyActor a )
	{ this( a, 10, 175 ); }
	
	public SemiLaser(ShudyActor a, int d, int milis)
	{
		actor = a;
		damage = d;
		cooldown = new Timer();
		cooldownInMilis = milis;
	}
	
	@Override
	public void fire()
	{
		if ( world == null )
			world = actor.getWorld();
		
		if ( cooldown.isDone() )
		{
			ProjLaser laser = new ProjLaser(25, damage, actor, new MayflowerImage("assets/img/actors/laser.gif"));
			laser.setRotation(  actor.getRotation() );
			world.addObject( laser, actor.getCenterX() - actor.getImage().getWidth() / 2, actor.getCenterY() );
			cooldown.set( cooldownInMilis );
			
			if (  !( actor instanceof MenuRobot ) && Settings.SOUND )
				Mayflower.playSound( "assets/snd/laser_" + (int) ( Math.random() * 2 + 1 ) + ".ogg" );
		}
	}

	@Override
	public void act()
	{ }

	@Override
	public int getAmmo()
	{ return -1; }
	
	@Override
	public void setAmmo(int a)
	{ }
}
