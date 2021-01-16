package com.qa25.trips.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MainPageTest extends TestBase{

    @BeforeTest
    public void insurePreconditions(){
        appManager.getMainPage().isMainPageOpened(appManager.getBaseURL());

       // appManager.getMainPage().opeMainPage(appManager.getBaseURL());
    }


    @Test(enabled = true, priority = 1, groups = {"UI"})
    public void sloganValidationTest() {

        boolean res = appManager.getMainPage().ifMainPageConsistLogo();
        Assert.assertTrue(res);

    }

    //UI MainPage tests
    @Test(enabled = true, priority = 0, groups = {"UI"})
    public void mainPageCheckElementsPresentTest() throws InterruptedException {
        appManager.getMainPage().delay(2000);

        //Hamburger menu
         Assert.assertTrue(appManager.getMainPage().ifHamburgerMenuPresented());

        //Currency drop-down
         Assert.assertTrue(appManager.getMainPage().ifCurrencyDrDownPresented());

        //Language drop-down
         Assert.assertTrue(appManager.getMainPage().ifLanguageDrDownPresented());

        //Search title
        Assert.assertTrue(appManager.getMainPage().ifSearchTitlePresented());

        //From block
        Assert.assertTrue(appManager.getMainPage().ifFromTitlePresented());

        //To block
        Assert.assertTrue(appManager.getMainPage().ifToTitlePresented());

        //Clear button
        Assert.assertTrue(appManager.getMainPage().ifClearButtonPresented());

        //Let's go button
        Assert.assertTrue(appManager.getMainPage().ifGoButtonPresented());

        //Map icon
        Assert.assertTrue(appManager.getMainPage().ifMapIconPresented());

        //Search icon
        Assert.assertTrue(appManager.getMainPage().ifSearchIconPresented());

    }

    @Test(enabled = true, priority = 2, groups = {"UI"})
    public void mainPageCheckElementsDefaultValuesSetTest() throws InterruptedException {
        appManager.getMainPage().delay(2000);

        //Currency drop-down
        String cur = appManager.getMainPage().getCurrencyValue();
        Assert.assertEquals(cur,"EUR");

        //Language drop-down
        String lng = appManager.getMainPage().getLanguageValue();
        Assert.assertEquals(lng,"Ru");
        //Assert.assertEquals(lng,"En");

        //From block
        String fromText = appManager.getMainPage().getFromValue();
        Assert.assertEquals(fromText,"");

        //To block
        String toText = appManager.getMainPage().getToValue();
        Assert.assertEquals(toText,"");

    }

    @Test (enabled = true, priority = 3, groups = {"functional"}) //priority - порядок выполнения тестов
    public void changeLanguageValidationTest() throws InterruptedException {
        //1=Ru, 0=En
        appManager.getMainPage().selectAppLanguage(1);
        appManager.getMainPage().delay(1000);
        Assert.assertTrue(appManager.getMainPage().isLanguageOnPageCorrect(1));

    }

}
