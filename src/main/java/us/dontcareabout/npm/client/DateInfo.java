package us.dontcareabout.npm.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	public void cutCloseInterval(DateInterval closeInterval) {
		for (DateInterval dateInterval : openIntervals) {
			if (dateInterval.contains(closeInterval)) {
				// 這邊無法移動日期
				addOpenInterval(closeInterval.getEnd(), dateInterval.getEnd()); //應為 closeInterval.getEnd() +1 天
				dateInterval.setEnd(closeInterval.getStart()); // 應為 closeInterval.getStart() -1 天
				return;
			}
		}

		//TODO: 處理找不到可切除的日期區間的情形
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
		DateInfo newDateInfo = new DateInfo();
		for (DateInterval dateInterval : openIntervals) {
			newDateInfo.addOpenInterval(dateInterval.getStart(), dateInterval.getEnd());
		}
		return newDateInfo;
	}

	public ArrayList<DateInterval> getOpenIntervals() {
		return openIntervals;
	}
}
