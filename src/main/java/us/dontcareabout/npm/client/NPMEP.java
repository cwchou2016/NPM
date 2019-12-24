package us.dontcareabout.npm.client;

import com.google.gwt.user.client.Window;
import us.dontcareabout.gwt.client.GFEP;

public class NPMEP extends GFEP {
	private final static String SHEET_ID = "1xRkXYlAioJe44AvhLpxuyZLfkvkmz9L0sPZo67feKH0";

	public NPMEP() {
	}

	@Override
	protected String version() {
		return "0.0.1";
	}

	@Override
	protected String defaultLocale() {
		return "zh_TW";
	}

	@Override
	protected void featureFail() {
		Window.alert("這個瀏覽器我不尬意，不給用..... \\囧/");
	}

	@Override
	protected void start() {
		ExhibitionTable.loadFromGoogleSheet(SHEET_ID);

		ExhibitionTable.addDataReadyHandler(new DataReadyEvent.DataReadyHandler() {
			@Override
			public void onDataReady(DataReadyEvent event) {
				Window.alert("載入完成");
			}
		});
	}
}
