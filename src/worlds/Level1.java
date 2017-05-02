package worlds;

import org.newdawn.slick.SlickException;

import actors.Grunt;
import actors.Player;
import engine.Utils;
import mayflower.Keyboard;
import mayflower.Mayflower;

public class Level1 extends ShudyWorld
{

	public Level1()
	{
		// scale Level1.png to display width/height
		if ( WIDESCREEN )
			try
			{ setBackground( Utils.getScaledImage("assets/img/worlds/Level1_169.png", getWidth(), getHeight()) ); }
			catch(SlickException e)
			{ e.printStackTrace(); }
		else
			try
			{ setBackground( Utils.getScaledImage("assets/img/worlds/Level1_43.png", getWidth(), getHeight()) ); }
			catch(SlickException e)
			{ e.printStackTrace(); }
		
		Player karel = new Player();
		addObject(karel, 400, 300);
		
		Grunt hector = new Grunt(100, 10, "assets/img/monster.png");
		addObject(hector, 600, 200);
		
		Grunt god = new Grunt(Integer.MAX_VALUE, 10, "assets/img/monster.png");
		addObject(god, 800, 400);
	}
	
	@Override
	public void act()
	{
		super.act();
	
		if ( Mayflower.isKeyPressed(Keyboard.KEY_ESCAPE) )
			Mayflower.exit();
	}
}