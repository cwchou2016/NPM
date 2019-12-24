package us.dontcareabout.npm.client;

import us.dontcareabout.gwt.client.google.SheetEntry;

import java.util.Date;

public final class RawData extends SheetEntry {
	protected RawData() {
	}    //JSNI: JavaScriptObject 規格要求

	public String getName() {
		return stringField("name");
	}

	public String getRooms() {
		return stringField("rooms");
	}

	public Date getStart() {
		return dateField("start");
	}

	public Date getEnd() {
		return dateField("end");
	}

	public boolean getClose() {
		return stringField("close") == "TRUE" ? true : false;
	}
}
