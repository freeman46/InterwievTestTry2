package Pages;

import Core.Browser;
import org.openqa.selenium.By;

public class MarketPage extends BasePage{
    public MarketPage (Browser browser) {
        super(browser);
    }
/*
    public void findCssSelector(String locator) {
        browser.getWebDriver().findElement(By.cssSelector(locator));
        click(By.cssSelector(locator));
    }
*/

    String catalog = "//span[@text()=‘Каталог’]";
    String electronics = "//*[contains(text(), 'Электроника')]";

    public void goToElectronics() {
        click(catalog);
        click(electronics);
    }
}
