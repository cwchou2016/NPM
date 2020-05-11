package us.dontcareabout.npm.client;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.dom.ScrollSupport;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.Viewport;
import us.dontcareabout.gwt.client.GFEP;
import us.dontcareabout.npm.client.ui.ChartContainer;

import java.util.Date;

public class NPMEP extends GFEP {
	private final static String SHEET_ID = "1xRkXYlAioJe44AvhLpxuyZLfkvkmz9L0sPZo67feKH0";

	public NPMEP() {
	}

	@Override
	protected String version() {
		return "0.0.1";
	}

	@Override
	protected String defaultLocale() {
		return "zh_TW";
	}

	@Override
	protected void featureFail() {
		Window.alert("這個瀏覽器我不尬意，不給用..... \\囧/");
	}

	@Override
	protected void start() {
		DateTimeFormat dateFormat = DateTimeFormat.getFormat("yyyyMMdd");
		Date d1 = dateFormat.parse("20190101");
		Date d2 = dateFormat.parse("20221231");

		ChartContainer cc = new ChartContainer("故宮南院展覽資訊", new DateInterval(d1, d2));


		VerticalLayoutContainer vlc = new VerticalLayoutContainer();
		vlc.setScrollMode(ScrollSupport.ScrollMode.AUTO);
		//不限制小孩的大小，所以小孩一旦比自己大就會出現 scroll
		vlc.add(cc, new VerticalLayoutContainer.VerticalLayoutData(-1, -1));
		vlc.add(cc);
		Viewport vp = new Viewport();
		vp.add(vlc);

		RootPanel.get().add(vp);
	}
}
