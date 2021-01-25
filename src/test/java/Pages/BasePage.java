package Pages;

import Core.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BasePage {
    public BasePage(Browser b) {
        this.browser = b;
    }
    protected final Browser browser;
    public void sendKeysWithClear(String locator, String text) {
        browser.getWebDriver().findElement(By.xpath(locator)).clear();
        browser.getWebDriver().findElement(By.xpath(locator)).sendKeys(new CharSequence[]{text});
    }

    public void click(String locator) {
        scrollPageToElement(locator);
        click(By.xpath(locator));
    }
    protected void scrollPageToElement(String locator) {
        this.browser.scrollPageToElement(By.xpath(locator));
    }
    public void click(By by) {
        this.browser.waitForAjaxQueryComplete(this.browser.getImplicitlyWait());
        this.browser.getWebDriver().findElement(by).click();
    }

    /*public String[] getCatalogItem(String navBar) {
        List<WebElement> e = browser.getWebDriver().findElements(By.xpath())
    }*/

}
