package us.dontcareabout.npm.client.Exception;

import us.dontcareabout.npm.client.DateInterval;

import java.util.List;

public class CutDateIntervalException extends Exception {
	public final DateInterval inner;
	public final List<DateInterval> outer;

	public CutDateIntervalException(DateInterval inner, List<DateInterval> outer) {
		super(inner + " is not in " + outer);
		this.inner = inner;
		this.outer = outer;
	}

}
