package actors;

import mayflower.Mayflower;

public class FullscreenButton extends ToggleButton
{
	private boolean b;
	
	public FullscreenButton(String img, boolean b)
	{
		super(img, "Display", "fullscreen");
		this.b = b;
		
	}
	
	@Override
	public void act()
	{
		super.act();
		
		if ( Mayflower.mouseClicked(this) )
			Mayflower.setFullScreen(b);
	}
}
