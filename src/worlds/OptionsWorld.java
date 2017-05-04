package worlds;

import org.lwjgl.opengl.Display;

import actors.Label;
import actors.ToggleButton;
import actors.WorldChangeButton;

import mayflower.Color;
import mayflower.Mayflower;
import mayflower.MayflowerImage;

public class OptionsWorld extends ShudyWorld
{
	public OptionsWorld()
	{		
		ToggleButton vsync = new ToggleButton( "assets/img/buttons/vsync", "Display", "vsync", Display.class, "setVSyncEnabled" );
		addObject( vsync, getWidth()/2-100, 100);
		
		ToggleButton fullscreen = new ToggleButton( "assets/img/buttons/fullscreen", "Display", "fullscreen", Mayflower.class, "setFullScreen" );
		addObject( fullscreen, getWidth()/2-100, 200);
		
		ToggleButton fps = new ToggleButton( "assets/img/buttons/fps", "Display", "showFPS", Mayflower.class, "showFPS" );
		addObject( fps, getWidth()/2-100, 300);
		
		ToggleButton widescreen = new ToggleButton( "assets/img/buttons/widescreen", "Display", "widescreen" );
		addObject( widescreen, getWidth()/2-100, 400);
		
		ToggleButton sound = new ToggleButton( "assets/img/buttons/sound", "Sound", "enabled" );
		addObject( sound, getWidth()/2-100, 500);
		
		MayflowerImage soundNote = new MayflowerImage( "Restart to toggle music.", 24, new Color(0, 136, 0) );
		Label soundLabel = new Label(soundNote);
		addObject( soundLabel, getWidth()/2-soundNote.getWidth()/2, 575 );
		
		WorldChangeButton<MainMenu> back = new WorldChangeButton<MainMenu>( "assets/img/buttons/back.gif", MainMenu.class );
		addObject( back, getWidth()/2-100, getHeight()-100 );
	}
	
	@Override
	public void act()
	{ super.act(); }
}
