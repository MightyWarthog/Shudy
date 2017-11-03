package actors;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import engine.Settings;
import worlds.ShudyWorld;

import mayflower.Mayflower;
import mayflower.MayflowerImage;

//Not fixing this probably

public class ToggleButton extends Label
{
	private String setting;
	private String extra;
	private String img;
	
	private boolean b;
	
	private Class<?> c;
	
	public ToggleButton(String img, String setting)
	{
		super(new MayflowerImage(0, 0));
		
		this.setting = setting;
		this.img = img;
		
		b = Boolean.parseBoolean( Settings.SETTINGS.getProperty(setting) );
		
		if ( b )
			setImage(img+"_on.gif");
		else
			setImage(img+"_off.gif");
	}
	
	public ToggleButton(String img, String setting, Class<?> c, String extra)
	{
		this(img, setting);
		this.extra = extra;
		this.c = c;
	}

	@Override
	public void act()
	{
		super.act();
		
		if ( Mayflower.mouseClicked(this) )
		{
			b = !b;
			
			Settings.SETTINGS.setProperty( setting, String.valueOf(b) );
			
			try ( FileOutputStream config = new FileOutputStream("shudy.properties") )
			{ Settings.SETTINGS.store( config, null); }
			catch(IOException e)
			{ e.printStackTrace(); }
			
			ShudyWorld.updateFields();
			
			if ( b )
				setImage(img+"_on.gif");
			else
				setImage(img+"_off.gif");
			
			if ( extra != null )
			{
				Method extraMethod;
				try
				{ extraMethod = c.getDeclaredMethod(extra, boolean.class); }
				catch(NoSuchMethodException | SecurityException e)
				{
					e.printStackTrace();
					extraMethod = null;
				}
				
				try
				{ extraMethod.invoke( null, b ); }
				catch(InvocationTargetException | IllegalAccessException | IllegalArgumentException e)
				{ e.printStackTrace(); }
			}
		}
	}
}
