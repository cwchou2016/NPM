package us.dontcareabout.npm.client.ui;

import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.sencha.gxt.chart.client.draw.Color;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.chart.client.draw.sprite.SpriteOutEvent;
import com.sencha.gxt.chart.client.draw.sprite.SpriteOverEvent;
import us.dontcareabout.gxt.client.draw.LayerSprite;
import us.dontcareabout.npm.client.DateInterval;

public class TimelineSprite extends LayerSprite {
	private int dayHeight = 5;
	private int lineWidth = 60;
	private DateInterval interval;

	private int dayShift = 5;

	public int getDayHeight() {
		return dayHeight;
	}

	public void setDayHeight(int dayHeight) {
		this.dayHeight = dayHeight;
	}

	public int getLineWidth() {
		return lineWidth;
	}

	public void setLineWidth(int lineWidth) {
		this.lineWidth = lineWidth;
	}

	public int getDayShift() {
		return dayShift;
	}

	public void setDayShift(int dayShift) {
		this.dayShift = dayShift;
	}

	public TimelineSprite() {
	}

	public TimelineSprite(DateInterval interval) {
		this.interval = interval;

		int days = interval.getDays() + (dayShift * 2) + 1;
		Line line = new Line(days);
		add(line);
	}

	public void addLine(DateInterval interval, boolean isClose, SpriteOverEvent.SpriteOverHandler handler) {
		int days = interval.getDays() + 1;
		Line line = new Line(days);

		int y = CalendarUtil.getDaysBetween(this.interval.getStart(), interval.getStart()) + dayShift;
		line.setLY(y * dayHeight);
		line.setClose(isClose);

		line.addSpriteOutHandler(new SpriteOutEvent.SpriteOutHandler() {
			@Override
			public void onSpriteLeave(SpriteOutEvent spriteOutEvent) {
				ChartContainer.info.hide();
			}
		});

		line.addSpriteOverHandler(handler);

		add(line);
	}

	class Line extends LayerSprite {
		// TODO: 設定顏色
		private Color bgColor = RGB.CYAN;
		private Color closeColor = RGB.RED;
		private Color openColor = RGB.ORANGE;

		Line(int days) {
			resize(lineWidth, days * dayHeight);
			setBgColor(bgColor);
		}

		void setClose(boolean isClose) {
			if (isClose) {
				setBgColor(closeColor);
				setLZIndex(3);
			} else {
				setBgColor(openColor);
				setLZIndex(2);
			}
		}
	}
}

