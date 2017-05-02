package actors;

import mayflower.Mayflower;

public class QuitButton extends Label
{

	public QuitButton(String img)
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
