package actors;

import java.util.List;

import mayflower.MayflowerImage;
import worlds.Level1;

public class Grunt extends ShudyActor
{
	public Grunt(int h, int s, MayflowerImage img)
	{
		health = h;
		speed = s;
		setImage(img);
	}

	@Override
	public void act()
	{
		super.act();

		List<Player> players = world.getObjects( Player.class );
		if ( players.size() > 0 )
		{
			Player p = players.get(0);
			turnTowards(p);
			
			if ( !intersects(p) )
				move(speed);
			else
				p.damage(1);
		}
	}

	@Override
	protected void die()
	{
		((Level1) world).addPoints(100);
		super.die();
	}
}
