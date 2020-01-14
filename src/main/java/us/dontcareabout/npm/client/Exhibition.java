package us.dontcareabout.npm.client;

import us.dontcareabout.npm.client.Exception.RoomNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Exhibition {
	private String name;

	/**
	 * key: 展間名稱
	 * value: 開放日期區間
	 */
	private Map<String, DateInfo> openIntervals = new HashMap<>();


	Exhibition(RawData data) {
		this.name = data.getName();

		for (String r : data.getRooms().split(",")) {
			String room = r.trim().toUpperCase();
			DateInfo dateInfo = new DateInfo(data.getStart(), data.getEnd());
			openIntervals.put(room, dateInfo);
		}
	}

	/**
	 * @return 成功加入換展關閉展廳之資訊
	 */
	public boolean addClose(RawData data) {
		ArrayList<String> roomToClose = new ArrayList<>();

		for (String r : data.getRooms().split(",")) {
			String closeRoom = r.trim().toUpperCase();

			// 找不到展廳
			if (!getRooms().contains(closeRoom)) {
				// 展廳未分兩半，只關閉一半
				String parentRoom = Showroom.getParentName(closeRoom);
				if (getRooms().contains(parentRoom)) {
					splitRoom(parentRoom);
					roomToClose.add(closeRoom);
					continue;
				}

				// 展廳已分兩半，全部關閉
				if (getRooms().containsAll(Showroom.splitRoom(closeRoom))) {
					roomToClose.addAll(Showroom.splitRoom(closeRoom));
					continue;
				}
				throw new RoomNotFoundException(closeRoom);
			} else {
				roomToClose.add(closeRoom);
			}
		}

		DateInterval closeInterval = new DateInterval(data.getStart(), data.getEnd());
		for (String room : roomToClose) {
			openIntervals.get(room).cutCloseInterval(closeInterval);
		}
		return true;
	}

	/**
	 * 分割展間並複制展覽日期區間
	 */
	private void splitRoom(String room) {
		for (String subRoom : Showroom.splitRoom(room)) {
			openIntervals.put(subRoom, openIntervals.get(room).deepClone());
		}
		openIntervals.remove(room);
	}

	/**
	 * 展出期間
	 */
	public DateInterval getDisplayDate() {
		DateInfo intervals = openIntervals.values().iterator().next();
		return intervals.getOpenRange();
	}

	public Set<String> getRooms() {
		return openIntervals.keySet();
	}

	public String getName() {
		return name;
	}

	public Map<String, DateInfo> getOpenIntervals() {
		return Collections.unmodifiableMap(openIntervals);
	}
}
