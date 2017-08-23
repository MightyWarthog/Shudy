package items;

import actors.Elite;
import actors.ShudyActor;

public class AutoLaser extends SemiLaser implements Weapon
{
	private int ammo;
	
	public AutoLaser(ShudyActor a)
	{ this ( a, 9 ); }
	
	public AutoLaser(ShudyActor a, int d)
	{ this(a, d, 134); }
	
	public AutoLaser(ShudyActor a, int d, int milis)
	{
		super(a, d, milis);
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
		else if ( actor instanceof Elite )
			super.fire();
		else
			actor.equip( new SemiLaser( actor ) );
	}
	
	@Override
	public int getAmmo()
	{ return ammo; }

	@Override
	public void setAmmo(int a)
	{ ammo = a; }
}
