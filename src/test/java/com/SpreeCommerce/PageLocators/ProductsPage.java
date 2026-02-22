package com.SpreeCommerce.PageLocators;

import com.SpreeCommerce.Methods.CustomFunctions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class ProductsPage {
    private final Page page;

    public final Locator shopAllHeader;
    public final Locator filterButton;
    CustomFunctions customFunctions;

    public ProductsPage(Page page) {
        this.page = page;

        shopAllHeader = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Shop All"));
        filterButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Filter"));
    }

    public Locator ItemName(String name){
        return page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(name));
    }
}
