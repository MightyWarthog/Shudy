package worlds;

import actors.Grunt;
import actors.Player;

public class Level1 extends ShudyWorld
{

	public Level1()
	{		
		Player karel = new Player();
		addObject(karel, 400, 300);
		
		Grunt hector = new Grunt(100, 10, "assets/img/actors/player.gif");
		addObject(hector, 600, 200);
		
		Grunt god = new Grunt(Integer.MAX_VALUE, 10, "assets/img/monster.png");
		addObject(god, 800, 400);
	}
	
	@Override
	public void act()
	{ super.act(); }
}