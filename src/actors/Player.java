package actors;

import mayflower.Keyboard;
import mayflower.Mayflower;

public class Player extends ShudyActor
{
	public Player()
	{
		setImage("assets/img/actors/player.gif");
		speed = 5;
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
			ProjLaser laser = new ProjLaser(5);
			//laser.setRotation( (int)Math.toDegrees( Math.atan2(
			//		Mayflower.getMouseInfo().getY() - getCenterY(),
			//		Mayflower.getMouseInfo().getX() - getCenterX() ) ) );
			laser.setRotation(getRotation()+270);
			
			getWorld().addObject(laser, getCenterX()-getImage().getWidth()/2, getCenterY());
		}
	}
	
	@Override
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
		
		/*
		switch( Mayflower.getKey() )
		{
			case Keyboard.KEY_W:
				setLocation(getX(), getY() - speed);
				break;
			case Keyboard.KEY_A:
				setLocation(getX() - speed, getY());
				break;
			case Keyboard.KEY_S:
				setLocation(getX(), getY() + speed);
				break;
			case Keyboard.KEY_D:
				setLocation(getX() + speed, getY());
				break;
		}
		*/
	}
	
	@Override
	protected void rotate()
	{
		// thanks to Vishnu for helping me figure this out!
		setRotation( (int)Math.toDegrees( Math.atan2(
				Mayflower.getMouseInfo().getY() - getCenterY(),
				Mayflower.getMouseInfo().getX() - getCenterX() ) )+90 );
	}
	
	@Override
	protected void speed()
	{
		if ( Mayflower.isKeyPressed(Keyboard.KEY_UP) && speed < 20 )
			speed++;
		
		if ( Mayflower.isKeyPressed(Keyboard.KEY_DOWN) && speed > 0)
				speed--;
	}
}
