package us.dontcareabout.npm.client.ui;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;

public class UiCenter {
	private final static SimpleEventBus eventBus = new SimpleEventBus();

	public static void fire(GwtEvent<?> event) {
		eventBus.fireEvent(event);
	}

	public static HandlerRegistration addMarkOverHandler(OnMarkOverEvent.OnMarkOverHandler handler) {
		return eventBus.addHandler(OnMarkOverEvent.TYPE, handler);
	}

	public static HandlerRegistration addMarkLeaveHandler(OnMarkLeaveEvent.OnMarkLeaveHandler handler) {
		return eventBus.addHandler(OnMarkLeaveEvent.TYPE, handler);
	}
}
