package com.game.zenny.zh.client.net.exception;

import com.game.zenny.zh.client.logger.LogType;
import com.game.zenny.zh.client.logger.Logger;

public class InvalidPacketConstructorException extends Exception {

	//// OBJECT
	// -- InvalidPacketConstructorException
	private static final long serialVersionUID = -750436884354575485L;

	/**
	 * @param none
	 */
	public InvalidPacketConstructorException() {
	}

	/**
	 * @param message
	 */
	public InvalidPacketConstructorException(String message) {
		super(message);

		Logger.logInConsole(LogType.WARNING, message);
	}

}
