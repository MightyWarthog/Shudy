package engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import mayflower.Mayflower;

import worlds.Level1;
import worlds.MainMenu;
import worlds.OptionsWorld;

public class Game extends Mayflower
{
	private static final DisplayMode DISPLAY = Display.getDisplayMode();
	
	private static int width = DISPLAY.getWidth();
	private static int height = DISPLAY.getHeight();
	
	private static boolean vsync = true;
	private static boolean fullscreen = true;
	private static boolean fps = false;

	public Game()
	{ super("Shudy", width, height); }
	
	public static void main(String[] args)
	{
		File config = new File("shudy.properties");
		if ( !config.exists() )
			try
			{ config.createNewFile(); }
			catch(IOException e)
			{ e.printStackTrace(); }
		
		Properties settings = new Properties();
		try
		{ settings.load(new FileInputStream("shudy.properties")); }
		catch(IOException e)
		{ e.printStackTrace(); }
		
		if ( settings.isEmpty() )
			writeConfig(settings);
		else
			readConfig(settings);
		
		new Game();
	}
	
	@Override
	public void init()
	{
		System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
		
		Display.setVSyncEnabled(vsync);
		
		Mayflower.setFullScreen(fullscreen);
		Mayflower.showFPS(fps);
		
		//Load these into RAM so it's ready when the user presses options
		new OptionsWorld();
		new Level1();
		
		setWorld( new MainMenu() );
	}

	private static void readConfig(Properties p)
	{
		width = Integer.parseInt( p.getProperty("width") );
		height = Integer.parseInt( p.getProperty("height") );
		
		vsync = Boolean.parseBoolean( p.getProperty("vsync") );
		fullscreen = Boolean.parseBoolean( p.getProperty("fullscreen") );
		fps = Boolean.parseBoolean( p.getProperty("showFPS") );
	}

	private static void writeConfig(Properties p)
	{
		p.setProperty( "width", String.valueOf(width) );
		p.setProperty( "height", String.valueOf(height) );
		p.setProperty( "rate", String.valueOf( DISPLAY.getFrequency() ) );
		
		p.setProperty( "vsync", String.valueOf(vsync) );
		p.setProperty( "fullscreen", String.valueOf(fullscreen) );
		p.setProperty( "showFPS", String.valueOf(fps) );
		
		String widescreen = ( (float)width/(float)height >= 16.0f/9.0f )? "true" : "false";
		p.setProperty( "widescreen", widescreen );
		
		p.setProperty( "scale", "1.00" );
		
		p.setProperty( "sound", "true" );
		
		try
		{ p.store(new FileOutputStream("shudy.properties"), null); }
		catch(IOException e)
		{ e.printStackTrace(); }
	}
}
