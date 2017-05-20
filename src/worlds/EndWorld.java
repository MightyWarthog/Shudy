package worlds;

import actors.MenuRobot;
import engine.Settings;
import mayflower.Color;
import mayflower.Label;
import mayflower.Mayflower;
import mayflower.Timer;

public class EndWorld extends MainMenu
{	
	public EndWorld()
	{
		super(true);
		
		botSpawner = new Timer();
		
		Label gameOver = new Label( "GAME OVER", 48, new Color(0, 136, 0) );
		addObject( gameOver, Settings.WIDTH / 2 - gameOver.getImage().getWidth() / 2, Settings.HEIGHT / 2 - gameOver.getImage().getHeight() / 2 );
		
		for (int i = 0; i < 12; i++ )
			addObject( new MenuRobot( MainMenu.IMAGES[ (int) ( Math.random() * 2 ) ] ), Mayflower.getRandomNumber(Settings.WIDTH), Mayflower.getRandomNumber(Settings.HEIGHT) );
	}
}
