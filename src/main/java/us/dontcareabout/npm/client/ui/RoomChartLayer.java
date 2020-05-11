package us.dontcareabout.npm.client.ui;

import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.chart.client.draw.sprite.SpriteOverEvent;
import us.dontcareabout.gxt.client.draw.LTextSprite;
import us.dontcareabout.gxt.client.draw.LayerSprite;
import us.dontcareabout.gxt.client.draw.layout.HorizontalLayoutLayer;
import us.dontcareabout.gxt.client.draw.layout.VerticalLayoutLayer;
import us.dontcareabout.npm.client.DateInterval;

public class RoomChartLayer extends VerticalLayoutLayer {
	private int singleRoomWidth = 60;
	private TimelineSprite roomA;
	private TimelineSprite roomB;

	private HorizontalLayoutLayer timelineLayer = new HorizontalLayoutLayer();

	public RoomChartLayer(String room, DateInterval interval) {
		roomA = new TimelineSprite(interval);
		roomB = new TimelineSprite(interval);

		timelineLayer.addChild(roomA, singleRoomWidth);
		timelineLayer.addChild(roomB, singleRoomWidth);

		RoomName roomName = new RoomName(room);

		addChild(roomName, 60);
		addChild(timelineLayer, 1);
	}

	public void addExhibitionInfo(String room, DateInterval interval, boolean isClose,
								  SpriteOverEvent.SpriteOverHandler handler) {
		//TODO: 判斷 room 是否為子展間

		roomA.addLine(interval, isClose, handler);
	}

	class RoomName extends LayerSprite {
		RoomName(String room) {
			LTextSprite text = new LTextSprite(room);
			text.setFontSize(36);
			resize(singleRoomWidth * 2, 60);
			setBgColor(RGB.BLUE);
			add(text);
		}
	}
}
