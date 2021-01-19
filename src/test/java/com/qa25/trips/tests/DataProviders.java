package com.qa25.trips.tests;

import com.qa25.trips.model.Cities;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {

    //Regular tests to verify the fields filling - small one
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

    //Test to verify routes appear
    @DataProvider
    public Iterator<Object[]> validRoutesAppearFromFile() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(
                new File("src/test/resources/DataProviderCitiesForRoutesValid.csv")));

        return readCityNamesFromFile(reader);
    }

    @DataProvider
    public Iterator<Object[]> invalidRoutesAppearFromFile() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(
                new File("src/test/resources/DataProviderCitiesForRoutesInvalid.csv")));

        return readCityNamesFromFile(reader);
    }

    //Regression tests to verify the fields filling - full one
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

            list.add(new Object[]{new Cities()
                    .setFromCity(line.split(";")[0])
                    .setToCity(line.split(";")[1])});

            line = reader.readLine();
        }

        return list.iterator();
    }

}
