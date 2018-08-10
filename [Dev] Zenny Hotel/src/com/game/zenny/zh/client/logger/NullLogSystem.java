package com.game.zenny.zh.client.logger;

import org.newdawn.slick.util.LogSystem;

public class NullLogSystem implements LogSystem {

	@Override
	public void debug(String arg0) {}

	@Override
	public void error(Throwable arg0) {}

	@Override
	public void error(String arg0) {}

	@Override
	public void error(String arg0, Throwable arg1) {}

	@Override
	public void info(String arg0) {}

	@Override
	public void warn(String arg0) {}

	@Override
	public void warn(String arg0, Throwable arg1) {}

}
