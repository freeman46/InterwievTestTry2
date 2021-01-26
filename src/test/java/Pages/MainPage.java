package Pages;

import Core.Browser;

public class MainPage extends BasePage {
    public MainPage (Browser browser) {
        super(browser);
    }
    String market = "//div[text() = 'Маркет']";

    public void goToMarket() {
        click(market);
    }

}
