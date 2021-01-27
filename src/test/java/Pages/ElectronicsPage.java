package Pages;

import Core.Browser;

public class ElectronicsPage extends BasePage{
    public ElectronicsPage (Browser browser) {
        super(browser);
    }
    String smartphone = "//a[text()='Смартфоны']";

    public void goToPhones() {
        click(smartphone);
    }

}
