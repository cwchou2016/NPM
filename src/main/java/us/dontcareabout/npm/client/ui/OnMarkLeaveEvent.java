package us.dontcareabout.npm.client.ui;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class OnMarkLeaveEvent extends GwtEvent<OnMarkLeaveEvent.OnMarkLeaveHandler> {
	public static final Type<OnMarkLeaveHandler> TYPE = new Type<>();

	@Override
	public Type<OnMarkLeaveHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(OnMarkLeaveHandler onMarkLeaveHandler) {
		onMarkLeaveHandler.onMarkLeave(this);
	}


	public interface OnMarkLeaveHandler extends EventHandler {
		public void onMarkLeave(OnMarkLeaveEvent event);
	}
}