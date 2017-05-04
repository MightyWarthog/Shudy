package engine;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.ini4j.Wini;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import actors.Star;

public class Utils
{
	public static boolean isPlaying;
	
	public static List<Star> starActors;

	public static Image getScaledImage(String img, int width, int height)
	{
		try
		{ return new Image(img).getScaledCopy(width, height); }
		catch(SlickException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static Wini getConfig(String file)
	{
		try
		{ return new Wini( new File(file) ); }
		catch(IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static void saveConfig(Wini ini)
	{
		try
		{ ini.store(); }
		catch(IOException e)
		{ e.printStackTrace(); }
	}
}
