package items;

import actors.Player;

public class AutoLaser extends SemiLaser implements Weapon
{
	private int ammo;
	
	public AutoLaser(Player p)
	{
		super( p, 9, 134 );
		ammo = 1000;
	}

	@Override
	public void fire()
	{
		if ( ammo > 0 )
		{
			super.fire();
			ammo--;
		}
		else
			player.equip( new SemiLaser(player) );
	}
	
	@Override
	public int getAmmo()
	{ return ammo; }

	@Override
	public void setAmmo(int a)
	{ ammo = a; }
}
