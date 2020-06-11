package us.dontcareabout.npm.client.ui;

import com.sencha.gxt.chart.client.draw.RGB;
import us.dontcareabout.gxt.client.draw.LTextSprite;
import us.dontcareabout.gxt.client.draw.LayerSprite;
import us.dontcareabout.gxt.client.draw.layout.HorizontalLayoutLayer;
import us.dontcareabout.gxt.client.draw.layout.VerticalLayoutLayer;
import us.dontcareabout.npm.client.DateInterval;
import us.dontcareabout.npm.client.Exception.RoomCannotSplitException;
import us.dontcareabout.npm.client.Showroom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomChartLayer extends VerticalLayoutLayer {

	private HorizontalLayoutLayer hLayer = new HorizontalLayoutLayer();
	private Map<String, TimelineLayer> subRoomMap = new HashMap<>();

	public RoomChartLayer(String room, DateInterval interval, int shiftDays) throws RoomCannotSplitException {
		RoomNameSprite roomName = new RoomNameSprite(room);

		List<String> subRoomList = Showroom.splitRoom(room);
		for (String subRoom : subRoomList) {
			TimelineLayer tl = new TimelineLayer(interval, shiftDays);
			subRoomMap.put(subRoom, tl);
			hLayer.addChild(tl, 1.0 / subRoomList.size());
		}

		//TODO: 設定大小
		addChild(roomName, 60);
		addChild(hLayer, 1);
	}

	static class RoomNameSprite extends LayerSprite {
		private LTextSprite nameText;

		RoomNameSprite(String name) {
			//TODO: 修改顏色
			nameText = new LTextSprite(name);
			nameText.setFontSize(72);
			add(nameText);
			setBgColor(RGB.BLUE);
		}
	}
}
