package engine;

import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import actors.Star;

import mayflower.MayflowerMusic;

public class Utils
{
	public static List<Star> starActors;
	public static final MayflowerMusic MENU_MUSIC = new MayflowerMusic("assets/snd/menu_1.ogg");

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
}
