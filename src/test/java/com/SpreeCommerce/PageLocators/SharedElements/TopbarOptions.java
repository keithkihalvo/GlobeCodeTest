package com.SpreeCommerce.PageLocators.SharedElements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class TopbarOptions {
    private final Page page;
    public final Locator siteLogo;
    public final Locator searchButton;
    public final Locator closeSearchButton;
    public final Locator searchTextfield;
    public final Locator shopAllLink;
    public final Locator fashionLink;
    public final Locator wellnessLink;
    public final Locator newArrivalsLink;
    public final Locator saleLink;
    public final Locator welcomeHeader;
    public final Locator accountButton;
    public final Locator wishlistButton;
    public final Locator cartButton;
    public final Locator accountLink;

    public TopbarOptions(Page page) {
        this.page = page;

        welcomeHeader = page.getByText("Welcome to the Spree Commerce");
        siteLogo = page.getByLabel("Top").getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("Spree Commerce DEMO logo"));
        searchButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search"));
        searchTextfield = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search"));
        shopAllLink = page.getByLabel("Top").getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("Shop All"));
        fashionLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Fashion").setExact(true));
        wellnessLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Wellness"));
        newArrivalsLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("New Arrivals").setExact(true));
        saleLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Sale").setExact(true));
        accountButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Open account panel"));
        wishlistButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Open wishlist"));
        cartButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Items in cart, View bag"));
        closeSearchButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Close"));
        accountLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Open account panel"));
    }
}
