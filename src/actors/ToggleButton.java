package actors;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.ini4j.Wini;

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
	
	protected Wini ini;
	
	public ToggleButton(String img, String section, String setting)
	{
		super("assets/img/buttons/empty.gif");
		
		this.section = section;
		this.setting = setting;
		this.img = img;
		
		ini = Utils.getConfig("settings.ini");
		
		b = Boolean.parseBoolean( ini.get(section, setting) );
		
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
			
			ini.put( section, setting, b );
			Utils.saveConfig(ini);
			
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
