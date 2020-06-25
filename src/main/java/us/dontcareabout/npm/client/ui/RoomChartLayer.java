package us.dontcareabout.npm.client.ui;

import com.sencha.gxt.chart.client.draw.RGB;
import us.dontcareabout.gxt.client.draw.component.TextButton;
import us.dontcareabout.gxt.client.draw.layout.HorizontalLayoutLayer;
import us.dontcareabout.gxt.client.draw.layout.VerticalLayoutLayer;
import us.dontcareabout.npm.client.DateInterval;
import us.dontcareabout.npm.client.Exception.RoomCannotSplitException;
import us.dontcareabout.npm.client.Exception.RoomNotFoundException;
import us.dontcareabout.npm.client.Exhibition;
import us.dontcareabout.npm.client.Showroom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomChartLayer extends VerticalLayoutLayer {
	private String room;
	private HorizontalLayoutLayer roomLayout = new HorizontalLayoutLayer();
	private Map<String, TimelineLayer> subRoomMap = new HashMap<>();

	public RoomChartLayer(String room, DateInterval interval, int shiftDays) throws RoomCannotSplitException {
		this.room = room;
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

	/**
	 * 加入(子)展間的展覽資訊
	 */
	public void addExhibitionInfo(String showRoom, Exhibition e, DateInterval dateInterval, boolean isClose) throws RoomCannotSplitException, RoomNotFoundException {
		// TODO: 處理展覽詳細資訊

		if (Showroom.isSubRoom(showRoom)) {
			TimelineLayer tl = subRoomMap.get(showRoom);
			if (tl == null) {
				throw new RoomNotFoundException(showRoom);
			}
			subRoomMap.get(showRoom).addMark(dateInterval, isClose);
			return;
		}

		if (!showRoom.equals(room)) {
			throw new RoomNotFoundException(showRoom);
		}

		for (String subRoom : Showroom.splitRoom(showRoom)) {
			subRoomMap.get(subRoom).addMark(dateInterval, isClose);
		}
	}

}
