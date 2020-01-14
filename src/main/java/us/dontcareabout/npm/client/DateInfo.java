package us.dontcareabout.npm.client;

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
	 */
	public void cutCloseInterval(DateInterval closeInterval) {
		for (DateInterval dateInterval : openIntervals) {
			if (dateInterval.contains(closeInterval)) {
				// 這邊無法移動日期
				openIntervals.add(new DateInterval(closeInterval.getEnd(), dateInterval.getEnd())); //應為 closeInterval.getEnd() +1 天
				dateInterval.setEnd(closeInterval.getStart()); // 應為 closeInterval.getStart() -1 天
				return;
			}
		}

		throw new CutDateIntervalException(closeInterval, openIntervals);
	}

	/**
	 * @return 整個開放日期的範圍
	 */
	public DateInterval getOpenRange() {
		Collections.sort(openIntervals, new Comparator<DateInterval>() {
			/**
			 * 比較兩個 DateInterval.start
			 */
			@Override
			public int compare(DateInterval d1, DateInterval d2) {
				return d1.getStart().compareTo(d2.getStart());
			}
		});

		return new DateInterval(openIntervals.get(0).getStart(), openIntervals.get(openIntervals.size() - 1).getEnd());
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
