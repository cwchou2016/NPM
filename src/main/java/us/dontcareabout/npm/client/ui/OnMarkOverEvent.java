package us.dontcareabout.npm.client.ui;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.sencha.gxt.chart.client.draw.sprite.SpriteOverEvent;


public class OnMarkOverEvent extends GwtEvent<OnMarkOverEvent.OnMarkOverHandler> {
	public static final Type<OnMarkOverHandler> TYPE = new Type<>();
	private final SpriteOverEvent spriteOverEvent;

	public OnMarkOverEvent(SpriteOverEvent spriteOverEvent) {
		this.spriteOverEvent = spriteOverEvent;
	}

	public SpriteOverEvent getSpriteOverEvent() {
		return spriteOverEvent;
	}


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


