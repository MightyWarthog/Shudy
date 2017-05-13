package items;

import actors.Player;
import actors.ProjLaser;

import mayflower.Actor;
import mayflower.Mayflower;
import mayflower.World;

public class SemiLaser extends Actor implements Weapon
{
	protected static final String LASER_SOUNDS[] = {"assets/snd/laser_1.ogg", "assets/snd/laser_2.ogg"};
	
	protected World world;
	
	protected Player player;
	
	private int damage;
	
	public SemiLaser( Player p)
	{ this(p, 10); }
	
	protected SemiLaser( Player p, int d )
	{
		player = p;
		damage = d;
	}
	
	@Override
	public void fire()
	{
		if ( world == null )
			world = player.getWorld();
		
		ProjLaser laser = new ProjLaser(25, damage);
		
		laser.setRotation(  player.getRotation() );
		world.addObject( laser, player.getCenterX()-player.getImage().getWidth()/2, player.getCenterY() );
		Mayflower.playSound( LASER_SOUNDS[ (int) (Math.random() * 2) ] );
	}

	@Override
	public void act()
	{
		if ( world == null )
			world = getWorld();
	}

}
