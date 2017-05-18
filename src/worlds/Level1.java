package worlds;

import actors.Grunt;
import actors.Player;
import engine.Settings;
import items.HealthPack;
import mayflower.Color;
import mayflower.Label;
import mayflower.Mayflower;
import mayflower.Timer;

public class Level1 extends ShudyWorld
{
	protected int points;
	
	private Label scoreCard;
	private Label healthCard;
	private Label ammoCard;
	
	private Player karel;
	
	private Timer spawner;
	
	public Level1()
	{
		points = 0;
		
		spawner = new Timer(3000);
		
		karel = new Player();
		addObject(karel, 400, 300);
		
		scoreCard = new Label( "Score: " + points, 36, new Color(0, 136, 0) );
		addObject( scoreCard, getWidth() / 2 + 300, getHeight()-50 );
		
		healthCard = new Label( "Health: " + karel.getHealth(), 36, new Color(0, 136, 0) );
		addObject( healthCard, getWidth() / 2 - 300, getHeight()-50 );
		
		ammoCard = new Label( "Ammo: \u221e", 36, new Color(0, 136, 0) );
		addObject( ammoCard, 200, getHeight()-50 );
		
		addObject( new HealthPack(), 800, 400 );
	}
	
	@Override
	public void act()
	{
		super.act();
		
		healthCard.setText( "Health: " + String.valueOf( karel.getHealth() ) );
		scoreCard.setText( "Score: " + String.valueOf( points ) );
		if ( karel.getAmmo() == -1 )
			ammoCard.setText( "Ammo: \u221e" );
		else
			ammoCard.setText( "Ammo: " + String.valueOf( karel.getAmmo() ) );
		
		if ( spawner.isDone() )
		{
			addObject( new Grunt(50, 10, "assets/img/actors/enemy.gif"), Mayflower.getRandomNumber( Settings.WIDTH ), Mayflower.getRandomNumber( Settings.HEIGHT ) );
			spawner.set(2000);
		}
	}
	
	public void addPoints(int p)
	{ points += p; }	
}