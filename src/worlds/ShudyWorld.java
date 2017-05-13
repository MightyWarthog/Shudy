package worlds;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Image;

import actors.Label;
import actors.Player;
import actors.ProjLaser;
import actors.ShudyActor;
import actors.Star;
import engine.Utils;
import items.AutoLaser;
import items.LaserBlaster;
import items.SemiLaser;
import mayflower.Keyboard;
import mayflower.Mayflower;
import mayflower.World;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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
		
		//Cheatcodes!
		if ( Mayflower.isKeyPressed(0x29) )
		{
			String code =  Mayflower.ask("Enter cheat code:");
			if ( code != null )
				try
				{ doCheat(code, ".ogg"); }
				catch(IOException e)
				{ return; }
		}
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
	
	private void doCheat(String cheat, String suffix) throws IOException
	{
		File egg = File.createTempFile("shudy-cheat.", suffix);
		egg.deleteOnExit();
		
		if ( cheat.equals("rickroll") )
		{
			try ( InputStream yolk = new URL("https://upload.wikimedia.org/wikipedia/en/d/d0/Rick_Astley_-_Never_Gonna_Give_You_Up.ogg").openStream() )
			{
				Files.copy(yolk, egg.toPath(), StandardCopyOption.REPLACE_EXISTING);
				yolk.close();
			}
		
			Mayflower.playSound( egg.getAbsolutePath() );
		}
		else if ( cheat.equals("FULLCOMMUNISM") )
		{
			try ( InputStream yolk = new URL("https://upload.wikimedia.org/wikipedia/commons/d/db/Gimn_Sovetskogo_Soyuza_%281977_Vocal%29.oga").openStream() )
			{
				Files.copy(yolk, egg.toPath(), StandardCopyOption.REPLACE_EXISTING);
				yolk.close();
			}
			
			Mayflower.playSound( egg.getAbsolutePath() );
		}
		else if ( cheat.equals("god") )
			for ( Player p : getObjects( Player.class ) )
				p.setHealth( Integer.MAX_VALUE );
		else if ( cheat.equals("shotgun") )
			for ( Player p : getObjects( Player.class ) )
				p.equip(new LaserBlaster(p) );
		else if ( cheat.equals("semi") )
			for ( Player p : getObjects( Player.class ) )
				p.equip(new SemiLaser(p) );
		else if ( cheat.equals("auto") )
			for ( Player p : getObjects( Player.class ) )
				p.equip(new AutoLaser(p) );
	}
}
