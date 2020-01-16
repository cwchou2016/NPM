package us.dontcareabout.npm.client.Exception;

import us.dontcareabout.npm.client.ConsoleOut;
import us.dontcareabout.npm.client.DateInfo;
import us.dontcareabout.npm.client.DateInterval;

public class CutDateIntervalException extends Exception {
	public final DateInterval inner;
	public final DateInfo outer;

	public CutDateIntervalException(DateInterval inner, DateInfo outer) {
		super(ConsoleOut.print(inner) + " is not in " + ConsoleOut.print(outer));
		this.inner = inner;
		this.outer = outer;
	}

}
