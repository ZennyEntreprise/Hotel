package personnal.utils.zenny.transmitter.exception;

import personnal.utils.zenny.transmitter.logger.LogType;
import personnal.utils.zenny.transmitter.logger.Logger;

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
