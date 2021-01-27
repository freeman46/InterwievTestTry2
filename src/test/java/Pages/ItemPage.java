package Pages;

import Core.Browser;

public class ItemPage extends BasePage {
    public ItemPage (Browser browser) {
        super(browser);
    }

    String ratingItem = "//span[@class='_3nFvoU2Uov']";

    public String getRating () {
        return getRatingItem(ratingItem);
    }

    String rating = getRating();

    public void printRating () {
        System.out.println(rating);
    }





}
