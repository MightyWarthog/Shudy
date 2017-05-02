package actors;

import org.lwjgl.opengl.Display;

import mayflower.Mayflower;

public class FpsButton extends ToggleButton
{
	private boolean b;
	
	public FpsButton()
	{
		super("assets/img/buttons/empty.png", "Display", "showFPS");
		
		b = Boolean.parseBoolean( ini.get("Display", "showFPS") );
	}
	
	@Override
	public void act()
	{
		super.act();
		
		if ( b )
			setImage( "assets/img/buttons/fps_on.gif" );
		else
			setImage( "assets/img/buttons/fps_off.gif" );
		
		//if ( Mayflower.mouseClicked(this) )
			//Display.setVSyncEnabled(!b);
	}
}
