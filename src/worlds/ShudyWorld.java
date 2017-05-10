package worlds;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Image;

import actors.Label;
import actors.ProjLaser;
import actors.ShudyActor;
import actors.Star;
import engine.Utils;

import mayflower.Keyboard;
import mayflower.Mayflower;
import mayflower.World;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class ShudyWorld extends World
{
	public static Properties settings = getConfig();

	public static final int WIDTH = Integer.parseInt( settings.getProperty("width") );
	public static final int HEIGHT = Integer.parseInt( settings.getProperty("height") );
	public static final int RATE = Integer.parseInt( settings.getProperty("rate") );

	public static boolean vsync = Boolean.parseBoolean( settings.getProperty("vsync") );
	public static boolean fullscreen = Boolean.parseBoolean( settings.getProperty("fullscreen") );
	public static boolean showFPS = Boolean.parseBoolean( settings.getProperty("showFPS") );
	public static final boolean WIDESCREEN = Boolean.parseBoolean( settings.getProperty("widescreen") );
	public static boolean sound = Boolean.parseBoolean( settings.getProperty("sound") );
	
	public static final Image BACKGROUND = (WIDESCREEN)?
			Utils.getScaledImage("assets/img/worlds/starfield_169.png", WIDTH, HEIGHT) :
			Utils.getScaledImage("assets/img/worlds/starfield_43.png", WIDTH, HEIGHT);

	public ShudyWorld()
	{ this(true); }

	public ShudyWorld(boolean drawStars)
	{
		setBackground(BACKGROUND);

		setPaintOrder(Star.class, ProjLaser.class, ShudyActor.class, Label.class);
		
		if (drawStars && Utils.starActors != null)
			for (Star s : Utils.starActors)
				addObject( s, s.getX(), s.getY() );
		else if ( drawStars )	
		{
			generateStars();
			Utils.starActors = this.getObjects(Star.class);
		}
	}

	@Override
	public void act()
	{
		Display.sync(RATE);
		
		if (Mayflower.isKeyPressed(Keyboard.KEY_END))
		{
			Mayflower.setWorld(new MainMenu());
		}
		
		if ( sound && !Utils.MENU_MUSIC.isPlaying() )
			Utils.MENU_MUSIC.playLoop();
		if ( !sound && Utils.MENU_MUSIC.isPlaying() )
			Utils.MENU_MUSIC.stop();
	}

	private void generateStars()
	{
		for ( int w = 0; w < WIDTH; w += 64 )
			for ( int h = 0; h < HEIGHT; h += 64 )
			{
				Star s = new Star(w + (int) ( Math.random() * 32 ) - 16, h + (int) ( Math.random() * 32 ) - 16);
				addObject( s, s.getX(), s.getY() );
			}
	}

	public static void updateFields()
	{
		//settings = Utils.getConfig("shudy.properties");
		
		vsync = Boolean.parseBoolean( settings.getProperty("vsync") );
		fullscreen = Boolean.parseBoolean( settings.getProperty("fullscreen") );
		showFPS = Boolean.parseBoolean( settings.getProperty("showFPS") );
		sound = Boolean.parseBoolean( settings.getProperty("sound") );
	}
	
	private static Properties getConfig()
	{
		Properties p = new Properties();
		try
		{
			p.load(new FileInputStream("shudy.properties"));
			return p;
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
