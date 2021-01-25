package com.qa25.trips.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HamburgerMenuTest extends TestBase{

    @BeforeMethod
    public void insurePreconditions() throws InterruptedException {
        if(!appManager.getMainPage().getLanguageValue().equals("En")) {
            appManager.getMainPage().selectAppLanguage(0);
        }

        if(!appManager.getMainPage().getCurrencyValue().equals("EUR")) {
            appManager.getMainPage().selectAppCurrency();
        }
    }

    @Test(enabled = true, priority = 0, groups = {"UI"})
    public void hamburgerMenuHomeItemTest() throws InterruptedException {

        //Home item: click and check that Home page is opened
        appManager.getMainPage().clickHamburgerMenu();
  //      appManager.getMainPage().delay(1000);
        appManager.getHamburgerMenu().clickHomeItem();
  //      appManager.getMainPage().delay(1000);
        Assert.assertTrue(appManager.getMainPage().ifMainPageConsistLogo(),"The Home page is not opened.");

    }

    @Test(enabled = true, priority = 0, groups = {"UI"})
    public void hamburgerContactsItemTest() throws InterruptedException {

        ///Contacts item: click and check that Contacts page is opened
        appManager.getMainPage().clickHamburgerMenu();
  //      appManager.getMainPage().delay(1000);
        appManager.getHamburgerMenu().clickContactsItem();
   //     appManager.getMainPage().delay(1000);

        String erM1 = "", erM2 = "";
        boolean contactPageOpened = appManager.getContactsPage().ifContactsPageOpened();
        if(!contactPageOpened){
            erM1 = "The Contacts page is not opened. ";
        }
        boolean contactInfoPresent = appManager.getContactsPage().ifContactsInfoPresented();
        if(!contactInfoPresent){
            erM2 = "The contacts info is not presented. ";
        }

        if(!erM1.equals("") || !erM2.equals("")){
            logger.info("-->" + erM1 + erM2);
        }
        Assert.assertTrue(contactInfoPresent && contactPageOpened, erM1 + erM2);

    }
}
