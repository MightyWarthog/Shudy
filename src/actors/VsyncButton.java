package actors;

import org.lwjgl.opengl.Display;

import mayflower.Mayflower;

public class VsyncButton extends ToggleButton
{
	private boolean b;
	
	public VsyncButton()
	{
		super("assets/img/buttons/empty.png", "Display", "vsync");
		
		b = Boolean.parseBoolean( ini.get("Display", "vsync") );
	}
	
	@Override
	public void act()
	{
		super.act();
		
		if ( b )
			setImage( "assets/img/buttons/vsync_on.gif" );
		else
			setImage( "assets/img/buttons/vsync_off.gif" );
		
		if ( Mayflower.mouseClicked(this) )
			Display.setVSyncEnabled(!b);
	}
}
