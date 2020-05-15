package us.dontcareabout.npm.client.ui;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;


public class OnMarkOverEvent extends GwtEvent<OnMarkOverEvent.OnMarkOverHandler> {
	public static final Type<OnMarkOverHandler> TYPE = new Type<>();

	@Override
	public Type<OnMarkOverHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(OnMarkOverHandler onMarkOverHandler) {
		onMarkOverHandler.onMarkOver(this);
	}

	public interface OnMarkOverHandler extends EventHandler {
		void onMarkOver(OnMarkOverEvent event);
	}
}


