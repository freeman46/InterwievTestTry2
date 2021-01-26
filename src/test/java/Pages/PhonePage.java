package Pages;

import Core.Browser;
import org.openqa.selenium.By;

public class PhonePage extends BasePage {
    public PhonePage(Browser browser) {
        super(browser);
    }

    String maxprice = "//input[@name='Цена до']";
    String apple = "//span[text()='Apple']";
    String bq = "//span[text()='BQ']";
    String honor = "//span[text()='HONOR']";
    String huawei = "//span[text()='HUAWEI']";
    String nokia = "//span[text()='Nokia']";
    String mindiagonal = "//input[@name='Диагональ экрана (точно) до']";

    public void selectFiltrs () {
        sendKeysWithClearInteger(maxprice, 20000);
        click(By.xpath(apple));
        click(By.xpath(bq));
        click(By.xpath(honor));
        click(By.xpath(huawei));
        click(By.xpath(nokia));
        sendKeysWithClearInteger(mindiagonal, 3);
    }

    public void checkCountPhones() {
        int standartCount = 10;
        int factCount = getCountElements("//article[@data-autotest-id='product-snippet']");
        if (standartCount==factCount) {
            System.out.println("Количество элементов совпадает");
        }
        else {
            System.out.println("WARNING! Количество элементов НЕ совпадает");
        }
    }

}
