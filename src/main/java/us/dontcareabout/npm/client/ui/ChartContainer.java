package us.dontcareabout.npm.client.ui;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.chart.client.draw.sprite.SpriteSelectionEvent;
import us.dontcareabout.gxt.client.draw.LTextSprite;
import us.dontcareabout.gxt.client.draw.LayerContainer;
import us.dontcareabout.gxt.client.draw.LayerSprite;
import us.dontcareabout.gxt.client.draw.component.TextButton;
import us.dontcareabout.gxt.client.draw.layout.HorizontalLayoutLayer;
import us.dontcareabout.gxt.client.draw.layout.VerticalLayoutLayer;
import us.dontcareabout.npm.client.DateInterval;

import java.util.Date;

public class ChartContainer extends LayerContainer {
	public final static ExhibitionInfoSprite info = new ExhibitionInfoSprite(200, 300);

	private VerticalLayoutLayer vLayer = new VerticalLayoutLayer();
	private Header header;
	private ExhibitionChartLayer ecLayer = new ExhibitionChartLayer();

	public ChartContainer(String title, DateInterval interval) {
		header = new Header(title);
		vLayer.addChild(header, 100);
		vLayer.addChild(ecLayer, 1);
		addLayer(vLayer);
		addLayer(info);

		createYearButtons(interval);
		loadData(interval);
	}

	public void createYearButtons(DateInterval interval) {
		DateTimeFormat yearFormat = DateTimeFormat.getFormat("yyyy");
		int startYear = Integer.parseInt(yearFormat.format(interval.getStart()));
		int endYear = Integer.parseInt(yearFormat.format(interval.getEnd()));

		for (int idx = startYear; idx <= endYear; idx++) {
			header.addButton(String.valueOf(idx));
		}

		header.redeploy();
	}

	public void loadData(DateInterval interval) {
		// TODO: 傳入 List<Exhibition>

		ecLayer.updateChart(interval);
		// 測試資料處理
		ecLayer.addRoom("S301");
		ecLayer.addRoom("S302");
		ecLayer.addRoom("S303");

		DateTimeFormat dateFormat = DateTimeFormat.getFormat("yyyyMMdd");
		Date d1 = dateFormat.parse("20190201");
		Date d2 = dateFormat.parse("20190228");

		Date d3 = dateFormat.parse("20200201");
		Date d4 = dateFormat.parse("20200228");

		ecLayer.addExhibition("S301", new DateInterval(d1, d2), true);
		ecLayer.addExhibition("S303", new DateInterval(d3, d4), false);

		///////////////////

		vLayer.redeploy();
		vLayer.resize(2000, 3000);
		setSize("2000", "20000"); // 這裡可以設定成動態大小嗎？
	}

	@Override
	protected void adjustMember(int width, int height) {
		super.adjustMember(width, height);
		vLayer.resize(width, height);
	}

	class Header extends HorizontalLayoutLayer {
		Header(String title) {
			LTextSprite titleText = new LTextSprite(title);
			titleText.setFontSize(62);
			LayerSprite titleSprite = new LayerSprite();
			titleSprite.add(titleText);
			addChild(titleSprite, 500);
			setGap(10);
		}

		void addButton(final String name) {
			TextButton button = new TextButton(name);
			button.setBgColor(RGB.GRAY);
			button.setBgRadius(20);
			button.setMargin(5);

			button.addSpriteSelectionHandler(new SpriteSelectionEvent.SpriteSelectionHandler() {
				@Override
				public void onSpriteSelect(SpriteSelectionEvent spriteSelectionEvent) {
					DateTimeFormat dateFormat = DateTimeFormat.getFormat("yyyyMMdd");
					DateInterval di = new DateInterval(dateFormat.parse(name + "0101"), dateFormat.parse(name + "1231"));

					loadData(di);
				}
			});
			addChild(button, 100);
		}
	}
}
