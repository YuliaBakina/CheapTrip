package com.qa25.trips.tests;

import com.qa25.trips.model.Cities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RoutesPageTests extends TestBase {

    @BeforeMethod
    public void insurePreconditions() throws InterruptedException {
        if(!appManager.getMainPage().getLanguageValue().equals("En")) {
            appManager.getMainPage().selectAppLanguage(0);
        }

        if(!appManager.getMainPage().getCurrencyValue().equals("EUR")) {
            appManager.getMainPage().selectAppCurrency();
        }
    }

    @Test(dataProvider = "validRoutesAppearFromFile",dataProviderClass = DataProviders.class,
            enabled = true, priority = 0, groups = {"functional"})
    public void totalsValidationTest(Cities cities) throws InterruptedException {

        //Fill the FROM and TO fields with valid data
        appManager.getSearchPage().fillFromField(cities,"pos");
        appManager.getSearchPage().fillToField(cities,"pos");

        //Complete the search with Lets's go button
        appManager.getSearchPage().clickGoButton();

        //Verify if any routes are presented
        appManager.getRoutesPage().readRoutesData();

        //Go back to the Search page
        appManager.getRoutesPage().clickBackButton();

        //Verify the Search page is opened
        appManager.getMainPage().ifSearchTitlePresented();

        //Clear the form with CLEAR button
        appManager.getSearchPage().clickClearButton();
    }


}
