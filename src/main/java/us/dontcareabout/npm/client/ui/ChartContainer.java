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

public class ChartContainer extends LayerContainer {
	private VerticalLayoutLayer vLayer = new VerticalLayoutLayer();
	private Header header;
	private ExhibitionChartLayer ecLayer;

	public ChartContainer(String title, DateInterval interval) {
		header = new Header(title);
		vLayer.addChild(header, 100);
		addLayer(vLayer);

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
		vLayer.remove(ecLayer);
		ecLayer = new ExhibitionChartLayer(interval);
		vLayer.addChild(ecLayer, 1);
		vLayer.redeploy();
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
