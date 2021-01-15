package com.qa25.trips.tests;

import com.qa25.trips.model.CityName;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {

    //Regular tests - small one
    @DataProvider
    public Iterator<Object[]> validCityNamesFromFile() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(
                new File("src/test/resources/DataProviderCitiesValid.csv")));

        return readCityNamesFromFile(reader);
    }

    @DataProvider
    public Iterator<Object[]> invalidCityNamesFromFile() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(
                new File("src/test/resources/DataProviderCitiesInvalid.csv")));

        return readCityNamesFromFile(reader);
    }

    //Regression tests - full one
    @DataProvider
    public Iterator<Object[]> validCityNamesFromFileRegression() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(
                new File("src/test/resources/DataProviderCitiesValidRegression.csv")));

        return readCityNamesFromFile(reader);
    }

    private Iterator<Object[]> readCityNamesFromFile(BufferedReader reader) throws IOException {
        List<Object[]> list = new ArrayList<>();
        String line = reader.readLine();

        while (line != null) {

            list.add(new Object[]{new CityName().setCityName(line.trim())});
            line = reader.readLine();
        }

        return list.iterator();
    }

}
