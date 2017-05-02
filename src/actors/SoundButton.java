package actors;

import java.io.File;
import java.io.IOException;

import org.ini4j.Wini;

import mayflower.Mayflower;

public class SoundButton extends ToggleButton
{
	private boolean b;
	
	public SoundButton(String img, boolean b)
	{
		super(img, "Sound", "enabled");
		this.b = b;
		
	}
	
	@Override
	public void act()
	{
		super.act();
		
		if ( Mayflower.mouseClicked(this) )
		{
			Mayflower.setFullScreen(b);
			
			Wini ini;
			try
			{ ini = new Wini( new File("settings.ini") ); }
			catch (IOException e)
			{
				e.printStackTrace();
				ini = null;
			}
			
			ini.put( "Sound", "enabled", b );
			
			try
			{ ini.store(); }
			catch (IOException e)
			{ e.printStackTrace(); }
		}
	}
}
