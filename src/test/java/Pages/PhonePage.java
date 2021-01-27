package Pages;

import Core.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PhonePage extends BasePage {
    public PhonePage(Browser browser) {
        super(browser);
    }

    String maxPrice = "//input[@name='Цена до']";
    String apple = "//span[text()='Apple']";
    String bq = "//span[text()='BQ']";
    String honor = "//span[text()='HONOR']";
    String huawei = "//span[text()='HUAWEI']";
    String nokia = "//span[text()='Nokia']";
    String minDiagonal = "//input[@name='Диагональ экрана (точно) до']";
    String newestPhones = "//button[text()='по новизне']";

    public void selectFilters () {
        sendKeysWithClearInteger(maxPrice, 20000);
        click(By.xpath(apple));
        click(By.xpath(bq));
        click(By.xpath(honor));
        click(By.xpath(huawei));
        click(By.xpath(nokia));
        sendKeysWithClearInteger(minDiagonal, 3);
    }

    public void checkCountPhones() {
        int standardCount = 48;
        int factCount = getCountElements("//article[@data-autotest-id='product-snippet']");
        if (standardCount == factCount) {
            System.out.println("Количество элементов совпадает");
        }
        else {
            System.out.println("WARNING! Количество элементов НЕ совпадает");
        }
    }
/*
    public WebElement firstItem () {
        return getFirstItemCatalog("//article[@data-autotest-id='product-snippet'][1]");
    }

    WebElement memoryItem = firstItem();

*/

    public void updateSorting() {
        click(newestPhones);
    }
/*
    public void searchFirstItem () {
        click((By) memoryItem);
    }
*/
}
