package actors;

import mayflower.Actor;
import mayflower.MayflowerImage;

public class Star extends Actor
{
	private boolean b;
	private boolean bb;
	
	private int x;
	private int y;
	
	private MayflowerImage img;
	
	private final int TRANSPARENCY = (int) ( Math.random() * 90 ) + 10;
	private final int DELTA = (int) ( Math.random() * 4 + 1 );
	
	public Star(int x, int y)
	{
		setRotation( (int) ( Math.random() * 361 ) );
		
		switch( TRANSPARENCY % 4 )
		{
			case 0:
				img = new MayflowerImage("assets/img/actors/star32x32.gif");
				break;
			default:
				img = new MayflowerImage("assets/img/actors/star16x16.gif");
				break;
		}
		
		img.setTransparency(TRANSPARENCY);
		setImage(img);
		
		b = ( TRANSPARENCY  % 2 == 0 )? true : false;
		bb = b;
		
		this.x = x;
		this.y = y;
	}

	@Override
	public void act()
	{
		if (b)
			setRotation( getRotation() + (int) ( Math.random() * 4 ) + 1 );
		else
			setRotation( getRotation() - (int) ( Math.random() * 4 ) + 1 );
		
		if ( img.getTransparency() >= 100 || img.getTransparency() <= 0 )
			bb = !bb;
		
		if ( bb )
			img.setTransparency( img.getTransparency() + DELTA );
		else
			img.setTransparency( img.getTransparency() - DELTA );
	}
	
	@Override
	public int getX()
	{ return x; }
	
	@Override
	public int getY()
	{ return y; }
	
	@Override
	public void setLocation(double x, double y)
	{
		super.setLocation(x, y);
		this.x = (int) ( x + 0.5 );
		this.y = (int) ( y + 0.5 );
	}
}
