package us.dontcareabout.npm.client;

import us.dontcareabout.npm.client.Exception.RoomCannotSplitException;

import java.util.ArrayList;
import java.util.List;

/**
 * 展間名稱規則：
 *
 * 母展間的名稱皆為數字結尾，開頭不受限制。
 * 例如：S201、S301、S401
 *
 * 當母展間被分成兩個或兩個以上區域時，則加上英文字母，作為子展間的名稱。
 * 例如：S201 被分為兩個區域時，字展間名稱為 S201A、S201B
 *      S301 被分為三個區域時，字展間名稱為 S301A、S301B、S301C
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
		if (!isSubRoom(room)) {
			return null;
		}

		return room.charAt(room.length() - 1);
	}

	/**
	 * @return 母展間名稱
	 */
	public static String getParentName(String room) {
		if (!isSubRoom(room)) {
			return room;
		}

		return room.substring(0, room.length() - 1);
	}

	/**
	 * 將母展間分割成多個子展間
	 *
	 * @return 分割後子展間的名稱。
	 * @param room 母展間名稱
	 * @param amount 分割數量
	 * @throws RoomCannotSplitException {@param room} 為子展間，無法分割。
	 */
	public static List<String> splitRoom(String room, int amount) throws RoomCannotSplitException {
		ArrayList<String> subRooms = new ArrayList<>();

		if (isSubRoom(room)) {
			throw new RoomCannotSplitException(room);
		}
		for (int i = 0; i < amount; i++) {
			subRooms.add(room + ((char) (firstSubName + i)));
		}
		return subRooms;
	}

	/**
	 * 將母展間分割成兩個子展間
	 *
	 * @return 分割後子展間的名稱。
	 * @throws RoomCannotSplitException {@param room} 為子展間，無法分割。
	 */
	public static List<String> splitRoom(String room) throws RoomCannotSplitException {
		return splitRoom(room, 2);
	}
}
