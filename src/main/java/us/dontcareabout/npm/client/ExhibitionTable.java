package us.dontcareabout.npm.client;

import us.dontcareabout.gwt.client.google.Sheet;
import us.dontcareabout.gwt.client.google.SheetHappen;

import java.util.ArrayList;

public class ExhibitionTable {

	ExhibitionTable(String sheetId) {
		loadFromGoogleSheet(sheetId);
	}

	private void loadFromGoogleSheet(String sheetId) {
		SheetHappen.<RawData>get(sheetId, 1, new SheetHappen.Callback<RawData>() {
			@Override
			public void onSuccess(Sheet<RawData> gs) {
				ArrayList<RawData> dataList = gs.getEntry();
			}

			@Override
			public void onError(Throwable exception) {

			}
		});
	}

	/**
	 * 從 dataList 讀取展覽資訊
	 */
	public void loadExhibitionInfo(ArrayList<RawData> dataList) {
	}

	/**
	 * 從 dataList 讀取換展關閉展廳資訊
	 */
	public void loadCloseInfo(ArrayList<RawData> dataList) {
	}

}
