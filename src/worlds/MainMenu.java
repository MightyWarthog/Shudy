package worlds;

import actors.MenuRobot;
import actors.QuitButton;
import actors.WorldChangeButton;
import engine.Settings;
import items.AutoLaser;
import items.LaserBlaster;
import items.SemiLaser;

import mayflower.Mayflower;
import mayflower.Timer;

public class MainMenu extends ShudyWorld
{
	protected static final String[] IMAGES = { "assets/img/actors/enemy.gif", "assets/img/actors/player.gif" };
	
	protected Timer botSpawner;
	
	public MainMenu()
	{
		this(false);
	}
	
	protected MainMenu(boolean b)
	{
		if (b)
			return;
		
		QuitButton quit = new QuitButton("assets/img/buttons/quit.gif");
		addObject( quit, getWidth()/2-100, getHeight()-100 );
		
		WorldChangeButton<Level1> play = new WorldChangeButton<Level1>( "assets/img/buttons/play.gif", Level1.class );
		addObject( play, getWidth()/2-100, 100);
		
		WorldChangeButton<OptionsWorld> options = new WorldChangeButton<OptionsWorld>( "assets/img/buttons/options.gif", OptionsWorld.class );
		addObject( options, getWidth()/2-100, getHeight()-200 );
		
		botSpawner = new Timer();
		
		for (int i = 0; i < 12; i++ )
			addObject( new MenuRobot( IMAGES[ (int) ( Math.random() * 2 ) ] ), Mayflower.getRandomNumber(Settings.WIDTH), Mayflower.getRandomNumber(Settings.HEIGHT) );
	}
	
	@Override
	public void act()
	{
		super.act();
		
		if ( botSpawner.isDone() )
		{
			int r = Mayflower.getRandomNumber(4);
			
			if ( r == 0 )
				addObject( new MenuRobot( IMAGES[ (int) ( Math.random() * 2 ) ] ), Mayflower.getRandomNumber(Settings.WIDTH), Mayflower.getRandomNumber(Settings.HEIGHT) );
			else if ( r == 1 )
			{
				MenuRobot bot = new MenuRobot( IMAGES[ (int) ( Math.random() * 2 ) ] );
				bot.equip( new SemiLaser( bot, 10, 500) );
				addObject( bot, Mayflower.getRandomNumber(Settings.WIDTH), Mayflower.getRandomNumber(Settings.HEIGHT) );
			}
			else if ( r == 2 )
			{
				MenuRobot bot = new MenuRobot( IMAGES[ (int) ( Math.random() * 2 ) ] );
				bot.equip( new AutoLaser( bot, 9, 200 ) );
				addObject( bot, Mayflower.getRandomNumber(Settings.WIDTH), Mayflower.getRandomNumber(Settings.HEIGHT) );
			}
			else
			{
				MenuRobot bot = new MenuRobot( IMAGES[ (int) ( Math.random() * 2 ) ] );
				bot.equip( new LaserBlaster( bot ) );
				addObject( bot, Mayflower.getRandomNumber(Settings.WIDTH), Mayflower.getRandomNumber(Settings.HEIGHT) );
			}
			
			botSpawner.set(500);
		}
	}
}
