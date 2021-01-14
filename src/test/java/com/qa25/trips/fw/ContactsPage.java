package com.qa25.trips.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactsPage extends HelperBase{
    public ContactsPage(WebDriver wd) {
        super(wd);
    }

    public boolean ifContactsPageOpened() {
        return isElementPresent(By.xpath("//ion-title[contains(text(),'Contacts')]"));
    }

    public boolean ifContactsInfoPresented() {
        return isElementPresent(By.cssSelector(".padding.item.md.in-list.ion-focusable.hydrated.item-label"));
    }

}
