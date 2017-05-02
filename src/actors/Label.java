package actors;

import mayflower.Actor;
import mayflower.Mayflower;
import mayflower.MayflowerImage;
import worlds.ShudyWorld;

public class Label extends Actor
{
	public Label(String img)
	{ setImage(img); }

	public Label(MayflowerImage img)
	{ setImage(img); }

	@Override
	public void act()
	{	
		if ( ShudyWorld.SOUND && Mayflower.mouseClicked(this) )
			Mayflower.playSound("assets/snd/beep.wav");
	}
}
