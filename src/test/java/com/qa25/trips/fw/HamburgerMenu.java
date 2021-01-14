package com.qa25.trips.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HamburgerMenu extends HelperBase{

    public HamburgerMenu(WebDriver wd) {
        super(wd);
    }

    public void clickHomeItem(){
        click(By.cssSelector("[routerlink='/']"));
    }

    public void clickContactsItem(){
        click(By.cssSelector("[routerlink='/contact']"));
    }

}
