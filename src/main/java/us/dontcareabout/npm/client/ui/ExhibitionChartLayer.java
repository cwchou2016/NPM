package us.dontcareabout.npm.client.ui;

import us.dontcareabout.gxt.client.draw.layout.HorizontalLayoutLayer;
import us.dontcareabout.npm.client.DateInterval;

import java.util.HashMap;
import java.util.Map;

public class ExhibitionChartLayer extends HorizontalLayoutLayer {
	private Map<String, RoomChartLayer> rooms = new HashMap<>();
	private DateInterval interval;

	public ExhibitionChartLayer(DateInterval interval) {
		//TODO: 傳入 List<Exhibition>

		this.interval = interval;
		setGap(10);
	}

	public void addRoom(String room) {
		RoomChartLayer roomChart = new RoomChartLayer(room, interval);
		rooms.put(room, roomChart);
		addChild(roomChart, 120);
	}
}
