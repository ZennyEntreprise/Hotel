package personnal.utils.zenny.transmitter.logger;

import java.text.DateFormat;
import java.util.Date;

import personnal.utils.zenny.transmitter.Bridge;

public class Logger {

	//// STATIC
	// -- LOGGER
	public static boolean activated = true;

	private static DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM);

	/**
	 * @param logType
	 * @param message
	 */
	public static void logInConsole(LogType logType, String message) {
		if (!activated)
			return;

		String log = "(" + dateFormat.format(new Date()) + ") " + logType.getPrefix() + " " + message;
		System.out.println(log);
	}

	/**
	 * @param bridge
	 * @param logType
	 * @param message
	 */
	public static void log(Bridge bridge, LogType logType, String message) {
		logInConsole(logType, message);
	}

}