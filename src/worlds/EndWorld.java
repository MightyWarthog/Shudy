package worlds;

import engine.Settings;
import mayflower.Color;
import mayflower.Label;

public class EndWorld extends ShudyWorld
{

	public EndWorld()
	{
		Label gameOver = new Label( "GAME OVER", 48, new Color(0, 136, 0) );
		addObject( gameOver, Settings.WIDTH / 2 - gameOver.getImage().getWidth() / 2, Settings.HEIGHT / 2 - gameOver.getImage().getHeight() / 2 );
	}

	/*
	@Override
	public void act()
	{

	}
	*/

}
