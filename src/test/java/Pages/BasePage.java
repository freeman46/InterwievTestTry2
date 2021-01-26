package Pages;

import Core.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    public BasePage(Browser b) {
        this.browser = b;
    }
    protected final Browser browser;
    public void sendKeysWithClearString(String locator, String text) {
        browser.getWebDriver().findElement(By.xpath(locator)).clear();
        browser.getWebDriver().findElement(By.xpath(locator)).sendKeys(new CharSequence[]{text});
    }

    public void sendKeysWithClearInteger(String locator, Integer value) {
        browser.getWebDriver().findElement(By.xpath(locator)).clear();
        browser.getWebDriver().findElement(By.xpath(locator)).sendKeys(value.toString());
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

    public void checkElement(By by) {
        WebDriverWait wait = new WebDriverWait(browser.getWebDriver(), 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public int getCountElements (String locator) {
        return browser.getWebDriver().findElements(By.xpath(locator)).size();
    }



}
