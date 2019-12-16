package us.dontcareabout.npm.client;

import java.util.ArrayList;

public class Exhibition {
	private String name;
	private ArrayList<Object> openIntervals = new ArrayList<>();
	// TODO: class OpenIntervals
	// TODO: class DateInterval


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
