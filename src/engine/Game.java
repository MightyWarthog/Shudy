package engine;

import java.io.File;
import java.io.IOException;

import org.ini4j.Wini;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import mayflower.Mayflower;

import worlds.MainMenu;
import worlds.OptionsWorld;

public class Game extends Mayflower
{
	private static int width;
	private static int height;
	
	private static boolean vsync;
	private static boolean fullscreen;
	private static boolean fps;
	
	private static Wini ini;
	
	private static DisplayMode dm;

	public Game()
	{ super("Shudy", width, height); }
	
	public static void main(String[] args)
	{
		dm = Display.getDisplayMode(); 
		
		File config = new File("settings.ini");
		
		if ( !config.exists() )
			try
			{ config.createNewFile(); }
			catch(IOException e)
			{ e.printStackTrace(); }
		
		try
		{ ini = new Wini(config); }
		catch(IOException e)
		{ e.printStackTrace(); }
		
		if ( ini.isEmpty() )
			try
			{ writeConfig(); }
			catch(IOException e)
			{ e.printStackTrace(); }
		
		readConfig();
		
		new Game();
	}

	@Override
	public void init()
	{
		System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
		
		Display.setVSyncEnabled(vsync);
		
		Mayflower.setFullScreen(fullscreen);
		Mayflower.showFPS(fps);
		
		//Load this into RAM so it's ready when the user presses options
		new OptionsWorld();
		
		setWorld(new MainMenu());
	}
	
	private static void writeConfig() throws IOException
	{
		ini.put( "Display", "width", dm.getWidth() );
		ini.put( "Display", "height", dm.getHeight() );
		ini.put( "Display",  "rate", dm.getFrequency() );
		
		ini.put( "Display",	"vsync", "true" );
		ini.put( "Display", "fullscreen", "true" );
		ini.put( "Display", "showFPS", "true" );
		boolean widescreen = ( (float) dm.getWidth()/(float) dm.getHeight() >= 16.0f/9.0f )? true : false;
		ini.put( "Display", "widescreen", widescreen);
		ini.put( "Display", "scale", "1.00" );
		
		ini.put( "Sound", "enabled", "true");
		
		ini.store();
	}
	
	public static void readConfig()
	{
		width = Integer.parseInt( ini.get("Display", "width") );
		height = Integer.parseInt( ini.get("Display", "height") );
		//refresh = Integer.parseInt( ini.get("Display", "rate") );
		
		vsync = Boolean.parseBoolean( ini.get("Display", "vsync") );
		fullscreen = Boolean.parseBoolean( ini.get("Display", "fullscreen") );
		fps = Boolean.parseBoolean( ini.get("Display", "showFPS") );
	}
}
