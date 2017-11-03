package worlds;

import actors.Elite;
import actors.Grunt;
import actors.Player;
import engine.Settings;
import items.AutoLaser;
import items.HealthPack;
import items.LaserBlaster;
import mayflower.Color;
import mayflower.Label;
import mayflower.Mayflower;
import mayflower.MayflowerImage;
import mayflower.Timer;

public class Level1 extends ShudyWorld
{
	protected int points;
	
	private Label scoreCard;
	private Label healthCard;
	private Label ammoCard;
	
	private Player karel;
	
	private Timer spawner;
	private Timer medkit;
	
	private MayflowerImage enemyTexture;
	
	public Level1()
	{
		points = 0;
		
		spawner = new Timer(4000);
		medkit = new Timer(30000);
		
		karel = new Player();
		addObject(karel, 400, 300);
		
		scoreCard = new Label( "Score: " + points, 36, new Color(0, 136, 0) );
		addObject( scoreCard, Settings.WIDTH / 2 + 300, Settings.HEIGHT - 50 );
		
		healthCard = new Label( "Health: " + karel.getHealth(), 36, new Color(0, 136, 0) );
		addObject( healthCard, Settings.WIDTH / 2 - 300, Settings.HEIGHT - 50 );
		
		ammoCard = new Label( "Ammo: \u221e", 36, new Color(0, 136, 0) );
		addObject( ammoCard, 200, Settings.HEIGHT - 50 );
		
		enemyTexture = new MayflowerImage("assets/img/actors/enemy.gif");
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
			if ( points < 1000 )
				addObject( new Grunt( 50, 10, enemyTexture ), Mayflower.getRandomNumber( Settings.WIDTH ), Mayflower.getRandomNumber( Settings.HEIGHT ) );
			else if ( points < 2000)
				addObject( new Elite( 50, 8, 768, enemyTexture ), Mayflower.getRandomNumber( Settings.WIDTH ), Mayflower.getRandomNumber( Settings.HEIGHT ) );
			else if ( points <= 2500 )
			{
				Elite e = new Elite( 50, 8, 512, enemyTexture );
				e.equip( new AutoLaser(e, 2, 200) );
				addObject( e, Mayflower.getRandomNumber( Settings.WIDTH ), Mayflower.getRandomNumber( Settings.HEIGHT ) );
			}
			else
			{
				Elite e = new Elite( 50, 8, 384, enemyTexture );
				e.equip( new LaserBlaster(e) );
				addObject( e, Mayflower.getRandomNumber( Settings.WIDTH ), Mayflower.getRandomNumber( Settings.HEIGHT ) );
			}
			
			spawner.reset();
		}
		
		if ( medkit.isDone() )
		{
			if ( Mayflower.getRandomNumber(2) == 1 )
				addObject( new HealthPack(), Mayflower.getRandomNumber(Settings.WIDTH), Mayflower.getRandomNumber(Settings.HEIGHT) );
			medkit.reset();
		}
	}
	
	public void addPoints(int p)
	{ points += p; }	
}