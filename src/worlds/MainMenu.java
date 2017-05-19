package worlds;

import actors.MenuRobot;
import actors.QuitButton;
import actors.WorldChangeButton;
import engine.Settings;

import mayflower.Mayflower;
import mayflower.Timer;

public class MainMenu extends ShudyWorld
{
	private static final String[] IMAGES = { "assets/img/actors/enemy.gif", "assets/img/actors/player.gif" };
	private Timer botSpawner;
	
	public MainMenu()
	{
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
		if ( botSpawner.isDone() )
		{
			addObject( new MenuRobot( IMAGES[ (int) ( Math.random() * 2 ) ] ), Mayflower.getRandomNumber(Settings.WIDTH), Mayflower.getRandomNumber(Settings.HEIGHT) );
			botSpawner.set(500);
		}
	}
}
