package us.dontcareabout.npm.client.ui;

import us.dontcareabout.gwt.client.Console;
import us.dontcareabout.gxt.client.draw.LayerSprite;
import us.dontcareabout.gxt.client.draw.layout.HorizontalLayoutLayer;
import us.dontcareabout.gxt.client.draw.layout.VerticalLayoutLayer;
import us.dontcareabout.npm.client.DateInterval;
import us.dontcareabout.npm.client.Exception.RoomCannotSplitException;
import us.dontcareabout.npm.client.Exhibition;
import us.dontcareabout.npm.client.Showroom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExhibitionChartLayer extends HorizontalLayoutLayer {
	private static final int shiftDays = 30;
	private DateInterval interval;
	private Map<String, RoomChartLayer> roomMap = new HashMap<>();
	private VerticalLayoutLayer labelLayer = new VerticalLayoutLayer();

	public ExhibitionChartLayer(DateInterval interval) {
		this.interval = interval;
		setGap(10);

		DateLabelLayer label = new DateLabelLayer(interval, shiftDays);
		labelLayer.addChild(new LayerSprite(), 60);
		labelLayer.addChild(label, 1);
	}

	public void loadExhibitionList(List<Exhibition> exhibitionList) {
		for (Exhibition e : exhibitionList) {
			try {
				addExhibition(e);
			} catch (RoomCannotSplitException exception) {
				Console.log(exception);
			}
		}
		draw();
	}

	private void createRoom(String room) throws RoomCannotSplitException {
		RoomChartLayer rc = new RoomChartLayer(room, interval, shiftDays);
		roomMap.put(room, rc);
	}

	private void addExhibition(Exhibition e) throws RoomCannotSplitException {
		DateInterval displayDate = e.getDisplayDate();

		for (String r : e.getRooms()) {
			String parentRoom = Showroom.getParentName(r);

			if (roomMap.get(parentRoom) == null) {
				createRoom(parentRoom);
			}

			//先畫關閉
			roomMap.get(parentRoom).addExhibitionInfo(r, displayDate, true);

			// 再畫開放
			for (DateInterval dateInterval : e.getOpenIntervals().get(r).getOpenIntervals()) {
				roomMap.get(parentRoom).addExhibitionInfo(r, dateInterval, false);
			}
		}
	}

	private void draw() {
		double weight = 1.0 / (roomMap.keySet().size());
		addChild(labelLayer, 80);
		for (String room : roomMap.keySet()) {
			addChild(roomMap.get(room), weight);
		}
	}
}
