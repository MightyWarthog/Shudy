package worlds;

import actors.Grunt;
import actors.QuitButton;
import actors.WorldChangeButton;
import engine.Settings;

import mayflower.Mayflower;

public class MainMenu extends ShudyWorld
{
	private static final String[] IMAGES = { "assets/img/actors/enemy.gif", "assets/img/actors/player.gif" };
	public MainMenu()
	{
		QuitButton quit = new QuitButton("assets/img/buttons/quit.gif");
		addObject( quit, getWidth()/2-100, getHeight()-100 );
		
		WorldChangeButton<Level1> play = new WorldChangeButton<Level1>( "assets/img/buttons/play.gif", Level1.class );
		addObject( play, getWidth()/2-100, 100);
		
		WorldChangeButton<OptionsWorld> options = new WorldChangeButton<OptionsWorld>( "assets/img/buttons/options.gif", OptionsWorld.class );
		addObject( options, getWidth()/2-100, getHeight()-200 );
		
		for (int i = 0; i < 12; i++ )
			addObject( new Grunt( 50, 5, IMAGES[ (int) ( Math.random() * 2) ], true ), Mayflower.getRandomNumber(Settings.WIDTH), Mayflower.getRandomNumber(Settings.HEIGHT) );
	}
}
