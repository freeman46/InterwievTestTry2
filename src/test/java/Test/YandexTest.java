package Test;

import Core.Browser;
import Pages.ElectronicsPage;
import Pages.MainPage;
import Pages.MarketPage;
import Pages.PhonePage;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class YandexTest {
    Browser b;
    private MainPage mainpage;
    private MarketPage marketpage;
    private ElectronicsPage electronicspage;
    private PhonePage phonepage;

    @BeforeClass
    public void beforeClass() {
        b = new Browser();//инициализируем браузер
        b.openHost();//открытие главной страницы
        mainpage = new MainPage(b);
        marketpage = new MarketPage(b);
        electronicspage = new ElectronicsPage(b);
        phonepage = new PhonePage(b);
    }

    @AfterClass
    public void afterClass() {
        b.quit();
    }

    @Test
    public void Run () {
        mainpage.goToMarket();
        marketpage.checkElement(By.xpath("/html/body/div[2]/div[3]/noindex/div/div/div[2]/div[1]/div/button"));
        marketpage.goToElectronics();
        electronicspage.checkElement(By.xpath("//*[contains(text(), 'Смартфоны')]"));
        electronicspage.goToPhones();
        phonepage.selectFiltrs();
        phonepage.checkCountPhones();

    }



}
