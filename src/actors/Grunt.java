package actors;

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
		//if ( isTouching(ProjLaser.class) )
		//	super.damage();
	}

	@Override
	protected void move()
	{
		//TODO: AI
	}

	@Override
	protected void rotate()
	{
		//TODO: AI
	}

	@Override
	protected void speed()
	{
		//TODO: min/max
	}
}
