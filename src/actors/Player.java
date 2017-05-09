package actors;

import mayflower.Keyboard;
import mayflower.Mayflower;

public class Player extends ShudyActor
{
	static final String LASER_SOUNDS[] = {"assets/snd/laser1.wav", "assets/snd/laser2.wav"};
	
	public Player()
	{
		setImage("assets/img/actors/player.gif");
		speed = 10;
		health = 100;
	}
	
	@Override
	public void act()
	{
		super.act();
		
		// have the robot look towards the mouse
		rotate();
		
		// process movement (WASD)
		move();
		
		// speed control (ARROW_UP/ARROW_DOWN)
		speed();
		
		if ( Mayflower.mousePressed( null ) )
		{
			ProjLaser laser = new ProjLaser(25);
			laser.setRotation(getRotation());
			
			getWorld().addObject(laser, getCenterX()-getImage().getWidth()/2, getCenterY());
			Mayflower.playSound( LASER_SOUNDS[(int) (Math.random() * 2)] );
		}
	}
	
	protected void move()
	{
		if ( Mayflower.isKeyDown(Keyboard.KEY_W) )
			setLocation(getX(), getY() - speed);
		
		if ( Mayflower.isKeyDown(Keyboard.KEY_A) )
			setLocation(getX() - speed, getY());
		
		if ( Mayflower.isKeyDown(Keyboard.KEY_S) )
			setLocation(getX(), getY() + speed);
		
		if ( Mayflower.isKeyDown(Keyboard.KEY_D) )
			setLocation(getX() + speed, getY());
	}
	
	protected void rotate()
	{
		// thanks to Vishnu for helping me figure this out!
		setRotation( (int) ( Math.toDegrees( Math.atan2(
				Mayflower.getMouseInfo().getY() - getCenterY(),
				Mayflower.getMouseInfo().getX() - getCenterX() ) ) ) );
	}
	
	protected void speed()
	{
		if ( Mayflower.isKeyPressed(Keyboard.KEY_UP) && speed < 20 )
			speed++;
		
		if ( Mayflower.isKeyPressed(Keyboard.KEY_DOWN) && speed > 0)
			speed--;
	}

	public int getHealth()
	{ return health; }
}
