package worlds;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import actors.Label;
import actors.Player;
import actors.ProjLaser;
import actors.ShudyActor;
import actors.Star;
import items.AutoLaser;
import items.LaserBlaster;
import items.SemiLaser;
import engine.Settings;

import mayflower.Keyboard;
import mayflower.Mayflower;
import mayflower.MayflowerMusic;
import mayflower.World;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

public abstract class ShudyWorld extends World
{
	private static final MayflowerMusic MENU_MUSIC = new MayflowerMusic( "assets/snd/menu_1.ogg" );
	
	private static List<Star> stars;
	
	public ShudyWorld()
	{ this(true); }

	public ShudyWorld(boolean drawStars)
	{
		try
		{ setBackground( new Image(Settings.BACKGROUND).getScaledCopy(Settings.WIDTH, Settings.HEIGHT) ); }
		catch (SlickException e)
		{ e.printStackTrace(); }

		setPaintOrder(Star.class, ProjLaser.class, ShudyActor.class, Label.class);
		
		if (drawStars && stars != null)
		{
			for ( Star s : stars )
				addObject( s, s.getX(), s.getY() );
		}
		else if ( drawStars )	
		{
			generateStars();
			stars = getObjects(Star.class);
		}
	}

	@Override
	public void act()
	{
		Display.sync(Settings.RATE);
		
		if (Mayflower.isKeyPressed(Keyboard.KEY_END))
		{
			Mayflower.setWorld(new MainMenu());
		}
		
		if ( Settings.SOUND && !MENU_MUSIC.isPlaying() )
			MENU_MUSIC.playLoop();
		if ( !Settings.SOUND && MENU_MUSIC.isPlaying() )
			MENU_MUSIC.stop();
		
		//Cheatcodes!
		if ( Mayflower.isKeyPressed(0x29) )
		{
			String code =  Mayflower.ask("Enter cheat code:");
			if ( code != null )
				try
				{ doCheat(code); }
				catch(IOException e)
				{ return; }
		}
	}

	private void generateStars()
	{
		for ( int w = 0; w < Settings.WIDTH; w += 64 )
			for ( int h = 0; h < Settings.HEIGHT; h += 64 )
			{
				Star s = new Star( w + (int) ( Math.random() * 32 ) - 16, h + (int) ( Math.random() * 32 ) - 16 );
				addObject( s, s.getX(), s.getY() );
			}
	}

	public static void updateFields()
	{	
		Settings.VSYNC = Boolean.parseBoolean( Settings.SETTINGS.getProperty("vsync") );
		Settings.FULLSCREEN = Boolean.parseBoolean( Settings.SETTINGS.getProperty("fullscreen") );
		Settings.SHOWFPS = Boolean.parseBoolean( Settings.SETTINGS.getProperty("showFPS") );
		Settings.SOUND = Boolean.parseBoolean( Settings.SETTINGS.getProperty("sound") );
	}
	
	private void doCheat(String cheat) throws IOException
	{
		File egg = File.createTempFile( "shudy-cheat", ".ogg" );
		egg.deleteOnExit();
		
		URL sound = null;
			
		if ( Settings.SOUND )
			switch(cheat)
			{
				case "rickroll":
					sound = new URL( "https://upload.wikimedia.org/wikipedia/en/d/d0/Rick_Astley_-_Never_Gonna_Give_You_Up.ogg" );
					break;
				case "FULLCOMMUNISM":
					sound = new URL( "https://upload.wikimedia.org/wikipedia/commons/d/db/Gimn_Sovetskogo_Soyuza_%281977_Vocal%29.oga" );
					break;
			}
		
			if ( sound != null )
			{
				try ( InputStream yolk = sound.openStream() )
				{ Files.copy(yolk, egg.toPath(), StandardCopyOption.REPLACE_EXISTING); }
				
				Mayflower.playSound( egg.getAbsolutePath() );
			}
			
		switch(cheat)
		{
			case "god":
				for ( Player p : getObjects( Player.class ) )
				{
					p.setHealth( Integer.MAX_VALUE );
					p.setAmmo( Integer.MAX_VALUE );
				}
				break;
			case "shotgun":
				for ( Player p : getObjects( Player.class ) )
					p.equip(new LaserBlaster(p) );
				break;
			case "semi":
				for ( Player p : getObjects( Player.class ) )
					p.equip(new SemiLaser(p) );
				break;
			case "auto":
				for ( Player p : getObjects( Player.class ) )
					p.equip(new AutoLaser(p) );
				break;
			case "levelup":
				for ( Player p : getObjects( Player.class ) )
					((Level1) p.getWorld()).addPoints(500);
				break;
		}
	}
}
