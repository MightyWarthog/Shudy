package actors;

import java.util.List;

import worlds.Level1;

public class Grunt extends ShudyActor
{
	private boolean menu;
	
	public Grunt(int h, int s, String img, boolean m)
	{
		health = h;
		speed = s;
		setImage(img);
		menu = m;
	}
	
	public Grunt(int h, int s, String img)
	{
		this(h, s, img, false);
	}

	@Override
	public void act()
	{
		super.act();
		
		if ( !menu )
		{
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
		else
		{
			List<Grunt> grunts = world.getObjects( Grunt.class );
			if ( grunts.size() > 0 )
			{
				Grunt g = grunts.get( (int) ( Math.random() * grunts.size() ) );
				turnTowards(g);
			
				if ( !intersects(g) )
					move(speed);
				else
					g.damage(1);
			}
		}
	}
	
	@Override
	protected void die()
	{
		if ( !menu )
			((Level1) world).addPoints(100);
		super.die();
	}
}
