package us.dontcareabout.npm.client.ui;

import us.dontcareabout.npm.client.DateInterval;
import us.dontcareabout.npm.client.Exhibition;

public class ExhibitionInfo {
	//TODO: 轉成人類可讀內容

	public static String openInfo(Exhibition ex) {
		String info = ex.getName() + "\n" + ex.getRooms() + "\n" + ex.getDisplayDate() + "\n" + ex.getOpenIntervals();
		return info;
	}

	public static String closeInfo(String name, String room, DateInterval dateInterval) {
		String info = name + "\n" + room + " " + dateInterval + "換展";
		return info;
	}
}
