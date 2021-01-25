package Test;

import Core.Browser;
import Pages.ElectronicsPage;
import Pages.MainPage;
import Pages.MarketPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class YandexTest {
    Browser b;
    private MainPage mainpage;
    private MarketPage marketpage;
    private ElectronicsPage electronicspage;

    @BeforeClass
    public void beforeClass() {
        b = new Browser();//инициализируем браузер
        b.openHost();//открытие главной страницы
        mainpage = new MainPage(b);
        marketpage = new MarketPage(b);
        electronicspage = new ElectronicsPage(b);
    }

    @AfterClass
    public void afterClass() {
        b.quit();
    }

    @Test
    public void Run () {
        mainpage.goToMarket();
        marketpage.goToElectronics();
        electronicspage.goToPhones();
    }



}
