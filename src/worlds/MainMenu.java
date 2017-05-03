package worlds;

import org.newdawn.slick.SlickException;

import actors.QuitButton;
import actors.WorldChangeButton;
import engine.Utils;

import mayflower.Mayflower;
import mayflower.Timer;

public class MainMenu extends ShudyWorld
{
	private final Timer t;
	
	public MainMenu()
	{
		if ( WIDESCREEN )
			try
			{ setBackground( Utils.getScaledImage("assets/img/worlds/starfield_169.png", getWidth(), getHeight()) ); }
			catch(SlickException e)
			{ e.printStackTrace(); }
		else
			try
			{ setBackground( Utils.getScaledImage("assets/img/worlds/starfield_43.png", getWidth(), getHeight()) ); }
			catch(SlickException e)
			{ e.printStackTrace(); }
		
		QuitButton quit = new QuitButton("assets/img/buttons/quit.gif");
		addObject( quit, getWidth()/2-100, getHeight()-100 );
		
		WorldChangeButton<Level1> play = new WorldChangeButton<Level1>( "assets/img/buttons/play.gif", Level1.class);
		addObject( play, getWidth()/2-100, 100);
		
		WorldChangeButton<OptionsWorld> options = new WorldChangeButton<OptionsWorld>( "assets/img/buttons/options.gif", OptionsWorld.class );
		addObject( options, getWidth()/2-100, getHeight()-200 );
		
		t = new Timer();
		
		//final ArrayList<Integer> randomX = new ArrayList<Integer>(WIDTH/128) {{ for ( int i = 0; i < WIDTH; i += 8 ) add(i); }};
		//final ArrayList<Integer> randomY = new ArrayList<Integer>(HEIGHT/128) {{ for ( int i = 0; i < HEIGHT; i += 8 ) add(i); }};
		
		//int i = 1024;
		//while ( randomX.size() > 1 && randomY.size() > 1 && i > 0 )
			//addObject( new Star(), randomX.remove( (int) ( Math.random() * randomX.size() ) ), randomY.remove( (int) ( Math.random() * randomY.size() ) ) );
		
		//Collections.shuffle(randomX);
		//Collections.shuffle(randomY);
		
		//while ( randomX.size() > 0 && randomY.size() > 0 )
		//	addObject( new Star(), randomX.remove(0), randomY.remove(0) );
		
		
		//Halton h = new Halton(2, 3);
		//for ( int i = 0; i < 1024; i++ )
		//	addObject(  new Star(), (int) ( Utils.getHaltonNumber(2, i+2) + 0.5 ), (int) ( Utils.getHaltonNumber(2, i+2) + 0.5 ) );
		
		//Random r = new Random( System.nanoTime() );
		//for ( int i = 0; i < 512; i++ )
		//	addObject( new Star(), r.nextInt(WIDTH), r.nextInt(HEIGHT) );
		
		//randIdx = N * i / X + random.nextInt(N / X) + 1;
		
		
		/*
		System.out.println(1);
		
		final int[] randomInts = new Random().ints(2048, 0, HEIGHT).distinct().toArray();
		
		System.out.println(2);
		
		ArrayList<Integer> random = new ArrayList<Integer>(2048) {{ for ( int i : randomInts ) add(i); }};
		
		System.out.println(3);
		
		int i = 1024;
		while ( random.size() > 1 && i > 0 )
		{
			int r1 = (int) ( Math.random() * random.size() -1 );
			int r2 = (int) ( Math.random() * random.size() -1 );
			
			int rr1 = random.get(r1);
			int rr2 = random.get(r2);
			
			System.out.println(5);
			
			random.remove(r1);
			random.remove(r2);
			
			System.out.println(6);
			
			addObject(new Star(), rr1, rr2);
			i--;
		}
		*/
		
		//ArrayList<Integer> random = new ArrayList<Integer>();
		//for (int r = 0; r < WIDTH*HEIGHT; r++)
		//	random.add(r);
		
		//final Star[] stars = new Star[1024];
		
		/*
		for ( int i = 0; i < 1024; i++ )
		{
			int r1 = (int) ( Math.random() * random.size() );
			int r2 = (int) ( Math.random() * random.size() );
			
			int rr1 = random.get(r1);
			int rr2 = random.get(r2);
			
			random.remove(r1);
			random.remove(r2);
			
			addObject( new Star(), rr1, rr2);
		}
		*/
			//stars[i] = new Star();
	}
	
	@Override
	public void act()
	{
		super.act();
		
		//TODO: use better sound API
		final String[] sounds = {"assets/snd/menu_1.wav"};
		int index = Mayflower.getRandomNumber(sounds.length);
		
		if ( SOUND && t.isDone() && !Utils.isPlaying )
		{
				t.set(133000);
				Mayflower.playSound(sounds[index]);
				Utils.isPlaying = true;
		}
	}
}
