package com.SpreeCommerce.PageLocators;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class OrderConfirmationPage {
    private final Page page;
    public final Locator confirmationHeader;

    public OrderConfirmationPage(Page page) {
        this.page = page;
        confirmationHeader = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Your order is confirmed!"));
    }
}
