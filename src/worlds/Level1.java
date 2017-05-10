package worlds;

import actors.Grunt;
import actors.Player;

import mayflower.Actor;
import mayflower.Color;
import mayflower.Mayflower;
import mayflower.MayflowerImage;
import mayflower.Timer;

public class Level1 extends ShudyWorld
{
	protected int points;
	
	private textLabel scoreCard;
	private textLabel healthCard;
	
	private Player karel;
	
	private Timer spawner;
	
	public Level1()
	{
		points = 0;
		
		spawner = new Timer(3000);
		
		karel = new Player();
		addObject(karel, 400, 300);
		
		//Grunt hector = new Grunt(100, 10, "assets/img/actors/player.gif");
		//addObject(hector, 600, 200);
		
		//Grunt god = new Grunt(Integer.MAX_VALUE, 5, "assets/img/monster.png");
		//addObject(god, 800, 400);
		
		//for (int i = 0; i < 4; i++)
		//	addObject( new Grunt(50, 10, "assets/img/cookie.png"), Mayflower.getRandomNumber(getWidth()), Mayflower.getRandomNumber(getHeight()) );
		
		scoreCard = new textLabel( new MayflowerImage("Score: " + points, 36, new Color(0, 136, 0) ) );
		addObject( scoreCard, getWidth() / 2 + 300, getHeight()-50 );
		
		healthCard = new textLabel( new MayflowerImage("Health: " + karel.getHealth(), 36, new Color(0, 136, 0) ) );
		addObject( healthCard, getWidth() / 2 - 300, getHeight()-50 );
	}
	
	@Override
	public void act()
	{
		super.act();
		
		scoreCard.setImage( new MayflowerImage("Score: " + points, 36, new Color(0, 136, 0) ) );
		healthCard.setImage( new MayflowerImage("Health: " + karel.getHealth(), 36, new Color(0, 136, 0) ) );
		
		if ( spawner.isDone() )
		{
			addObject( new Grunt(50, 10, "assets/img/actors/enemy.gif"), Mayflower.getRandomNumber(WIDTH), Mayflower.getRandomNumber(HEIGHT) );
			spawner.set(3000);
		}
	}
	
	public void addPoints(int p)
	{ points += p; }
	
	private class textLabel extends Actor
	{
		public textLabel(MayflowerImage img)
		{ setImage(img); }
		
		@Override
		public void act()
		{ }
	}
}