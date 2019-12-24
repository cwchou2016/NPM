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

	public ArrayList<DateInterval> getOpenIntervals() {
		return openIntervals;
	}
}
