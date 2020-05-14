package us.dontcareabout.npm.client.ui;

import com.sencha.gxt.chart.client.draw.Color;
import com.sencha.gxt.chart.client.draw.RGB;
import us.dontcareabout.gxt.client.draw.LayerSprite;
import us.dontcareabout.npm.client.DateInterval;

/**
 * 時間軸，上面標示展廳開放日期，及換展關閉日期。
 * 在展廳開放或換展關閉期間的標示上有 tooltip 的功能，可顯示詳細展覽資訊。
 */
public class TimelineSprite extends LayerSprite {
	private int lineWidth;
	private int dayHeight;
	private int shiftDays;
	private DateInterval interval;

	//TODO: 調整顏色
	private Color bgColor = RGB.CYAN;
	private Color closeColor = RGB.RED;
	private Color openColor = RGB.ORANGE;

	public TimelineSprite(DateInterval interval, int lineWidth, int dayHeight, int shiftDays) {
		this.interval = interval;
		this.lineWidth = lineWidth;
		this.dayHeight = dayHeight;
		this.shiftDays = shiftDays;

		LayerSprite mark = createMark(interval, bgColor);
		mark.resize(mark.getWidth(), mark.getHeight() + shiftDays * dayHeight);
		add(mark);
	}

	/**
	 * @return 日期區間標記 {@link LayerSprite}
	 */
	private LayerSprite createMark(DateInterval dateInterval, Color color) {
		LayerSprite mark = new LayerSprite();
		mark.resize(lineWidth, (dateInterval.getDays() + 1) * dayHeight);
		mark.setBgColor(color);
		return mark;
	}

	/**
	 * 在 Timeline 上增加展間區間標記
	 */
	public void addMark(DateInterval dateInterval, boolean isClose) {
		LayerSprite mark;

		if (isClose) {
			mark = createMark(dateInterval, closeColor);
			mark.setLZIndex(3);
		} else {
			mark = createMark(dateInterval, openColor);
			mark.setLZIndex(2);
		}

		int days = dateInterval.getDays();
		int y = (days + shiftDays) * dayHeight;
		mark.setLY(y);

		add(mark);
	}
}
