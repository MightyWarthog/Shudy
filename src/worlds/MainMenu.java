package worlds;

import org.newdawn.slick.SlickException;

import actors.QuitButton;
import actors.Star;
import actors.WorldChangeButton;
import engine.Utils;

import mayflower.Mayflower;
import mayflower.Timer;

public class MainMenu extends ShudyWorld
{
	private Timer t;
	
	public MainMenu()
	{
		if ( WIDESCREEN )
			try
			{ setBackground( Utils.getScaledImage("assets/img/worlds/Level1_169.png", getWidth(), getHeight()) ); }
			catch(SlickException e)
			{ e.printStackTrace(); }
		else
			try
			{ setBackground( Utils.getScaledImage("assets/img/worlds/Level1_43.png", getWidth(), getHeight()) ); }
			catch(SlickException e)
			{ e.printStackTrace(); }
		
		QuitButton quit = new QuitButton("assets/img/buttons/quit.gif");
		addObject( quit, getWidth()/2-100, getHeight()-100 );
		
		WorldChangeButton<Level1> play = new WorldChangeButton<Level1>( "assets/img/buttons/play.gif", Level1.class);
		addObject( play, getWidth()/2-100, 100);
		
		WorldChangeButton<OptionsWorld> options = new WorldChangeButton<OptionsWorld>( "assets/img/buttons/options.gif", OptionsWorld.class );
		addObject( options, getWidth()/2-100, getHeight()-200 );
		
		t = new Timer();
		
		for ( int i = 0; i < 1024; i++ )
			addObject( new Star(), Mayflower.getRandomNumber(getWidth()), Mayflower.getRandomNumber(getHeight()) );
	}
	
	@Override
	public void act()
	{
		super.act();
		
		final String[] sounds = {"assets/snd/menu_1.wav"};
		int index = Mayflower.getRandomNumber(sounds.length);
		
		if ( SOUND && !Utils.isPlaying && t.isDone() )
		{
				t.set(133000);
				Mayflower.playSound(sounds[index]);
				Utils.isPlaying = true;
		}
	}
}
