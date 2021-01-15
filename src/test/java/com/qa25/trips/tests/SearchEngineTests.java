package com.qa25.trips.tests;

import com.qa25.trips.model.CityName;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SearchEngineTests extends TestBase{

    @BeforeClass
    public void insurePreconditions() throws InterruptedException {
        appManager.getMainPage().opeMainPage(appManager.getBaseURL());
        appManager.getMainPage().selectAppLanguage(0);

    }

    ///FROM FIELD
    //Regular tests
    @Test(dataProvider = "validCityNamesFromFile",dataProviderClass = DataProviders.class,
          enabled = true, priority = 0, groups = {"functional"})
    public void fillFromFieldPositiveTest(CityName cityName)  {
        //fill the From field
        appManager.getSearchPage().fillFromField(cityName,"pos");

        //click X icon
        appManager.getSearchPage().eraseFromField();

        //check the From field is empty
       // Assert.assertEquals(appManager.getMainPage().getFromValue(),"");

    }

    @Test(dataProvider = "invalidCityNamesFromFile",dataProviderClass = DataProviders.class,
            enabled = true, priority = 1, groups = {"functional"})
    public void FillFromFieldNegativeTest(CityName cityName)  {
        //fill the From field
        appManager.getSearchPage().fillFromField(cityName, "neg");

        //check the From field is empty
        Assert.assertEquals(appManager.getMainPage().getFromValue(),"");

    }

    ///TO FIELD
    //Regular tests
    @Test(dataProvider = "validCityNamesFromFile",dataProviderClass = DataProviders.class,
            enabled = true, priority = 0, groups = {"functional"})
    public void fillToFieldPositiveTest(CityName cityName)  {
        //fill the To field
       // appManager.getSearchPage().fillField(cityName,"pos","to");
        appManager.getSearchPage().fillToField(cityName, "pos");

        //click X icon
        appManager.getSearchPage().eraseToField();

        //check the To field is empty
       // Assert.assertEquals(appManager.getMainPage().getToValue(),"");

    }

    @Test(dataProvider = "invalidCityNamesFromFile",dataProviderClass = DataProviders.class,
            enabled = true, priority = 1, groups = {"functional"})
    public void FillToFieldNegativeTest(CityName cityName)  {
        //fill the To field
        appManager.getSearchPage().fillToField(cityName, "neg");

        //check the To field is empty
        Assert.assertEquals(appManager.getMainPage().getToValue(),"");

    }

    //////REGRESSIONS TESTS
    //FROM field - Regression test
    @Test(dataProvider = "validCityNamesFromFileRegression",dataProviderClass = DataProviders.class,
            enabled = false, priority = 2, groups = {"functional"})
    public void fillFromFieldPositiveRegressionTest(CityName cityName)  {
        //fill the From field
        appManager.getSearchPage().fillFromField(cityName,"pos");

        //click X icon
        appManager.getSearchPage().eraseFromField();

    }

    //To field - Regression test
    @Test(dataProvider = "validCityNamesFromFileRegression",dataProviderClass = DataProviders.class,
            enabled = false, priority = 2, groups = {"functional"})
    public void fillToFieldPositiveRegressionTest(CityName cityName)  {
        //fill the To field
        appManager.getSearchPage().fillToField(cityName,"pos");

        //click X icon
        appManager.getSearchPage().eraseToField();

    }

}
