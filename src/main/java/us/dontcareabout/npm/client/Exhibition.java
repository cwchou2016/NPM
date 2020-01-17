package us.dontcareabout.npm.client;

import us.dontcareabout.npm.client.Exception.CutDateIntervalException;
import us.dontcareabout.npm.client.Exception.RoomCannotSplitException;
import us.dontcareabout.npm.client.Exception.RoomNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
	 * @throws RoomCannotSplitException {@param data} 中的展間為子展間，無法分割。
	 * @throws RoomNotFoundException    {@param data} 中的展間，不在 {@link Exhibition} 展間內。
	 * @throws CutDateIntervalException {@param data} 中的換展日期不在 {@link Exhibition} 展覽期間內。
	 */
	public void addClose(RawData data)
			throws RoomCannotSplitException, RoomNotFoundException, CutDateIntervalException {
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
				List<String> subRooms = Showroom.splitRoom(closeRoom);
				if (getRooms().containsAll(subRooms)) {
					roomToClose.addAll(subRooms);
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
	}

	/**
	 * 分割展間並複制展覽日期區間
	 *
	 * @throws RoomCannotSplitException {@param room} 為子展間，無法分割。
	 */
	private void splitRoom(String room) throws RoomCannotSplitException {
		List<String> subRooms = Showroom.splitRoom(room);
		for (String subRoom : subRooms) {
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
