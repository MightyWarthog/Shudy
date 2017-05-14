package items;

import actors.Player;

public class AutoLaser extends SemiLaser implements Weapon
{
	private int ammo;
	
	public AutoLaser(Player p)
	{
		super( p, 9, 134 );
		ammo = 200;
	}

	@Override
	public void fire()
	{
		if ( ammo > 0 )
			super.fire();
		else
			player.equip( new SemiLaser(player) );
	}

}
