package us.dontcareabout.npm.client.ui;

import com.sencha.gxt.chart.client.draw.RGB;
import us.dontcareabout.gxt.client.draw.LayerSprite;
import us.dontcareabout.gxt.client.draw.component.TextButton;

/**
 * 只負責 tooltip 的行為跟顯示大小
 */
public class TooltipLayer extends LayerSprite {
	private TextButton infoText = new TextButton();

	public TooltipLayer() {
		resize(200, 300);
		setBgColor(RGB.YELLOW);
		add(infoText);
		setLZIndex(3000);
		hide();
	}

	/**
	 * 更新 tooltip 內容
	 */
	public void updateInfo(String info) {
		infoText.setText(info);
	}

	public void show(double x, double y) {
		setLX(x + 10);
		setLY(y + 10);
	}

	public void hide() {
		setLX(-1000);
		setLY(-1000);
	}
}
