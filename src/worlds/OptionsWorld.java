package worlds;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.SlickException;

import actors.Label;
import actors.ToggleButton;
import actors.WorldChangeButton;
import engine.Utils;

import mayflower.Color;
import mayflower.Mayflower;
import mayflower.MayflowerImage;

public class OptionsWorld extends ShudyWorld
{
	public OptionsWorld()
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
		
		//Label vsyncLabel = new Label( "assets/img/buttons/vsync.gif" );
		//addObject( vsyncLabel, getWidth()/2-100, 100);
		
		//VsyncButton vsyncOn = new VsyncButton( "assets/img/buttons/on.gif", true );
		//addObject( vsyncOn, getWidth()/2-75, 160 );
		
		//VsyncButton vsyncOff = new VsyncButton( "assets/img/buttons/off.gif", false );
		//addObject( vsyncOff, getWidth()/2+25, 160 );
		
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
		
		/*
		VsyncButton vsync = new VsyncButton();
		
		Label fullscreenLabel = new Label( "assets/img/buttons/fullscreen.gif" );
		addObject( fullscreenLabel, getWidth()/2-100, 255 );
		
		FullscreenButton fullscreenOn = new FullscreenButton( "assets/img/buttons/on.gif", true );
		addObject( fullscreenOn, getWidth()/2-75, 315 );
		
		FullscreenButton fullscreenOff = new FullscreenButton( "assets/img/buttons/off.gif", false );
		addObject( fullscreenOff, getWidth()/2+25, 315 );
		
		Label fpsLabel = new Label("assets/img/buttons/fps.gif");
		addObject( fpsLabel, getWidth()/2-100, 410 );
		
		FpsButton fpsOn = new FpsButton( "assets/img/buttons/on.gif", true );
		addObject( fpsOn, getWidth()/2-75, 470 );
		
		FpsButton fpsOff = new FpsButton( "assets/img/buttons/off.gif", false );
		addObject( fpsOff, getWidth()/2+25, 470 );
		
		ToggleButton widescreen = new ToggleButton( "assets/img/buttons/widescreen.gif", "Display", "widescreen" );
		addObject( widescreen, getWidth()/2-100, 565);
		
		ToggleButton sound = new ToggleButton("assets/img/buttons/sound.gif", "Sound", "enabled");
		addObject( sound, getWidth()/2-100, 660);
		*/
		
		WorldChangeButton<MainMenu> back = new WorldChangeButton<MainMenu>( "assets/img/buttons/back.gif", MainMenu.class );
		addObject( back, getWidth()/2-100, getHeight()-100 );
	}
	
	@Override
	public void act()
	{ super.act(); }
}
