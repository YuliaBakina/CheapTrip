package com.qa25.trips.fw;

import com.google.common.io.Files;
import com.qa25.trips.tests.TestBase;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class HelperBase {
    WebDriver wd;
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    public HelperBase(WebDriver wd){
        this.wd = wd;
    }

    public boolean isElementPresent(By locator) {
        return wd.findElements(locator).size()>0;
    }

    public String getText(By locator) {
        return wd.findElement(locator).getText();
    }

    public List<WebElement> getItemsList(By locator){
        return wd.findElements(locator);
    }

    public void type(By locator, String text) throws InterruptedException {
        WebElement field = wd.findElement(locator);
        field.click();
        field.clear();

        if(text.trim() != null){
            field.sendKeys(text);
        }

    }

    public void click(By locator){
        waitUntilElementIsClicable(locator).click();
    //    wd.findElement(locator).click();
    }

    public WebElement waitUntilElementIsClicable(By locator){
        WebDriverWait wait = new WebDriverWait(wd, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        return element;
    }

    public void delay(int timeout) throws InterruptedException {
        Thread.sleep(timeout);

    }

    public String takeScreenShot()  {
        File tmp = ((TakesScreenshot)wd).getScreenshotAs(OutputType.FILE);
        File screeenshot = new File("screenshot_" + System.currentTimeMillis() + ".png");

        try {
            Files.copy(tmp,screeenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screeenshot.getAbsolutePath();
    }

    public void scrollPageDown(){
        Actions actions = new Actions(wd);
        actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
    }

}
