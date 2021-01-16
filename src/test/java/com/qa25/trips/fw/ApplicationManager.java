package com.qa25.trips.fw;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    EventFiringWebDriver wd;
    MainPage mainPage;
    HamburgerMenu hamburgerMenu;
    ContactsPage contactsPage;
    SearchPage searchPage;
    RoutesPage routesPage;


    String browser;

    String baseURL = "http://test70.lowcoststrip.com/";

    public static class MyListener extends AbstractWebDriverEventListener {

        Logger logger = LoggerFactory.getLogger(MyListener.class);

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            logger.error(throwable.toString());
        }
    }

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public String getBaseURL(){
        return baseURL;
    }

    public MainPage getMainPage() {
        return mainPage;
    }

    public HamburgerMenu getHamburgerMenu(){
        return hamburgerMenu;
    }

    public ContactsPage getContactsPage(){
        return contactsPage;
    }

    public SearchPage getSearchPage(){
        return searchPage;
    }

    public RoutesPage getRoutesPage(){return routesPage;}

    public void init() {
    //    wd = new EventFiringWebDriver(new ChromeDriver());

        if(browser.equals(BrowserType.CHROME)){
            wd = new EventFiringWebDriver(new ChromeDriver());
        }else if(browser.equals(BrowserType.FIREFOX)){
            wd = new EventFiringWebDriver(new FirefoxDriver());
        }

        wd.register(new MyListener());

        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wd.manage().window().maximize();
//        wd.manage().deleteAllCookies();

        wd.get(baseURL);

       mainPage = new MainPage(wd);
       hamburgerMenu = new HamburgerMenu(wd);
       contactsPage = new ContactsPage(wd);
       searchPage = new SearchPage(wd);
       routesPage = new RoutesPage(wd);

    }

    public void stop() {
        wd.quit();
    }

}

