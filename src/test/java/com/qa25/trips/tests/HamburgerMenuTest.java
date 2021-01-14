package com.qa25.trips.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HamburgerMenuTest extends TestBase{

    @BeforeMethod
    public void insurePreconditions() throws InterruptedException {
        //appManager.getMainPage().isMainPageOpened(appManager.getBaseURL());
        appManager.getMainPage().opeMainPage(appManager.getBaseURL());
        appManager.getMainPage().selectAppLanguage(0);

    }

    @Test(enabled = true, priority = 0, groups = {"UI"})
    public void hamburgerMenuItemsTest() throws InterruptedException {
        //Home item: click and check that Home page is opened
        appManager.getMainPage().clickHamburgerMenu();
        appManager.getMainPage().delay(1000);
        appManager.getHamburgerMenu().clickHomeItem();
        appManager.getMainPage().delay(1000);
        Assert.assertTrue(appManager.getMainPage().ifMainPageConsistLogo());

        ///Contacts item: click and check that Contacts page is opened
        appManager.getMainPage().clickHamburgerMenu();
        appManager.getMainPage().delay(1000);
        appManager.getHamburgerMenu().clickContactsItem();
        appManager.getMainPage().delay(1000);
        Assert.assertTrue(appManager.getContactsPage().ifContactsPageOpened());
        Assert.assertTrue(appManager.getContactsPage().ifContactsInfoPresented());

    }
}
