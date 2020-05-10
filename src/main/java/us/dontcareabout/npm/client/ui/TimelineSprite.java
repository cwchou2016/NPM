package us.dontcareabout.npm.client.ui;

import com.sencha.gxt.chart.client.draw.Color;
import com.sencha.gxt.chart.client.draw.RGB;
import us.dontcareabout.gxt.client.draw.LayerSprite;
import us.dontcareabout.npm.client.DateInterval;

public class TimelineSprite extends LayerSprite {
	private int dayHeight = 20;
	private int lineWidth = 30;
	private DateInterval interval;

	private int dayShift = 2;

	public TimelineSprite(DateInterval interval) {
		this.interval = interval;

		int days = interval.getDays() + dayShift * 2;
		Line line = new Line(days);
		add(line);
	}


	class Line extends LayerSprite {
		// TODO: 設定顏色
		private Color bgColor = RGB.CYAN;
		
		Line(int days) {
			resize(lineWidth, days * dayHeight);
			setBgColor(bgColor);
		}
	}
}

