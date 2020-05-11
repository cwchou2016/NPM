package us.dontcareabout.npm.client.ui;

import com.sencha.gxt.chart.client.draw.RGB;
import us.dontcareabout.gxt.client.draw.LTextSprite;
import us.dontcareabout.gxt.client.draw.LayerSprite;


public class ExhibitionInfoSprite extends LayerSprite {
	private LTextSprite title = new LTextSprite("");
	private LTextSprite content = new LTextSprite("");


	public ExhibitionInfoSprite(int width, int height) {
		//TODO: 設計顯示格式
		resize(width, height);
		setLZIndex(3000);
		setBgColor(RGB.YELLOW);
		title.setLY(5);
		content.setLY(50);
		add(title);
		add(content);

		hide();
	}

	public void hide() {
		setInfo("", "");
		setLX(-1000);
		setLY(-1000);
	}

	public void setInfo(String title, String content) {
		this.title.setText(title);
		this.content.setText(content);
	}
}
