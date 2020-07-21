package us.dontcareabout.npm.client.ui;

import com.google.gwt.user.client.Event;
import us.dontcareabout.gxt.client.draw.LayerContainer;
import us.dontcareabout.npm.client.Exception.RoomCannotSplitException;
import us.dontcareabout.npm.client.Exception.RoomNotFoundException;
import us.dontcareabout.npm.client.Exhibition;
import us.dontcareabout.npm.client.Showroom;

public class TestContainer extends LayerContainer {
	public final static TooltipLayer tooltip = new TooltipLayer();
	private RoomChartLayer rc;

	public TestContainer(Exhibition ex) throws RoomCannotSplitException, RoomNotFoundException {
		super(300, 700);
		//只畫一個展間
		for (String r : ex.getRooms()) {
			String room = Showroom.getParentName(r);
			rc = new RoomChartLayer(room, ex.getDisplayDate(), 10);
			rc.addExhibitionInfo(r, ex, ex.getOpenIntervals().get(r).getOpenIntervals().get(0), false);
			break;
		}

		addLayer(rc);
		addLayer(tooltip);

		UiCenter.addMarkOverHandler(new OnMarkOverEvent.OnMarkOverHandler() {
			@Override
			public void onMarkOver(OnMarkOverEvent event) {
				Event be = event.getSpriteOverEvent().getBrowserEvent();
				tooltip.show(be.getClientX() - getAbsoluteLeft(), be.getClientY() - getAbsoluteTop());
			}
		});

		UiCenter.addMarkLeaveHandler(new OnMarkLeaveEvent.OnMarkLeaveHandler() {
			@Override
			public void onMarkLeave(OnMarkLeaveEvent event) {
				tooltip.hide();
			}
		});
	}

	@Override
	protected void adjustMember(int width, int height) {
		rc.resize(width, height);
	}
}
