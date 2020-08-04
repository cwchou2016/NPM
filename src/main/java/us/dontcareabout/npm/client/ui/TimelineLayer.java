package us.dontcareabout.npm.client.ui;

import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.sencha.gxt.chart.client.draw.Color;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.chart.client.draw.sprite.SpriteOutEvent;
import com.sencha.gxt.chart.client.draw.sprite.SpriteOverEvent;
import us.dontcareabout.gxt.client.draw.LayerSprite;
import us.dontcareabout.npm.client.DateInterval;

import java.util.ArrayList;
import java.util.List;

/**
 * 時間軸，上面標示展廳開放日期，及換展關閉日期。
 * 在展廳開放或換展關閉期間的標示上有 tooltip 的功能，可顯示詳細展覽資訊。
 */
public class TimelineLayer extends LayerSprite {
	//TODO: 調整顏色
	private static final Color BG_COLOR = RGB.CYAN;
	private static final Color CLOSE_COLOR = RGB.RED;
	private static final Color OPEN_COLOR = RGB.ORANGE;

	private int days;
	private int y;
	private int shiftDays;
	private DateInterval interval;
	private List<TimelineLayer> markList = new ArrayList<>();


	public TimelineLayer(DateInterval interval, int shiftDays) {
		this.interval = interval;
		this.shiftDays = shiftDays;

		days = interval.getDays() + 1 + (shiftDays * 2);
		setBgColor(BG_COLOR);
	}

	/**
	 * 在 Timeline 上增加展間區間標記
	 */
	public TimelineLayer addMark(DateInterval dateInterval, boolean isClose) {
		TimelineLayer mark = new TimelineLayer(dateInterval, 0);

		if (isClose) {
			mark.setBgColor(CLOSE_COLOR);
			mark.setLZIndex(3);
		} else {
			mark.setBgColor(OPEN_COLOR);
			mark.setLZIndex(2);
		}

		mark.y = CalendarUtil.getDaysBetween(interval.getStart(), mark.interval.getStart()) + shiftDays;
		add(mark);
		markList.add(mark);
		return mark;
	}

	public void addTooltip(final String info) {
		addSpriteOverHandler(new SpriteOverEvent.SpriteOverHandler() {
			@Override
			public void onSpriteOver(SpriteOverEvent spriteOverEvent) {
				UiCenter.fire(new OnMarkOverEvent(spriteOverEvent, info));
			}
		});

		addSpriteOutHandler(new SpriteOutEvent.SpriteOutHandler() {
			@Override
			public void onSpriteLeave(SpriteOutEvent spriteOutEvent) {
				UiCenter.fire(new OnMarkLeaveEvent());
			}
		});
	}

	@Override
	protected void adjustMember() {
		super.adjustMember();
		double dayHeight = getHeight() / days;

		for (TimelineLayer mark : markList) {
			mark.resize(getWidth(), mark.days * dayHeight);
			mark.setLY(mark.y * dayHeight);
		}
	}
}
