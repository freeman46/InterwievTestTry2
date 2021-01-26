package Pages;

import Core.Browser;
import org.openqa.selenium.By;

public class MarketPage extends BasePage{
    public MarketPage (Browser browser) {
        super(browser);
    }


    String catalog = "//button[@class,'zsSJkfeAPw_16jABpOZ2-gjdzW5ajbI_3WgR56k47x']";
    String electronics = "//*[contains(text(), 'Электроника')]";

    public void goToElectronics() {
        click(catalog);
        checkElement(By.xpath("//*[contains(text(), 'Электроника')])"));
        click(electronics);
    }

}
