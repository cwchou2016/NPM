package us.dontcareabout.npm.client;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;


public class DataReadyEvent extends GwtEvent<DataReadyEvent.DataReadyHandler> {
	public static final Type<DataReadyHandler> TYPE = new Type<>();

	@Override
	public Type<DataReadyHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(DataReadyHandler dataReadyHandler) {
		dataReadyHandler.onDataReady(this);
	}

	public interface DataReadyHandler extends EventHandler {
		public void onDataReady(DataReadyEvent event);
	}
}
