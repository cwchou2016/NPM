package us.dontcareabout.npm.client.ui;

import us.dontcareabout.gxt.client.draw.LayerSprite;
import us.dontcareabout.gxt.client.draw.layout.HorizontalLayoutLayer;
import us.dontcareabout.gxt.client.draw.layout.VerticalLayoutLayer;
import us.dontcareabout.npm.client.DateInterval;
import us.dontcareabout.npm.client.Exception.RoomCannotSplitException;

import java.util.HashMap;
import java.util.Map;

public class ExhibitionChartLayer extends HorizontalLayoutLayer {
	private static final int shiftDays = 10;
	private DateInterval interval;
	private Map<String, RoomChartLayer> roomMap = new HashMap<>();
	private VerticalLayoutLayer labelLayer = new VerticalLayoutLayer();

	public ExhibitionChartLayer(DateInterval interval) {
		//TODO: 傳入 List<Exhibition>
		this.interval = interval;
		setGap(10);

		DateLabelLayer label = new DateLabelLayer(interval, shiftDays);
		labelLayer.addChild(new LayerSprite(), 60);
		labelLayer.addChild(label, 1);

	}

	public void createRoom(String room) throws RoomCannotSplitException {
		RoomChartLayer rc = new RoomChartLayer(room, interval, shiftDays);
		roomMap.put(room, rc);
	}

	public void draw() {
		double weight = 1.0 / (roomMap.keySet().size());
		addChild(labelLayer, 80);
		for (String room : roomMap.keySet()) {
			addChild(roomMap.get(room), weight);
		}
	}
}
