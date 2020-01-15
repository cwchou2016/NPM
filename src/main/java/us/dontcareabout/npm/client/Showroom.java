package us.dontcareabout.npm.client;

import us.dontcareabout.npm.client.Exception.RoomCannotSplitException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 處理展廳
 */
public class Showroom {

	/**
	 * 第一個子展間名稱
	 */
	private static final char firstSubName = 'A';

	/**
	 * 判斷是否是子展間
	 */
	public static boolean isSubRoom(String room) {
		// 檢查最後一個字元：非數字為子展間。
		return !Character.isDigit(room.charAt(room.length() - 1));
	}

	/**
	 * @return 子展間的名稱。當傳入的 {@param room} 非子展間時，回傳 null。
	 */
	public static Character getSubName(String room) {
		if (!isSubRoom(room)) return null;

		return room.charAt(room.length() - 1);
	}

	/**
	 * @return 母展間名稱
	 */
	public static String getParentName(String room) {
		if (!isSubRoom(room)) return room;

		return room.substring(0, room.length() - 1);
	}

	/**
	 * @return 分割後子展間的名稱。
	 * @throws RoomCannotSplitException {@param room} 為子展間，無法分割。
	 */
	public static List<String> splitRoom(String room) throws RoomCannotSplitException {
		ArrayList<String> subRooms = new ArrayList<>();

		if (isSubRoom(room)) throw new RoomCannotSplitException(room);

		// 目前應該只需要兩個子展間吧
		subRooms.add(room + firstSubName);
		subRooms.add(room + ((char) ((int) firstSubName + 1)));

		return Collections.unmodifiableList(subRooms);
	}
}
