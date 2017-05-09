package actors;

import java.util.List;

import worlds.Level1;

public class Grunt extends ShudyActor
{
	public Grunt(int h, int s, String img)
	{
		health = h;
		speed = s;
		setImage(img);
	}

	@Override
	public void act()
	{
		super.act();
		
		List<Player> players = getObjectsInRange(Integer.MAX_VALUE, Player.class);
		if ( players.size() > 0 )
		{
			Player p = players.get( (int) ( Math.random() * players.size() ) );
			turnTowards(p);
			
			if ( !intersects(p) )
				move(5);
			else
				p.damage(1);
		}
	}
	
	@Override
	protected void die()
	{
		((Level1) getWorld()).addPoints(100);
		super.die();
	}
}
