package items;

import worlds.ShudyWorld;
import actors.Grunt;
import actors.LaserParticle;
import actors.Player;

import mayflower.Actor;
import mayflower.Mayflower;
import mayflower.Timer;
import mayflower.World;

public class LaserBlaster extends Actor implements Weapon
{

	private Player player;
	
	private World world;
	
	private Timer cooldown;
	
	private int ammo;
	
	public LaserBlaster(Player p)
	{
		player = p;
		cooldown = new Timer();
		ammo = 24;
	}

	@Override
	public void fire()
	{
		if ( world == null )
			world = player.getWorld();
		
		if ( ammo > 0 && cooldown.isDone() )
		{
			for (byte i = 0; i < 12; i++)
				world.addObject( new Pellet(), player.getCenterX() - player.getImage().getWidth()/2 + (int) (Math.random() * 72 - 36), player.getCenterY() + (int) (Math.random() * 72 - 36) );
			
			cooldown.set(1000);
			
			if ( ShudyWorld.sound )
				Mayflower.playSound( "assets/snd/blaster_" + (int) (Math.random() * 4 + 1) + ".ogg" );
			
			ammo--;
		}
		else if ( ammo <= 0 )
			player.equip( new SemiLaser(player) );
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
			setRotation( player.getRotation() );
			moved = 0;
		}

		@Override
		public void act()
		{
			for ( Grunt e : world.getObjects(Grunt.class) )
				if ( intersects(e) )
				{
					e.damage( 6 );
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
