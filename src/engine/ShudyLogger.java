package engine;

import mayflower.util.Logger;

public final class ShudyLogger implements Logger
{

	public ShudyLogger()
	{
		
	}

	@Override
	public void log(Object o)
	{
		System.out.printf("Logger: %s%n", o);
	}

}
