package actors;

import mayflower.Mayflower;
import mayflower.MayflowerImage;

public class QuitButton extends Label
{

	public QuitButton(MayflowerImage img)
	{
		super(img);
	}

	@Override
	public void act()
	{
		super.act();
		
		if ( Mayflower.mouseClicked(this) )
			Mayflower.exit();
	}
}
