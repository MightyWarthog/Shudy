package worlds;

import org.lwjgl.opengl.Display;

import actors.Label;
import actors.ProjLaser;
import actors.ShudyActor;
import actors.Star;
import engine.Utils;

import mayflower.World;

import org.ini4j.Wini;

public abstract class ShudyWorld extends World
{
	private static final Wini ini = Utils.getConfig("settings.ini");

	public static final int WIDTH = Integer.parseInt( ini.get("Display", "width") );
	public static final int HEIGHT = Integer.parseInt( ini.get("Display", "height") );
	public static final int RATE = Integer.parseInt( ini.get("Display", "rate") );

	public static final boolean VSYNC = Boolean.parseBoolean( ini.get("Display", "vsync") );
	public static final boolean FULLSCREEN = Boolean.parseBoolean( ini.get("Display", "fullscreen") );
	public static final boolean FPS = Boolean.parseBoolean( ini.get("Display", "showFPS") );
	public static final boolean WIDESCREEN = Boolean.parseBoolean( ini.get("Display", "widescreen") );
	public static final boolean SOUND = Boolean.parseBoolean( ini.get("Sound", "enabled") );

	public ShudyWorld()
	{ this(true); }

	public ShudyWorld(boolean drawStars)
	{
		setPaintOrder(Star.class, ProjLaser.class, ShudyActor.class, Label.class);

		if (drawStars)
			generateStars();
	}

	@Override
	public void act()
	{ Display.sync(RATE); }

	private void generateStars()
	{
		for ( int w = 0; w < WIDTH; w += 64 )
			for ( int h = 0; h < HEIGHT; h += 64 )
			{
				Star s = new Star(w + (int) ( Math.random() * 32 ) - 16, h + (int) ( Math.random() * 32 ) - 16);
				addObject( s, s.getX(), s.getY() );
			}
	}
}
