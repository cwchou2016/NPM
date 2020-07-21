package us.dontcareabout.npm.client.ui;

import us.dontcareabout.gxt.client.draw.LayerSprite;

/**
 * 只負責 tooltip 的行為跟顯示大小
 */
public class TooltipLayer extends LayerSprite {

	public TooltipLayer() {
		resize(200, 300);
		setLZIndex(3000);
		hide();
	}

	public void show(double x, double y) {
		setLX(x);
		setLY(y);
		setHidden(false);
	}

	public void hide() {
		setLX(-1000);
		setLY(-1000);
		setHidden(true);
	}
}
