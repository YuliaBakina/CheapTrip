package com.qa25.trips.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends HelperBase{


    public MainPage(WebDriver wd) {
        super(wd);
    }

    public void isMainPageOpened(String url){
        if(!wd.getCurrentUrl().contains(url)){
            wd.navigate().to(url);
        }
    }

    public void opeMainPage(String url){
           wd.navigate().to(url);
    }

    public boolean ifMainPageConsistLogo(){
        String logo = getText(By.cssSelector(".hide-xs.md.title-default.hydrated"));
        if(logo.contains("CheapTrip.")) {
            return true;
        }
        return false;
    }

    public boolean ifHamburgerMenuPresented() {
        return isElementPresent(By.xpath("//ion-menu-button"));
    }

    public void clickHamburgerMenu() {
        click(By.xpath("//ion-menu-button"));
    }

    public boolean ifCurrencyDrDownPresented() {
        return isElementPresent(By.cssSelector(".select.item-interactive.item-select.item-has-value.item.md.item-lines-none.ion-activatable.ion-focusable.hydrated"));
    }

    public void clickCurrencyDrDown(){
        click(By.cssSelector(".select.item-interactive.item-select.item-has-value.item.md.item-lines-none.ion-activatable.ion-focusable.hydrated"));
    }

    public boolean ifLanguageDrDownPresented() {
        return isElementPresent(By.cssSelector(".select.buttons-last-slot.sc-ion-buttons-md-h.sc-ion-buttons-md-s.md.hydrated"));
    }

    public void clickLanguageDrDown() {
        click(By.cssSelector(".select.buttons-last-slot.sc-ion-buttons-md-h.sc-ion-buttons-md-s.md.hydrated"));
    }

    public boolean ifSearchTitlePresented() {
        return isElementPresent(By.xpath("//ion-card-title"));
    }

    public String getMainPageTitle() {
        return getText(By.xpath("//ion-card-title"));
    }

    public boolean ifFromTitlePresented() {
        /*String title = getText(By.id("ion-input-0-lbl"));
        if(title.equals("From:")){
            return true;
        }
        return false;*/
        return isElementPresent(By.id("ion-input-0-lbl"));
    }

    public boolean ifToTitlePresented() {
        /*String title = getText(By.id("ion-input-1-lbl"));
        if(title.equals("To:")){
            return true;
        }
        return false;*/
        return isElementPresent(By.id("ion-input-1-lbl"));
    }

    public boolean ifClearButtonPresented() {
        return isElementPresent(By.xpath("//ion-button[1]"));
    }

    public void clickClearButton() {
        click(By.xpath("//ion-button[1]"));
    }

    public boolean ifGoButtonPresented() {
        return isElementPresent(By.xpath("//ion-button[2]"));
    }

    public void clickGoButton() {
        System.out.println("clickGoButton");
        click(By.xpath("//ion-button[2]"));
    }

    public boolean ifSearchIconPresented() {
        return isElementPresent(By.cssSelector("[tab='discover']"));
    }

    public void clickSearchIcon() {
        click(By.cssSelector("[tab='discover']"));
    }

    public boolean ifMapIconPresented() {
        return isElementPresent(By.cssSelector("[tab='map']"));
    }

    public void clickMapIcon() {
        click(By.cssSelector("[tab='map']"));
    }

    public String getLanguageValue() {
        return getText(By.cssSelector(".select.buttons-last-slot.sc-ion-buttons-md-h.sc-ion-buttons-md-s.md.hydrated"));
    }

    public String getCurrencyValue() {
        return getText(By.cssSelector(".select.item-interactive.item-select.item-has-value.item.md.item-lines-none.ion-activatable.ion-focusable.hydrated"));
    }

    public String getFromValue() {
        return getText(By.name("ion-input-0"));
    }

    public String getToValue() {
        return getText(By.name("ion-input-1"));
    }

///////////////////////////////
    public void selectAppLanguage(int lang) throws InterruptedException {

        click(By.cssSelector("ion-buttons.select.buttons-last-slot.sc-ion-buttons-md-h.sc-ion-buttons-md-s.md.hydrated"));
        delay(1000);
        getItemsList(By.cssSelector("ion-item.select-interface-option")).get(lang).click();
    }

    public boolean isLanguageOnPageCorrect(int lang) {

        if(lang == 1){
            return getText(By.xpath("//ion-card-title")).contains("Найдите самый дешевый способ добраться из города в город");
        }
        if (lang == 0){
            return getText(By.xpath("//ion-card-title")).contains("Discover the cheapest way to get anywhere combining plane");
        }
        return false;
    }

//////////////////////


}
