package com.qa25.trips.tests;

import com.qa25.trips.model.Cities;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchEngineTests extends TestBase{

    @BeforeMethod
    public void insurePreconditions() throws InterruptedException {

        if(!appManager.getMainPage().getLanguageValue().equals("En")) {
            appManager.getMainPage().selectAppLanguage(0);
        }

        if(!appManager.getMainPage().getCurrencyValue().equals("EUR")) {
            appManager.getMainPage().selectAppCurrency();
        }

    }

///FROM FIELD
    //Regular tests
    @Test(dataProvider = "validCityNamesFromFile",dataProviderClass = DataProviders.class,
          enabled = true, priority = 0, groups = {"functional"})
    public void fillFromFieldPositiveTest(Cities cityName) throws InterruptedException {
        //fill the From field
        appManager.getSearchPage().fillFromField(cityName,"pos");

        //click X icon
        appManager.getSearchPage().eraseFromField();

        //check the From field is empty
        Assert.assertEquals(appManager.getMainPage().getFromValue(),"","The FROM field is not empty");

    }

    @Test(dataProvider = "invalidCityNamesFromFile",dataProviderClass = DataProviders.class,
            enabled = true, priority = 1, groups = {"functional"})
    public void FillFromFieldNegativeTest(Cities  cityName) throws InterruptedException {
        //fill the From field
        appManager.getSearchPage().fillFromField(cityName, "neg");

        //check the From field is empty
        Assert.assertEquals(appManager.getMainPage().getFromValue(),"","The FROM field is not empty");

    }

///TO FIELD
    //Regular tests
    @Test(dataProvider = "validCityNamesFromFile",dataProviderClass = DataProviders.class,
            enabled = true, priority = 0, groups = {"functional"})
    public void fillToFieldPositiveTest(Cities cityName) throws InterruptedException {
        //fill the To field
        appManager.getSearchPage().fillToField(cityName, "pos");

        //click X icon
        appManager.getSearchPage().eraseToField();

        //check the To field is empty
        Assert.assertEquals(appManager.getMainPage().getToValue(),"","The TO field is not empty");

    }

    @Test(dataProvider = "invalidCityNamesFromFile",dataProviderClass = DataProviders.class,
            enabled = true, priority = 1, groups = {"functional"})
    public void FillToFieldNegativeTest(Cities  cityName) throws InterruptedException {
        //fill the To field
        appManager.getSearchPage().fillToField(cityName, "neg");

        //check the To field is empty
        Assert.assertEquals(appManager.getMainPage().getToValue(),"", "The TO field is not empty");

    }

///////Buttons
    //CLEAR button
    @Test(dataProvider = "validCityNamesFromFile",dataProviderClass = DataProviders.class,
            enabled = true, priority = 2, groups = {"functional"})
    public void clearButtonPositiveTest(Cities cities) throws InterruptedException {
        //Fill the FROM and TO fields with valid data
        appManager.getSearchPage().fillFromField(cities,"pos");
        appManager.getSearchPage().fillToField(cities,"pos");

        //Clear the form with CLEAR button
        appManager.getSearchPage().clickClearButton();

        //Verify if the FROM and TO fields are empty
        Boolean fromValue = true, toValue = true;
        String erM1 = "", erM2 = "";
        if(!appManager.getMainPage().getFromValue().equals("")){
            fromValue = false;
            erM1 = "The FROM field is not empty.";
        }
        if(!appManager.getMainPage().getToValue().equals("")){
            toValue = false;
            erM2 = "The TO field is not empty.";
        }

        Assert.assertTrue(fromValue && toValue, erM1 + " " + erM2);

    }

    //LET'S GO button
    @Test(dataProvider = "validRoutesAppearFromFile",dataProviderClass = DataProviders.class,
            enabled = true, priority = 2, groups = {"functional"})
    public void goButtonPositiveTest(Cities cities) throws InterruptedException {
        //Fill the FROM and TO fields with valid data
        appManager.getSearchPage().fillFromField(cities,"pos");
        appManager.getSearchPage().fillToField(cities,"pos");

        //Complete the search with Lets's go button
        appManager.getSearchPage().clickGoButton();

        //Verify if any routes are presented
        Assert.assertTrue(appManager.getMainPage().searchResulIsDisplayed(),"Search result is not displayed!");

        //Go back to the Search page
        appManager.getRoutesPage().clickBackButton();

        //Verify the Search page is opened
        appManager.getMainPage().ifSearchTitlePresented();

        //Clear the form with CLEAR button
        appManager.getSearchPage().clickClearButton();
    }

    @Test(dataProvider = "invalidRoutesAppearFromFile",dataProviderClass = DataProviders.class,
            enabled = true, priority = 2, groups = {"functional"})
    public void goButtonNegativeTest(Cities cities) throws InterruptedException {
        //Fill the FROM and TO fields with valid data
        appManager.getSearchPage().fillFromField(cities,"pos");
        appManager.getSearchPage().fillToField(cities,"pos");

        //Complete the search with Lets's go button
        appManager.getSearchPage().clickGoButton();

        //Verify if error message appears
        Assert.assertTrue(appManager.getSearchPage().ifErrorMessagePresented(), appManager.getSearchPage().getErrorMessageText());
        logger.info("--> For this route error message is: " + appManager.getSearchPage().getErrorMessageText());

        //Close Error message
        appManager.getSearchPage().closeErrorMessage();

        //Verify the Search page is opened
        appManager.getMainPage().ifSearchTitlePresented();

    }

//////REGRESSIONS TESTS for FROM and TO fields on all of the cities
    @Test(dataProvider = "validCityNamesFromFileRegression",dataProviderClass = DataProviders.class,
            enabled = false, priority = 3, groups = {"functional"})
    public void fillFieldsPositiveRegressionTest(Cities  cityName) throws InterruptedException {
        //fill the From field
        appManager.getSearchPage().fillFromField(cityName,"pos");

        //click X icon
        appManager.getSearchPage().eraseFromField();

        //fill the To field
        appManager.getSearchPage().fillToField(cityName,"pos");

        //click X icon
        appManager.getSearchPage().eraseToField();

    }


}
