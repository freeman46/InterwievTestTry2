package Core;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Browser {
    private String PROPERTIES_FILE = "properties\\browser.properties";
    private String browserType = "";
    private String implicitlyTimeout;
    DesiredCapabilities capabilities;
    protected WebDriver driver;
    private String HOST = "";
    protected Logger log;
    private Long explicitlyTimeout = 0L;
    Core.Config conf = new Core.Config();

    public Browser() {
        this.initFromFile();
    }

    public void sleep(double seconds) {
        try {
            log.debug("Wait [" + seconds + "] seconds...");
            Thread.sleep((long) (seconds * 1000.0D));
        } catch (Exception var) {
            ;
        }
    }

    public void scrollPageToElement(By by) {
        WebElement element = this.driver.findElement(by);
        ((Locatable) element).getCoordinates().inViewPort();
    }

    public boolean waitForAjaxQueryComplete(long seconds) {
        boolean res = true;
        WebDriverWait wait = new WebDriverWait(this.driver, seconds);
        try {
            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    long counter = (Long) ((JavascriptExecutor) d).executeScript("return window.jQuery.active", new Object[0]);
                    return counter == 0L;
                }
            });
            sleep(0.3D);
        } catch (Exception var) {
            res = false;
        }
        return res;
    }

    public void initFromFile() {
        conf.loadFromFile(this.PROPERTIES_FILE);
        this.browserType = conf.getProperty("browser");
        this.HOST = conf.getProperty("http");
        implicitlyTimeout = conf.getProperty("implicitlyTimeout");
        switch (browserType) {
            case "chrome":
                capabilities = DesiredCapabilities.chrome();
                capabilities.setBrowserName(browserType);
                capabilities.setPlatform(Platform.WINDOWS);
                capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                HashMap<String, String> chromePrefs = new HashMap<>();
                //включение защиты, чтоб скачивались файлы XML без предупреждения об опасности
                chromePrefs.put("safebrowsing.enabled", "true");
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", chromePrefs);
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--aggressive-cache-discard");
                options.addArguments("--disable-cache");
                options.addArguments("--disable-application-cache");
                options.addArguments("--disable-offline-load-stale-cache");
                options.addArguments("--disk-cache-size=0");
                options.addArguments("--disable-gpu");
                options.addArguments("--dns-prefetch-disable");
                options.addArguments("--no-proxy-server");
                options.addArguments("--log-level=3");
                options.addArguments("--silent");
                options.addArguments("--disable-browser-side-navigation");
                options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                options.setProxy(null);
                // путь до хрома
                options.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
                initChrome();
                break;
        }
    }

    public void initChrome() {
        try {
            this.driver = new ChromeDriver(capabilities);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setImplicitlyWait(implicitlyTimeout);
    }

    public WebDriver getWebDriver() {
        if (this.driver == null) {
            this.initFromFile();
        }
        return this.driver;
    }

    public void openHost() {
        System.out.println("Открытие главной страницы.( " + this.HOST + " ).");
        try {
            this.driver.get(this.HOST);
            this.driver.manage().window().maximize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void quit() {
        try {
            this.driver.quit();
        } catch (Exception var) {
            ;
        }
        this.driver = null;
    }

    public boolean setImplicitlyWait(String seconds) {
        Boolean result = true;
        try {
            if (this.driver != null) {
                this.implicitlyTimeout = seconds.trim();
                this.driver.manage().timeouts().implicitlyWait(Long.valueOf(this.implicitlyTimeout), TimeUnit.SECONDS);
            } else {
                result = false;
            }
        } catch (Exception var) {
            result = false;
        }
        return result;
    }

    public Long getImplicitlyWait() {
        return Long.valueOf(this.implicitlyTimeout);
    }

    public Long getExplicitlyWait() {
        return explicitlyTimeout;
    }

    public boolean waitForElementNotDisplay(final By by) {
        boolean res = true;
        WebDriverWait wait = new WebDriverWait(driver, getExplicitlyWait());
        long q = this.getImplicitlyWait();
        this.driver.manage().timeouts().implicitlyWait(1L, TimeUnit.SECONDS);
        try {
            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    boolean result = false;
                    try {
                        result = d.findElement(by).isDisplayed();
                        result = !result;
                    } catch (Exception var) {
                        Browser.this.log.debug("not displayed exception");
                        result = true;
                    }
                    if (result) {
                        Browser.this.log.debug("not displayed");
                    } else {
                        Browser.this.log.debug("displayed");
                    }
                    return result;
                }
            });
        } catch (Exception var) {
            res = false;
        }
        this.driver.manage().timeouts().implicitlyWait(q, TimeUnit.SECONDS);
        return res;
    }
}
