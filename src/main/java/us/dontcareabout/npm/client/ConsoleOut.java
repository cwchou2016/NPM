package us.dontcareabout.npm.client;

import com.google.gwt.i18n.client.DateTimeFormat;
import us.dontcareabout.gwt.client.Console;

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
		for (String k : openInterval.keySet()) {
			sb.append(k + "=");
			sb.append(print(openInterval.get(k)) + ", ");
		}
		sb.setLength(sb.length() - 2);
		sb.append("}");
		return sb.toString();
	}

	public static String print(DateInfo dateInfo) {
		StringBuilder sb = new StringBuilder("[");
		for (DateInterval d : dateInfo.getOpenIntervals()) {
			sb.append(print(d) + ", ");
		}
		sb.setLength(sb.length() - 2);
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
