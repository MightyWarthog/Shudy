package worlds;

import actors.QuitButton;
import actors.WorldChangeButton;
import engine.Utils;

import mayflower.Mayflower;
import mayflower.Timer;

public class MainMenu extends ShudyWorld
{
	private final Timer t;
	
	public MainMenu()
	{		
		QuitButton quit = new QuitButton("assets/img/buttons/quit.gif");
		addObject( quit, getWidth()/2-100, getHeight()-100 );
		
		WorldChangeButton<Level1> play = new WorldChangeButton<Level1>( "assets/img/buttons/play.gif", Level1.class);
		addObject( play, getWidth()/2-100, 100);
		
		WorldChangeButton<OptionsWorld> options = new WorldChangeButton<OptionsWorld>( "assets/img/buttons/options.gif", OptionsWorld.class );
		addObject( options, getWidth()/2-100, getHeight()-200 );
		
		t = new Timer();
	}
	
	@Override
	public void act()
	{
		super.act();
		
		//TODO: use better sound API
		final String[] sounds = {"assets/snd/menu_1.wav"};
		int index = Mayflower.getRandomNumber(sounds.length);
		
		if ( SOUND && t.isDone() && !Utils.isPlaying )
		{
				t.set(133000);
				Mayflower.playSound(sounds[index]);
				Utils.isPlaying = true;
		}
	}
}
