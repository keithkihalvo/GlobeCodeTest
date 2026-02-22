package com.SpreeCommerce.PageLocators;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class HomePage {
    private final Page page;

    public final Locator welcomeTitle;

    public HomePage(Page page) {
        this.page = page;

        welcomeTitle = page.getByText("Welcome to the Spree Commerce");
    }
}
