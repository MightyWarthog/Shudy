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

import org.ini4j.Wini;

public abstract class ShudyWorld extends World
{
	public static Wini INI = Utils.getConfig("settings.ini");

	public static final int WIDTH = Integer.parseInt( INI.get("Display", "width") );
	public static final int HEIGHT = Integer.parseInt( INI.get("Display", "height") );
	public static final int RATE = Integer.parseInt( INI.get("Display", "rate") );

	public static boolean VSYNC = Boolean.parseBoolean( INI.get("Display", "vsync") );
	public static boolean FULLSCREEN = Boolean.parseBoolean( INI.get("Display", "fullscreen") );
	public static boolean SHOWFPS = Boolean.parseBoolean( INI.get("Display", "showFPS") );
	public static final boolean WIDESCREEN = Boolean.parseBoolean( INI.get("Display", "widescreen") );
	public static boolean SOUND = Boolean.parseBoolean( INI.get("Sound", "enabled") );
	
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
		
		if ( SOUND && !Utils.MENU_MUSIC.isPlaying() )
			Utils.MENU_MUSIC.playLoop();
		if ( !SOUND && Utils.MENU_MUSIC.isPlaying() )
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
		INI = Utils.getConfig("settings.ini");
		VSYNC = Boolean.parseBoolean( INI.get("Display", "vsync") );
		FULLSCREEN = Boolean.parseBoolean( INI.get("Display", "fullscreen") );
		SHOWFPS = Boolean.parseBoolean( INI.get("Display", "showFPS") );
		SOUND = Boolean.parseBoolean( INI.get("Sound", "enabled") );
	}
}
