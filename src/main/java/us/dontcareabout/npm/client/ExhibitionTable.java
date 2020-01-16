package us.dontcareabout.npm.client;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import us.dontcareabout.gwt.client.google.Sheet;
import us.dontcareabout.gwt.client.google.SheetHappen;
import us.dontcareabout.npm.client.Exception.CutDateIntervalException;
import us.dontcareabout.npm.client.Exception.ExhibitionNotFoundException;
import us.dontcareabout.npm.client.Exception.RoomCannotSplitException;
import us.dontcareabout.npm.client.Exception.RoomNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExhibitionTable {
	private static final SimpleEventBus eventBus = new SimpleEventBus();
	private static final ArrayList<Exhibition> exhibitionTable = new ArrayList<>();
	private static final Map<RawData, Exception> errorDataMap = new HashMap<>();

	public static HandlerRegistration addDataReadyHandler(DataReadyEvent.DataReadyHandler handler) {
		return eventBus.addHandler(DataReadyEvent.TYPE, handler);
	}

	public static List<Exhibition> getExhibitionTable() {
		return Collections.unmodifiableList(exhibitionTable);
	}

	public static Map<RawData, Exception> getErrorDataMap() {
		return Collections.unmodifiableMap(errorDataMap);
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

			try {
				addCloseData(data);
			} catch (ExhibitionNotFoundException | RoomCannotSplitException | RoomNotFoundException | CutDateIntervalException ex) {
				errorDataMap.put(data, ex);
			}
		}
	}

	/**
	 * 加入換展資料
	 *
	 * @throws ExhibitionNotFoundException {@param data} 不在 {@link ExhibitionTable} 內。
	 * @throws RoomCannotSplitException    {@param data} 中的展間為子展間，無法分割。
	 * @throws RoomNotFoundException       {@param data} 中的展間，不在 {@link Exhibition} 展間內。
	 * @throws CutDateIntervalException    {@param data} 中的換展日期不在 {@link Exhibition} 展覽期間內。
	 */
	private static void addCloseData(RawData data)
			throws ExhibitionNotFoundException, RoomCannotSplitException, RoomNotFoundException, CutDateIntervalException {
		for (Exhibition e : exhibitionTable) {
			if (e.getName().equals(data.getName())) {
				try {
					e.addClose(data);
				} catch (RoomNotFoundException ex) {
					throw new RoomNotFoundException(ex.room);
				} catch (RoomCannotSplitException ex) {
					throw new RoomCannotSplitException(ex.room);
				} catch (CutDateIntervalException ex) {
					throw new CutDateIntervalException(ex.inner, ex.outer);
				}
				return;
			}
		}
		throw new ExhibitionNotFoundException(data.getName());
	}
}
