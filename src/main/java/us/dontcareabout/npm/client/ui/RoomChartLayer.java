package us.dontcareabout.npm.client.ui;

import com.sencha.gxt.chart.client.draw.RGB;
import us.dontcareabout.gxt.client.draw.component.TextButton;
import us.dontcareabout.gxt.client.draw.layout.HorizontalLayoutLayer;
import us.dontcareabout.gxt.client.draw.layout.VerticalLayoutLayer;
import us.dontcareabout.npm.client.DateInterval;
import us.dontcareabout.npm.client.Exception.RoomCannotSplitException;
import us.dontcareabout.npm.client.Showroom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomChartLayer extends VerticalLayoutLayer {
	private HorizontalLayoutLayer roomLayout = new HorizontalLayoutLayer();
	private Map<String, TimelineLayer> subRoomMap = new HashMap<>();

	public RoomChartLayer(String room, DateInterval interval, int shiftDays) throws RoomCannotSplitException {
		TextButton roomName = new TextButton(room);
		roomName.setBgColor(RGB.BLUE);

		List<String> subRoomList = Showroom.splitRoom(room);
		for (String subRoom : subRoomList) {
			TimelineLayer tl = new TimelineLayer(interval, shiftDays);
			subRoomMap.put(subRoom, tl);
			roomLayout.addChild(tl, 1.0 / subRoomList.size());
		}

		//TODO: 調整大小
		addChild(roomName, 60);
		addChild(roomLayout, 1);
	}

}
