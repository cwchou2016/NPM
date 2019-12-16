package us.dontcareabout.npm.client;

import java.util.HashMap;
import java.util.Map;

public class Exhibition {
	private String name;

	/**
	 * key: 展間名稱
	 * value: 開放日期區間
	 */
	private Map<String, DateInfo> openIntervals = new HashMap<>();


	Exhibition(RawData data) {
		this.name = data.getName();
	}

	/**
	 * 加入換展關閉展廳之資訊
	 */
	public void addClose(RawData data) {
	}

	@Override
	public String toString() {
		return "Name: " + name;
	}
}
