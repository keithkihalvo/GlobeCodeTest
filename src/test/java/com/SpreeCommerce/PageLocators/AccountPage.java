package com.SpreeCommerce.PageLocators;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class AccountPage {
    private final Page page;
    public final Locator ordersTab;
    public final Locator logOutButton;
    public final Locator myAccountHeader;

    public AccountPage(Page page) {
        this.page = page;
        ordersTab = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Orders & Returns"));
        logOutButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Log out"));
        myAccountHeader = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("My Account"));
    }
}
