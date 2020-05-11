package us.dontcareabout.npm.client.ui;

import us.dontcareabout.gxt.client.draw.LTextSprite;
import us.dontcareabout.gxt.client.draw.LayerContainer;
import us.dontcareabout.gxt.client.draw.LayerSprite;
import us.dontcareabout.gxt.client.draw.layout.HorizontalLayoutLayer;
import us.dontcareabout.gxt.client.draw.layout.VerticalLayoutLayer;
import us.dontcareabout.npm.client.DateInterval;

public class ChartContainer extends LayerContainer {
	private VerticalLayoutLayer vLayer = new VerticalLayoutLayer();
	private ExhibitionChartLayer ecLayer;

	public ChartContainer(String title, DateInterval interval) {
		Header header = new Header(title);
		vLayer.addChild(header, 100);
		addLayer(vLayer);

		loadData(interval);
	}

	public void loadData(DateInterval interval) {
		// TODO: 傳入 List<Exhibition>
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
		}
	}
}
