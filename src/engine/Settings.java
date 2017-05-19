package engine;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class Settings
{
	public static final Properties SETTINGS = new Properties()
			{{
				try ( FileInputStream configFile = new FileInputStream("shudy.properties") )
				{ load( configFile ); }
				catch (IOException e)
				{ e.printStackTrace(); }
			}};
	
	public static final int WIDTH = Integer.parseInt( SETTINGS.getProperty("width") );
	public static final int HEIGHT = Integer.parseInt( SETTINGS.getProperty("height") );
	public static final int RATE = Integer.parseInt( SETTINGS.getProperty("rate") );
	
	public static final boolean WIDESCREEN = Boolean.parseBoolean( SETTINGS.getProperty("widescreen") );
	
	public static final String BACKGROUND = (WIDESCREEN)?
			"assets/img/worlds/starfield_169.png" :
			"assets/img/worlds/starfield_43.png";
	
	public static boolean VSYNC = Boolean.parseBoolean( SETTINGS.getProperty("vsync") );
	public static boolean FULLSCREEN = Boolean.parseBoolean( SETTINGS.getProperty("fullscreen") );
	public static boolean SHOWFPS = Boolean.parseBoolean( SETTINGS.getProperty("showFPS") );
	public static boolean SOUND = Boolean.parseBoolean( SETTINGS.getProperty("sound") );
}
