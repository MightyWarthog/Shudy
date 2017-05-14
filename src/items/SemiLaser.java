package items;

import actors.Player;
import actors.ProjLaser;

import mayflower.Actor;
import mayflower.Mayflower;
import mayflower.Timer;
import mayflower.World;

public class SemiLaser extends Actor implements Weapon
{	
	protected World world;
	
	protected Player player;
	
	protected Timer cooldown;
	
	protected int cooldownInMilis;
	
	private int damage;
	
	public SemiLaser( Player p)
	{ this( p, 10, 175 ); }
	
	protected SemiLaser( Player p, int d, int milis )
	{
		player = p;
		damage = d;
		cooldown = new Timer();
		cooldownInMilis = milis;
		world = player.getWorld();
	}
	
	@Override
	public void fire()
	{
		if ( world == null )
			world = player.getWorld();
		
		if ( cooldown.isDone() )
		{
			ProjLaser laser = new ProjLaser(25, damage);
			laser.setRotation(  player.getRotation() );
			world.addObject( laser, player.getCenterX()-player.getImage().getWidth()/2, player.getCenterY() );
			cooldown.set( cooldownInMilis );
			Mayflower.playSound( "assets/snd/laser_" + (int) ( Math.random() * 2 + 1 ) + ".ogg" );
		}
	}

	@Override
	public void act()
	{ }

	@Override
	public int getAmmo()
	{ return -1; }
}
