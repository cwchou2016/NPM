package us.dontcareabout.npm.client;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Exhibition {
	private String name;

	/**
	 * key: 展間名稱
	 * value: 開放日期區間
	 */
	private Map<String, DateInfo> openIntervals = new HashMap<>();


	Exhibition(RawData data) {
		this.name = data.getName();

		for (String r : data.getRooms().split(",")) {
			String room = r.trim().toUpperCase();
			openIntervals.put(room, new DateInfo());
			openIntervals.get(room).addOpenInterval(data.getStart(), data.getEnd());
		}
	}

	/**
	 * 加入換展關閉展廳之資訊
	 */
	public void addClose(RawData data) {
	}

	public Set<String> getRooms() {
		return openIntervals.keySet();
	}

	@Override
	public String toString() {
		return "Name: " + name + ", " +
				"Showroom: " + getRooms() + ", " +
				"Display date: " + openIntervals;
	}
}
