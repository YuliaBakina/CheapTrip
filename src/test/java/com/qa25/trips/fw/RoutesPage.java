package com.qa25.trips.fw;

import com.qa25.trips.model.Cities;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

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

    public void readRoutesData() throws InterruptedException {
        //read search results
        List<WebElement> resultList = new ArrayList<>();
        resultList.clear();
        delay(1000);

        resultList = getItemsList(By.cssSelector("[class='ng-star-inserted item md ion-focusable hydrated']"));

        int count = 1;
        //read details for each route and its subroutes
        for(WebElement t_e : resultList){

            String totalDuration = t_e.findElement(By.cssSelector("[class='time']")).getText().trim();
            String totalPrice = t_e.findElement(By.cssSelector("[color='primary']")).getText().trim();


            //convert trip duration - too many different options for d/h/m
            String durD = "0", durH = "0", durM = "0";
            String[] duration = totalDuration.split(" ");
                for (int j = 0; j < duration.length; j++) {
                    if(totalDuration.contains("d")){
                        durD = totalDuration.split("d")[0].trim();
                    }
                    if (duration[j].contains("h")) {
                        durH = duration[j].split("h")[0].trim();
                    }
                    if (duration[j].contains("min")) {
                        durM = duration[j].split("min")[0].trim();
                    }
                }

            //convert trip duration into minutes
            int tripDuration = Integer.parseInt(durD) * 24 * 60 + Integer.parseInt(durH) * 60 + Integer.parseInt(durM);

            //convert trip price
            totalPrice = totalPrice.split("€")[1];
            double tripPrice = Double.parseDouble(totalPrice);

            int subTripDuration = 0;
            double subTripPrice = 0;

            //for each route read its subroutes
            Actions actions = new Actions(wd);
            actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
            t_e.findElement(By.cssSelector("[class='mat-accordion']")).click();
            delay(500);
            actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
            delay(500);

            List<WebElement> resultSubList = new ArrayList<>();
            resultSubList.clear();
            resultSubList = t_e.findElements(By.cssSelector("[class='ion-no-margin sc-ion-label-md-h sc-ion-label-md-s md hydrated']"));

            //read details for each subroute
            for(WebElement s_e : resultSubList){
                String subDuration = s_e.findElement(By.cssSelector("[class='time']")).getText().trim();
                String subPrice = s_e.findElement(By.cssSelector("[class='currency']")).getText().trim();

                //convert trip duration - too many different options for d/h/m
                durD = "0";
                durH = "0";
                durM = "0";
                duration = subDuration.split(" ");
                for (int j = 0; j < duration.length; j++) {
                    if(subDuration.contains("d")){
                        durD = subDuration.split("d")[0].trim();
                    }
                    if (duration[j].contains("h")) {
                        durH = duration[j].split("h")[0].trim();
                    }
                    if (duration[j].contains("min")) {
                        durM = duration[j].split("min")[0].trim();
                    }
                }

                //convert trip duration into minutes
                subTripDuration = subTripDuration + Integer.parseInt(durD) * 24 * 60 + Integer.parseInt(durH) * 60 + Integer.parseInt(durM);

                //convert trip price
                subTripPrice = subTripPrice + Double.parseDouble(subPrice.split("€")[1]);

            }

            //log the differences
            logger.info("Route#" + count + ": differences between total and subtotal durations = " + (tripDuration - subTripDuration) +
                    ", between total and subtotal prices = " + (tripPrice - subTripPrice));

            count++;
        }


    }

    public void clickNewSearchButton() {
        click(By.xpath("//a [@role='button']"));
    }
}
