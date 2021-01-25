package com.qa25.trips.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MainPageTest extends TestBase{

    @BeforeMethod
    public void insurePreconditions() {
        appManager.getMainPage().isMainPageOpened(appManager.getBaseURL());

    }


    @Test(enabled = true, priority = 1, groups = {"UI"})
    public void sloganValidationTest() {

        boolean res = appManager.getMainPage().ifMainPageConsistLogo();
        Assert.assertTrue(res,"Site logo is not presented.");

    }

    //UI MainPage tests
    @Test(enabled = true, priority = 0, groups = {"UI"})
    public void mainPageCheckHamburgerMenuPresentTest() {
        Boolean res = appManager.getMainPage().ifHamburgerMenuPresented();
        if(!res){
            logger.info("--> The Hamburger Menu is not presented.");
        }
        Assert.assertTrue(res, "The Hamburger Menu is not presented.");
    }

    @Test(enabled = true, priority = 0, groups = {"UI"})
    public void mainPageCheckCurrencyPresentTest()  {
        Boolean res = appManager.getMainPage().ifCurrencyDrDownPresented();
        if(!res){
            logger.info("--> The Currency drop-down is not presented.");
        }
        Assert.assertTrue(res, "The Currency drop-down is not presented.");
    }

    @Test(enabled = true, priority = 0, groups = {"UI"})
    public void mainPageCheckLanguagePresentTest(){
        Boolean res = appManager.getMainPage().ifLanguageDrDownPresented();
        if(!res){
            logger.info("--> The Language drop-down is not presented.");
        }
        Assert.assertTrue(res, "The Language drop-down is not presented.");
    }

    @Test(enabled = true, priority = 0, groups = {"UI"})
    public void mainPageCheckSearchTitlePresentTest() {
       Boolean res = appManager.getMainPage().ifSearchTitlePresented();
        if(!res){
            logger.info("--> The Search title is not presented.");
        }
        Assert.assertTrue(res, "The Search title is not presented.");
    }

    @Test(enabled = true, priority = 0, groups = {"UI"})
    public void mainPageCheckFromBlockPresentTest() {
       Boolean res = appManager.getMainPage().ifFromTitlePresented();
        if(!res){
            logger.info("--> The FROM field is not presented.");
        }
        Assert.assertTrue(res, "The FROM field is not presented.");
    }

    @Test(enabled = true, priority = 0, groups = {"UI"})
    public void mainPageCheckToBlockPresentTest()  {
        Boolean res = appManager.getMainPage().ifToTitlePresented();
        if(!res){
            logger.info("--> The TO field is not presented.");
        }
        Assert.assertTrue(res, "The TO field is not presented.");
    }

    @Test(enabled = true, priority = 0, groups = {"UI"})
    public void mainPageCheckClearButtonPresentTest() {
        Boolean res = appManager.getMainPage().ifClearButtonPresented();
        if(!res){
            logger.info("--> The CLEAR button is not presented.");
        }
        Assert.assertTrue(res, "The CLEAR button is not presented.");
    }

    @Test(enabled = true, priority = 0, groups = {"UI"})
    public void mainPageCheckGoButtonPresentTest()  {
        Boolean res = appManager.getMainPage().ifGoButtonPresented();
        if(!res){
            logger.info("--> The LET'S GO button is not presented.");
        }
        Assert.assertTrue(res, "The LET'S GO button is not presented.");
    }

    @Test(enabled = true, priority = 0, groups = {"UI"})
    public void mainPageCheckMapIconPresentTest()  {
        Boolean res = appManager.getMainPage().ifMapIconPresented();
        if(!res){
            logger.info("--> The MAP icon is not presented.");
        }
        Assert.assertTrue(res, "The MAP icon is not presented.");
    }

    @Test(enabled = true, priority = 0, groups = {"UI"})
    public void mainPageCheckSearchIconPresentTest()  {
        Boolean res = appManager.getMainPage().ifSearchIconPresented();
        if(!res){
            logger.info("--> The SEARCH icon is not presented.");
        }
        Assert.assertTrue(res, "The SEARCH icon is not presented.");
    }


    @Test(enabled = true, priority = 2, groups = {"UI"})
    public void mainPageCheckElementsDefaultValuesSetTest() {
        //можно через массивы или объект, но пока сделала просто
        Boolean ifCur = true, ifLng = true, ifFrom = true, ifTo = true;
        String erM1 = "", erM2 = "", erM3 = "", erM4 = "";

        //Currency drop-down
        String cur = appManager.getMainPage().getCurrencyValue();
        if(!cur.equals("EUR")){
            ifCur = false;
            erM1 = "Default value for currency = " + cur + ".";
        }

        //Language drop-down
        String lng = appManager.getMainPage().getLanguageValue();
        if(!lng.equals("Ru")){
            ifLng = false;
            erM2 = "Default value for language = " + lng + ".";
        }

        //From block
        String fromText = appManager.getMainPage().getFromValue();
        if(!fromText.equals("")){
            ifFrom = false;
            erM3 = "Default value for FROM field = " + fromText + ".";
        }

        //To block
        String toText = appManager.getMainPage().getToValue();
        if(!toText.equals("")){
            ifTo = false;
            erM4 = "Default value for TO field = " + toText + ".";
        }

        if(!erM1.equals("") || !erM2.equals("") || !erM3.equals("") || !erM4.equals("")){
            logger.info("-->" + erM1 + erM2 + erM3 + erM4);
        }
        Assert.assertTrue((ifCur && ifLng && ifFrom && ifTo),
                erM1 + erM2 + erM3 + erM4);
    }

    @Test (enabled = true, priority = 3, groups = {"functional"}) //priority - порядок выполнения тестов
    public void changeLanguageValidationTest() throws InterruptedException {
        //1=Ru, 0=En
        appManager.getMainPage().selectAppLanguage(1);
        appManager.getMainPage().delay(1000);
        Assert.assertTrue(appManager.getMainPage().isLanguageOnPageCorrect(1),"The page language is not correct.");

    }

}
