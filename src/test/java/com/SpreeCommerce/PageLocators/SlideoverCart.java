package com.SpreeCommerce.PageLocators;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class SlideoverCart {
    private final Page page;
    public final Locator closeButton;
    public final Locator checkoutButton;

    public SlideoverCart(Page page) {
        this.page = page;
        closeButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Close sidebar"));
        checkoutButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Checkout"));
    }
}
