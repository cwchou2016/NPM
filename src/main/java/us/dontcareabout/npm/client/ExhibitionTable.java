package us.dontcareabout.npm.client;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import us.dontcareabout.gwt.client.google.Sheet;
import us.dontcareabout.gwt.client.google.SheetHappen;
import us.dontcareabout.npm.client.Exception.ExhibitionNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExhibitionTable {
	private static final SimpleEventBus eventBus = new SimpleEventBus();
	private static final ArrayList<Exhibition> exhibitionTable = new ArrayList<>();

	public static HandlerRegistration addDataReadyHandler(DataReadyEvent.DataReadyHandler handler) {
		return eventBus.addHandler(DataReadyEvent.TYPE, handler);
	}

	public static List<Exhibition> getExhibitionTable() {
		return Collections.unmodifiableList(exhibitionTable);
	}


	public static void loadFromGoogleSheet(String sheetId) {
		SheetHappen.<RawData>get(sheetId, 1, new SheetHappen.Callback<RawData>() {
			@Override
			public void onSuccess(Sheet<RawData> gs) {
				ArrayList<RawData> dataList = gs.getEntry();
				loadExhibitionInfo(dataList);
				loadCloseInfo(dataList);
				eventBus.fireEvent(new DataReadyEvent());
			}

			@Override
			public void onError(Throwable exception) {

			}
		});
	}

	/**
	 * 從 dataList 讀取展覽資訊
	 */
	private static void loadExhibitionInfo(ArrayList<RawData> dataList) {
		for (RawData data : dataList) {
			if (!data.getClose()) exhibitionTable.add(new Exhibition(data));
		}
	}

	/**
	 * 從 dataList 讀取換展關閉展廳資訊
	 */
	private static void loadCloseInfo(ArrayList<RawData> dataList) {
		for (RawData data : dataList) {
			if (!data.getClose()) continue;

			boolean found = false;
			for (Exhibition e : exhibitionTable) {
				if (e.getName().equals(data.getName())) {
					found = e.addClose(data);
					break;
				}
			}
			if (!found) throw new ExhibitionNotFoundException(data.getName());
		}
	}
}
