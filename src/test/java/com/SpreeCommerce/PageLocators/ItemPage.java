package com.SpreeCommerce.PageLocators;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class ItemPage {
    private final Page page;
    public final Locator increaseQuantity;
    public final Locator decreaseQuantity;
    public final Locator addToCartButton;
    public final Locator quantityTextfield;
    public final Locator chooseSizeButton;
    public final Locator saleIndicator;
    public final Locator itemRegularPrice;
    public final Locator itemSalePrice;

    public ItemPage(Page page) {
        this.page = page;
        increaseQuantity = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Increase quantity"));
        decreaseQuantity = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Decrease quantity"));
        addToCartButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add To Cart"));
        quantityTextfield = page.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("Quantity"));
        saleIndicator = page.locator("//div[@id='product-details-page']//p[contains(@class,'inline text-danger')]").first();
        itemRegularPrice = page.locator("//span[contains(text(),'Regular price')]//following-sibling::p").first();
        itemSalePrice = page.locator("//p[contains(@class,'inline text-danger')]");
        chooseSizeButton = page.getByLabel("Please choose Size");
    }

    public Locator colorOption(String selectedColor){
        return page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(selectedColor));
    }

    public Locator sizeOption(String selectedSize){
        return page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName(selectedSize));
    }

    public Locator selectedSize(String selectedSize){
        return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Size:"+selectedSize));
    }

    public Locator itemHeader(String itemName){
        return page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName(itemName));
    }

    public Locator colorHeader(String itemName){
        return page.getByText("Color: " + itemName);
    }

}
