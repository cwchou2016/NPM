package us.dontcareabout.npm.client;

import java.util.ArrayList;
import java.util.Date;

/**
 * 開放日期區間
 */
public class DateInfo {
	ArrayList<DateInterval> openIntervals;

	DateInfo() {
		openIntervals = new ArrayList<>();
	}

	/**
	 * 加入展廳開放日期區間
	 */
	public void addOpenInterval(Date date1, Date date2) {
		openIntervals.add(new DateInterval(date1, date2));
	}

	/**
	 * 從展廳開放日期區間切除展廳暫時閉閉日期區間
	 */
	public void cutCloseInterval(DateInterval di) {

	}

	public ArrayList<DateInterval> getOpenIntervals() {
		return openIntervals;
	}

	@Override
	public String toString() {
		return openIntervals.toString();
	}
}
