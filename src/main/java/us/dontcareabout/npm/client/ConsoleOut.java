package us.dontcareabout.npm.client;

import com.google.gwt.i18n.client.DateTimeFormat;
import us.dontcareabout.gwt.client.Console;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConsoleOut {
	private final static DateTimeFormat dateFormat = DateTimeFormat.getFormat("yyyy-MM-dd");

	public static void viewExhibitionTable() {
		for (Exhibition e : ExhibitionTable.getExhibitionTable()) {
			Console.log(print(e));
		}
	}

	public static void viewErrorData() {
		for (RawData data : ExhibitionTable.getErrorDataMap().keySet()) {
			Console.log(print(data) + " " + ExhibitionTable.getErrorDataMap().get(data));
		}
	}

	public static String print(Exhibition e) {
		return "Name: " + e.getName() + ", " +
				"Showroom: " + e.getRooms() + ", " +
				"Display date: " + print(e.getDisplayDate()) + ", " +
				"OpenIntervals: " + print(e.getOpenIntervals());
	}

	public static String print(DateInterval dateInterval) {
		String start = dateFormat.format(dateInterval.getStart());
		String end = dateFormat.format(dateInterval.getEnd());
		return "(" + start + ", " + end + ")";
	}

	public static String print(Map<String, DateInfo> openInterval) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");

		List<String> keys = new ArrayList<>(openInterval.keySet());
		for (int i = 0; i < keys.size(); i++) {
			String k = keys.get(i);
			sb.append(k + "=");
			sb.append(print(openInterval.get(k)));
			if (i < keys.size() - 1) {
				sb.append(", ");
			}
		}

		sb.append("}");
		return sb.toString();
	}

	public static String print(DateInfo dateInfo) {
		StringBuilder sb = new StringBuilder("[");

		List<DateInterval> openIntervals = dateInfo.getOpenIntervals();
		for (int i = 0; i < openIntervals.size(); i++) {
			sb.append(print(openIntervals.get(i)));
			if (i < openIntervals.size() - 1) {
				sb.append(", ");
			}
		}

		sb.append("]");
		return sb.toString();
	}

	public static String print(RawData data) {
		return data.getIndex() + ", " +
				data.getName() + ", " +
				data.getClose() + ", " +
				dateFormat.format(data.getStart()) + ", " +
				dateFormat.format(data.getEnd()) + ", " +
				data.getRooms();
	}
}
