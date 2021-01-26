package Pages;

import Core.Browser;
import org.openqa.selenium.By;

public class MarketPage extends BasePage{
    public MarketPage (Browser browser) {
        super(browser);
    }


    String catalog = "/html/body/div[2]/div[3]/noindex/div/div/div[2]/div[1]/div/button";
    String electronics = "//*[contains(text(), 'Электроника')]";

    public void goToElectronics() {
        click(catalog);
        checkElement(By.xpath("//*[contains(text(), 'Электроника')])"));
        click(electronics);
    }

}
