package worlds;

import org.lwjgl.opengl.Display;

import engine.Settings;

import actors.ToggleButton;
import actors.WorldChangeButton;

import mayflower.Mayflower;

public class OptionsWorld extends ShudyWorld
{
	public OptionsWorld()
	{		
		ToggleButton vsync = new ToggleButton( "assets/img/buttons/vsync", "vsync", Display.class, "setVSyncEnabled" );
		addObject( vsync, Settings.WIDTH / 2 - 100, 100);
		
		ToggleButton fullscreen = new ToggleButton( "assets/img/buttons/fullscreen", "fullscreen", Mayflower.class, "setFullScreen" );
		addObject( fullscreen, Settings.WIDTH /2 - 100, 200);
		
		ToggleButton fps = new ToggleButton( "assets/img/buttons/fps", "showFPS", Mayflower.class, "showFPS" );
		addObject( fps, Settings.WIDTH / 2 - 100, 300);
		
		ToggleButton widescreen = new ToggleButton( "assets/img/buttons/widescreen", "widescreen" );
		addObject( widescreen, Settings.WIDTH / 2 - 100, 400);
		
		ToggleButton sound = new ToggleButton( "assets/img/buttons/sound", "sound" );
		addObject( sound, Settings.WIDTH / 2 - 100, 500);
		
		WorldChangeButton<MainMenu> back = new WorldChangeButton<MainMenu>( "assets/img/buttons/back.gif", MainMenu.class );
		addObject( back, Settings.WIDTH / 2 - 100, Settings.HEIGHT - 100 );
	}
	
	@Override
	public void act()
	{ super.act(); }
}
