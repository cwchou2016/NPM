package us.dontcareabout.npm.client;

import com.google.gwt.user.datepicker.client.CalendarUtil;
import us.dontcareabout.npm.client.Exception.CutDateIntervalException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * 開放日期區間
 */
public class DateInfo {
	private ArrayList<DateInterval> openIntervals = new ArrayList<>();
	private DateInterval openRange;

	DateInfo(Date date1, Date date2) {
		openRange = new DateInterval(date1, date2);
		openIntervals.add(new DateInterval(date1, date2));
	}

	/**
	 * 從展廳開放日期區間切除展廳暫時閉閉日期區間
	 *
	 * @throws CutDateIntervalException {@param closeInterval} 不在 {@link DateInfo} 範圍內
	 */
	public void cutCloseInterval(DateInterval closeInterval) throws CutDateIntervalException {
		for (DateInterval dateInterval : openIntervals) {
			if (dateInterval.contains(closeInterval)) {
				CalendarUtil.addDaysToDate(closeInterval.getEnd(), 1);
				CalendarUtil.addDaysToDate(closeInterval.getStart(), -1);
				openIntervals.add(new DateInterval(closeInterval.getEnd(), dateInterval.getEnd()));
				dateInterval.setEnd(closeInterval.getStart());
				sort();
				return;
			}
		}

		throw new CutDateIntervalException(closeInterval, this);
	}

	public void sort() {
		Collections.sort(openIntervals, new Comparator<DateInterval>() {
			/**
			 * 比較兩個 DateInterval.start
			 */
			@Override
			public int compare(DateInterval d1, DateInterval d2) {
				return d1.getStart().compareTo(d2.getStart());
			}
		});
	}

	public DateInterval getOpenRange() {
		return openRange;
	}

	public DateInfo deepClone() {
		DateInfo newDateInfo = new DateInfo(openRange.getStart(), openRange.getEnd());

		ArrayList<DateInterval> newIntervals = new ArrayList<>();
		for (DateInterval dateInterval : openIntervals) {
			newIntervals.add(new DateInterval(dateInterval.getStart(), dateInterval.getEnd()));
		}

		newDateInfo.openIntervals = newIntervals;
		return newDateInfo;
	}

	public ArrayList<DateInterval> getOpenIntervals() {
		return openIntervals;
	}
}
