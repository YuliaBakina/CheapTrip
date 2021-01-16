package com.qa25.trips.fw;

import com.qa25.trips.model.Cities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RoutesPage extends HelperBase{
    public RoutesPage(WebDriver wd) {
        super(wd);
    }


    public void clickBackButton() {
        click(By.cssSelector("[defaulthref='/places/tabs/discover']"));
    }

    public boolean ifRoutesPageOpened() {
        if(getText(By.cssSelector(".select.buttons-last-slot.sc-ion-buttons-md-h.sc-ion-buttons-md-s.md.hydrated")).equals("En")){
            return isElementPresent(By.xpath("//ion-title[contains(text(),'Available routes')]"));
        }
        if(getText(By.cssSelector(".select.buttons-last-slot.sc-ion-buttons-md-h.sc-ion-buttons-md-s.md.hydrated")).equals("Ru")){
            return isElementPresent(By.xpath("//ion-title[contains(text(),'Варианты маршрутов')]"));
        }
        return false;
    }

    public boolean ifRoutesPresent(Cities cities) throws InterruptedException {
        delay(1000);
        String frCity = getText(By.xpath("//ion-label[@class='sc-ion-label-md-h sc-ion-label-md-s md hydrated']//p//span[1]"));
        String tCity = getText(By.xpath("//ion-label[@class='sc-ion-label-md-h sc-ion-label-md-s md hydrated']//p//span[4]"));
        boolean fromCity = frCity.equals(cities.getFromCity());
        boolean toCity = tCity.equals(cities.getToCity());
        if(!fromCity) {
            logger.info("   --> Incorrect FROM city is displayed: " + frCity + " instead of " + cities.getFromCity());
        }
        if(!toCity) {
            logger.info("   --> Incorrect TO city is displayed: " + tCity + " instead of " + cities.getToCity());
        }
        return (fromCity && toCity);
    }

}
