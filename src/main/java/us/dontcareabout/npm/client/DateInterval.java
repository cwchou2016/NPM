package us.dontcareabout.npm.client;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.datepicker.client.CalendarUtil;

import java.util.Date;

/**
 * 日期區間
 */
public class DateInterval {
	private Date start;
	private Date end;

	public DateInterval(Date date1, Date date2) {
		this.start = date1.before(date2) ? date1 : date2;
		this.end = date1.after(date2) ? date1 : date2;
	}

	/**
	 * @return 是否包含 {@param innerInterval}
	 */
	public boolean contains(DateInterval innerInterval) {
		return start.before(innerInterval.start) && end.after(innerInterval.end);
	}

	/**
	 * @return 開始與結束間的天數
	 */
	public int getDays() {
		return CalendarUtil.getDaysBetween(getStart(), getEnd());
	}

	/**
	 * @return @return 以月為最小單位的 DateInterval
	 */
	public DateInterval getMonthInterval() {
		DateTimeFormat dateFormat = DateTimeFormat.getFormat("yyyyMMdd");
		DateTimeFormat getYearFormat = DateTimeFormat.getFormat("yyyy");
		DateTimeFormat getMonthFormat = DateTimeFormat.getFormat("MM");

		String startYear = getYearFormat.format(start);
		String startMonth = getMonthFormat.format(start);

		String endYear = getYearFormat.format(end);
		String endMonth = getMonthFormat.format(end);

		Date newStart = dateFormat.parse(startYear + startMonth + "01");
		Date newEnd = dateFormat.parse(endYear + endMonth + "01");

		CalendarUtil.addMonthsToDate(newEnd, 1);
		CalendarUtil.addDaysToDate(newEnd, -1);

		return new DateInterval(newStart, newEnd);
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
