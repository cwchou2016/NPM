package us.dontcareabout.npm.client.ui;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;

public class UiCenter {
	public final static SimpleEventBus eventBus = new SimpleEventBus();

	public static void fire(GwtEvent<?> event) {
		eventBus.fireEvent(event);
	}

	public static HandlerRegistration addMarkOverHandler(TimelineSprite.OnMarkOverHandler handler) {
		return eventBus.addHandler(TimelineSprite.OnMarkOverEvent.TYPE, handler);
	}

	public static HandlerRegistration addMarkLeaveHandler(TimelineSprite.OnMarkLeaveHandler handler) {
		return eventBus.addHandler(TimelineSprite.OnMarkLeaveEvent.TYPE, handler);
	}
}
