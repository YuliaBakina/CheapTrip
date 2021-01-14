package com.qa25.trips.fw;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class PageBase {


    protected WebDriver driver;
//  Logger logger = Logger.getLogger(TestBase.class.getName());

    public PageBase(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }


    void waitUntilElementClickable(WebElement element, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void waitUntilElementVisible (WebElement element, int time){
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    void waitUntilElementInVisible (WebElement element, int time){
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    void waitUntilAllElementsVisible (List<WebElement> elements, int time){
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOfAllElements(elements));
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public void inputTextToField(WebElement element, String text) {
        try {
            element.click();
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectInDropDown(WebElement element, String value){
        try {
            Select select = new Select(element);
            select.selectByValue(value);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void checkIn (WebElement element){
        try {
            if ( !element.isSelected() ){
                element.click();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Double getMinFromList(List<Double> list) {
        Double min = list.get(0);
        for (Double el : list){
            if (el < min){
                min = el;
            }
        }
        return min;
    }

    public boolean isElementPresent(WebElement element){
        return element.isDisplayed();
    }

    public boolean isElementClicable(WebElement element){
        try {
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
