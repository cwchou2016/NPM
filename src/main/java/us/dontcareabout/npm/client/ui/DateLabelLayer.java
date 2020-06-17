package us.dontcareabout.npm.client.ui;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.sencha.gxt.chart.client.draw.RGB;
import us.dontcareabout.gxt.client.draw.LTextSprite;
import us.dontcareabout.gxt.client.draw.LayerSprite;
import us.dontcareabout.npm.client.DateInterval;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateLabelLayer extends LayerSprite {
	private List<DateLabel> labelList = new ArrayList<>();
	int days;

	public DateLabelLayer(DateInterval interval, int shiftDays) {
		setBgColor(RGB.NONE);
		this.days = interval.getDays();
		Date date = new Date(interval.getStart().getTime());
		CalendarUtil.setToFirstDayOfMonth(date);
		DateTimeFormat dateFormat = DateTimeFormat.getFormat("yyyy-MM");

		while (date.before(interval.getEnd())) {
			DateLabel label = new DateLabel(dateFormat.format(date));
			label.y = CalendarUtil.getDaysBetween(interval.getStart(), date) + shiftDays;
			add(label);
			labelList.add(label);
			CalendarUtil.addMonthsToDate(date, 1);
		}
	}

	@Override
	protected void adjustMember() {
		double dayHeight = getHeight() / days;

		for (DateLabel label : labelList) {
			label.setLY(label.y * dayHeight);
			label.setLX(getWidth() - 10);
		}
	}

	class DateLabel extends LTextSprite {
		int y;

		DateLabel(String text) {
			super(text);
			setTextAnchor(TextAnchor.END);
		}
	}
}
