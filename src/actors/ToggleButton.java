package actors;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import worlds.ShudyWorld;

import engine.Utils;
import mayflower.Mayflower;

public class ToggleButton extends Label
{
	private String section;
	private String setting;
	private String extra;
	private String img;
	
	private boolean b;
	
	private Class<?> c;
	
	public ToggleButton(String img, String section, String setting)
	{
		super("assets/img/buttons/empty.gif");
		
		this.section = section;
		this.setting = setting;
		this.img = img;
		
		b = Boolean.parseBoolean( ShudyWorld.INI.get(section, setting) );
		
		if ( b )
			setImage(img+"_on.gif");
		else
			setImage(img+"_off.gif");
	}
	
	public ToggleButton(String img, String section, String setting, Class<?> c, String extra)
	{
		this(img, section, setting);
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
			
			ShudyWorld.INI.put( section, setting, b );
			
			Utils.saveConfig(ShudyWorld.INI);
			
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
