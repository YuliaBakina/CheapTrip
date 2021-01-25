package com.qa25.trips.fw;

import com.qa25.trips.model.Cities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage extends HelperBase{
    public SearchPage(WebDriver wd) {
        super(wd);
    }

    public void fillFromField(Cities cities, String testType) throws InterruptedException {
       // testType == pos || testType == neg

       type(By.name("ion-input-0"), cities.getFromCity());

        //verify if there is an error message for positive test
        if(testType.equals("pos")) {

            if (!ifErrorMessagePresented()) {
                getItemsList(By.xpath("//ion-item//ion-label[@id='ion-input-0-lbl']//..//..//ion-item//ion-list")).get(0).click();
            } else {
                logger.info("   --> Error message: " + getErrorMessageText());
                logger.info("   --> Test is fail, but should be pass");

                //close error message
                closeErrorMessage();

                //
                getItemsList(By.xpath("//ion-item//ion-label[@id='ion-input-0-lbl']//..//..//ion-item//ion-list")).get(0).click();
            }
        }

        //verify if there is an error message for negative test
        if(testType.equals("neg")){
            if(ifErrorMessagePresented()){
                logger.info("   --> Error message: " + getErrorMessageText());

                //close error message
                closeErrorMessage();
            }
        }

    }

    public void fillToField(Cities cities, String testType) throws InterruptedException {
        // testType == pos || testType == neg
        type(By.name("ion-input-1"), cities.getToCity());

        //verify if there is an error message for positive test
        if(testType.equals("pos")) {

            if (!ifErrorMessagePresented()) {
                getItemsList(By.xpath("//ion-item//ion-label[@id='ion-input-1-lbl']//..//..//ion-item//ion-list")).get(0).click();
            } else {
                logger.info("   --> Error message: " + getErrorMessageText());
                logger.info("   --> Test is fail, but should be pass");

                //close error message
                closeErrorMessage();

                //
                getItemsList(By.xpath("//ion-item//ion-label[@id='ion-input-1-lbl']//..//..//ion-item//ion-list")).get(0).click();
            }
        }

        //verify if there is an error message for negative test
        if(testType.equals("neg")){
            if(ifErrorMessagePresented()){
                logger.info("   --> Error message: " + getErrorMessageText());

                //close error message
                closeErrorMessage();
            }
        }
    }


    public void eraseToField(){
        click(By.xpath("//ion-input//input [@name='ion-input-1']//..//button[@aria-label='reset']"));
    }

    public void eraseFromField(){
          click(By.xpath("//ion-input//input [@name='ion-input-0']//..//button[@aria-label='reset']"));
      }

    public boolean ifErrorMessagePresented() {
        return isElementPresent(By.cssSelector(".alert-wrapper.ion-overlay-wrapper.sc-ion-alert-md"));
    }

    public void closeErrorMessage() {
        click(By.xpath("//button[@tabindex='0']"));
    }

    public String getErrorMessageText() {
        return getText(By.cssSelector(".alert-message.sc-ion-alert-md"));
      //  return getText(By.cssSelector("[role='dialog']"));

    }

    public void clickClearButton() {
        click(By.xpath("//ion-button[1]"));
    }

    public void clickGoButton() {
   //     System.out.println("clickGoButton");
        click(By.xpath("//ion-button[2]"));
    }

}
