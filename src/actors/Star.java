package actors;

import mayflower.Actor;

public class Star extends Actor
{
	boolean b;
	//boolean enlarge;
	
	public Star()
	{
		this("assets/img/actors/star.gif");
	}
	
	public Star(String img)
	{
		setImage(img);
		setRotation( (int) ( Math.random() * 361 ) );
		
		final int transparency = (int) ( Math.random() *90 )+10;
		getImage().setTransparency(transparency);
		
		b = ( transparency  % 2 == 0 )? true : false;
		//enlarge = b;
	}

	@Override
	public void act()
	{
		if (b)
			setRotation( getRotation()+1 );
		else
			setRotation( getRotation()-1 );
		
		/*
		MayflowerImage scaled = getImage();
		if (enlarge)
		{
			scaled.scale(scaled.getWidth()+1, scaled.getHeight()+1);
			setLocation( getX()-1, getY()-1 );
		}
		else
		{
			scaled.scale(scaled.getWidth()-1, scaled.getHeight()-1);
			setLocation( getX()+1, getY()+1 );
		}
		setImage(scaled);
		*/
		
		//enlarge = ( (int) ( Math.random() * 32 ) == 31 )? !enlarge : enlarge;
	}
}
