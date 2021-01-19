package com.qa25.trips.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SearchTests extends TestBase{

    String cityFrom = "Kiev";
    String cityTo = "Moscow";

    @BeforeClass
    public void insurePreconditions() throws InterruptedException {
        appManager.getMainPage().opeMainPage(appManager.getBaseURL());
        if(!appManager.getMainPage().getLanguageValue().equals("En")) {
            appManager.getMainPage().selectAppLanguage(0);
        }

    }

    @Test(dataProvider = "validCityNamesFromFile",dataProviderClass = DataProviders.class,
            enabled = true)
    public void searchResultIsPresent(){
        appManager.getMainPage().inputCityInFromField(cityFrom);
        appManager.getMainPage().inputCityInToField(cityTo);
        appManager.getMainPage().clickGoButton();
        Assert.assertTrue(appManager.getMainPage().searchResulIsDisplayed(),"Search result is not displayed!");
    }
}
