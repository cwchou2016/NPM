package us.dontcareabout.npm.client;

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
	 * 取得子展間的名稱
	 */
	public static void getSubName(String room) {
		//TODO
	}

	/**
	 * 取得母展間名稱
	 */
	public static void getParentName(String room) {
		//TODO
	}

	/**
	 * 將母展間切割為兩個子展間
	 */
	public static void splitRoom(String room) {
		//TODO
	}
}
