package worlds;

import actors.QuitButton;
import actors.WorldChangeButton;
import engine.Utils;

public class MainMenu extends ShudyWorld
{
	public MainMenu()
	{		
		QuitButton quit = new QuitButton("assets/img/buttons/quit.gif");
		addObject( quit, getWidth()/2-100, getHeight()-100 );
		
		WorldChangeButton<Level1> play = new WorldChangeButton<Level1>( "assets/img/buttons/play.gif", Level1.class );
		addObject( play, getWidth()/2-100, 100);
		
		WorldChangeButton<OptionsWorld> options = new WorldChangeButton<OptionsWorld>( "assets/img/buttons/options.gif", OptionsWorld.class );
		addObject( options, getWidth()/2-100, getHeight()-200 );
	}
	
	@Override
	public void act()
	{
		super.act();
		
		if ( SOUND && !Utils.MENU_MUSIC.isPlaying() )
			Utils.MENU_MUSIC.playLoop();
		if ( !SOUND && Utils.MENU_MUSIC.isPlaying() )
			Utils.MENU_MUSIC.stop();
	}
}
