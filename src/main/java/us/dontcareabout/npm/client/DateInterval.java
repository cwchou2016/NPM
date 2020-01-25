package us.dontcareabout.npm.client;

import java.util.Date;

/**
 * 日期區間
 */
public class DateInterval {
	private Date start;
	private Date end;

	DateInterval(Date date1, Date date2) {
		this.start = date1.before(date2) ? date1 : date2;
		this.end = date1.after(date2) ? date1 : date2;
	}

	/**
	 * @return 是否包含 {@param innerInterval}
	 */
	public boolean contains(DateInterval innerInterval) {
		return start.before(innerInterval.start) && end.after(innerInterval.end);
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
}
