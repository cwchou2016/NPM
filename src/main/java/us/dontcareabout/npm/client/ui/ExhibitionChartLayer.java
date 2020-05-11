package us.dontcareabout.npm.client.ui;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.sencha.gxt.chart.client.draw.sprite.SpriteOverEvent;
import us.dontcareabout.gxt.client.draw.LTextSprite;
import us.dontcareabout.gxt.client.draw.layout.HorizontalLayoutLayer;
import us.dontcareabout.npm.client.DateInterval;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ExhibitionChartLayer extends HorizontalLayoutLayer {
	private Map<String, RoomChartLayer> rooms = new HashMap<>();
	private DateInterval interval;

	public ExhibitionChartLayer() {
		setGap(10);
	}

	public void updateChart(DateInterval interval) {
		//TODO: 傳入 List<Exhibition>
		clear();
		rooms.clear();
		this.interval = interval.getMonthInterval();
		createLabel();
	}

	public void createLabel() {
		TimeLabel timeLabel = new TimeLabel();
		addChild(timeLabel, 120);
	}

	public void addRoom(String room) {
		RoomChartLayer roomChart = new RoomChartLayer(room, interval);
		rooms.put(room, roomChart);
		addChild(roomChart, 120);
	}

	public void addExhibition(String room, DateInterval interval, boolean isClose, SpriteOverEvent.SpriteOverHandler handler) {
		//TODO 傳入 Exhibition

		rooms.get(room).addExhibitionInfo(room, interval, isClose, handler);
	}

	class TimeLabel extends TimelineSprite {
		public TimeLabel() {
			Date date = new Date(interval.getStart().getTime());
			DateTimeFormat format = DateTimeFormat.getFormat("yyyy-MM-dd");

			while (date.before(interval.getEnd())) {
				LTextSprite text = new LTextSprite(format.format(date));
				text.setFontSize(26);
				int y = CalendarUtil.getDaysBetween(interval.getStart(), date) + 2;
				text.setLY(y * getDayHeight() + 60);
				add(text);
				CalendarUtil.addMonthsToDate(date, 1);
			}
		}
	}
}
