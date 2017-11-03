package actors;

import engine.Settings;
import mayflower.Actor;
import mayflower.Mayflower;
import mayflower.MayflowerImage;

public class Label extends Actor
{
	//public Label(String img)
	//{ setImage(img); }

	public Label(MayflowerImage img)
	{ setImage(img); }

	@Override
	public void act()
	{	
		if ( Settings.SOUND && Mayflower.mouseClicked(this) )
			Mayflower.playSound("assets/snd/beep.ogg");
		
		getWorld().removeObjects( getIntersectingObjects( Star.class ) );
	}
}
