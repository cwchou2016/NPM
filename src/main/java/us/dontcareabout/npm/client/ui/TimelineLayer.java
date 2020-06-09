package us.dontcareabout.npm.client.ui;

import com.sencha.gxt.chart.client.draw.Color;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.chart.client.draw.sprite.SpriteOutEvent;
import com.sencha.gxt.chart.client.draw.sprite.SpriteOverEvent;
import us.dontcareabout.gxt.client.draw.LayerSprite;
import us.dontcareabout.npm.client.DateInterval;

/**
 * 時間軸，上面標示展廳開放日期，及換展關閉日期。
 * 在展廳開放或換展關閉期間的標示上有 tooltip 的功能，可顯示詳細展覽資訊。
 */
public class TimelineLayer extends LayerSprite {
	//TODO: 調整顏色
	private static final Color BG_COLOR = RGB.CYAN;
	private static final Color CLOSE_COLOR = RGB.RED;
	private static final Color OPEN_COLOR = RGB.ORANGE;

	private int lineWidth;
	private int dayHeight;
	private int shiftDays;
	private DateInterval interval;


	public TimelineLayer(DateInterval interval, int lineWidth, int dayHeight, int shiftDays) {
		this.interval = interval;
		this.lineWidth = lineWidth;
		this.dayHeight = dayHeight;
		this.shiftDays = shiftDays;


		resize(lineWidth, (interval.getDays() + 1 + shiftDays) * dayHeight);
		setBgColor(BG_COLOR);
	}

	/**
	 * 在 Timeline 上增加展間區間標記
	 */
	public void addMark(DateInterval dateInterval, boolean isClose) {
		TimelineLayer mark = new TimelineLayer(dateInterval, lineWidth, dayHeight, 0);

		if (isClose) {
			mark.setBgColor(CLOSE_COLOR);
			mark.setLZIndex(3);
		} else {
			mark.setBgColor(OPEN_COLOR);
			mark.setLZIndex(2);
		}

		mark.addSpriteOverHandler(new SpriteOverEvent.SpriteOverHandler() {
			@Override
			public void onSpriteOver(SpriteOverEvent spriteOverEvent) {
				UiCenter.fire(new OnMarkOverEvent());
			}
		});

		mark.addSpriteOutHandler(new SpriteOutEvent.SpriteOutHandler() {
			@Override
			public void onSpriteLeave(SpriteOutEvent spriteOutEvent) {
				UiCenter.fire(new OnMarkLeaveEvent());
			}
		});

		int days = dateInterval.getDays();
		int y = (days + shiftDays) * dayHeight;
		mark.setLY(y);

		add(mark);
	}
}
