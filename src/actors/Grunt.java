package actors;

import java.util.List;

import worlds.ShudyWorld;

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
		
		List<Player> players = getObjectsInRange(ShudyWorld.HEIGHT/2, Player.class);
		if ( players.size() > 0 )
		{
			turnTowards(players.get( (int) (Math.random() * players.size() ) ));
			move(5);
			setRotation( getRotation() + 90 );
		}
	}

	/*
	@Override
	protected void move()
	{
		move(10);
	}

	@Override
	protected void rotate()
	{
		List<Player> players = getObjectsInRange(ShudyWorld.HEIGHT/2, Player.class);
		if ( players.size() > 0 )
		{
			turnTowards(players.get( (int) (Math.random() * players.size() ) ));
			setRotation( getRotation() + 90 );
		}
	}

	@Override
	protected void speed()
	{
		//speed = (Mayflower.getRandomNumber(1) == 1)? speed+5 : speed-5;
	}
	*/
}
