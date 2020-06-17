package us.dontcareabout.npm.client.ui;

import us.dontcareabout.gxt.client.draw.layout.HorizontalLayoutLayer;
import us.dontcareabout.npm.client.DateInterval;
import us.dontcareabout.npm.client.Exception.RoomCannotSplitException;

import java.util.HashMap;
import java.util.Map;

public class ExhibitionChartLayer extends HorizontalLayoutLayer {
	private static final int shiftDays = 10;
	private DateInterval interval;
	private Map<String, RoomChartLayer> roomMap = new HashMap<>();

	public ExhibitionChartLayer(DateInterval interval) {
		//TODO: 傳入 List<Exhibition>
		this.interval = interval;
		setGap(10);
	}

	public void createRoom(String room) throws RoomCannotSplitException {
		RoomChartLayer rc = new RoomChartLayer(room, interval, shiftDays);
		roomMap.put(room, rc);
	}

	public void draw() {
		for (String room : roomMap.keySet()) {
			addChild(roomMap.get(room), 1.0 / roomMap.keySet().size());
		}
	}
}
