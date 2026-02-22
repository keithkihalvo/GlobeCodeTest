package com.SpreeCommerce.PageLocators;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class CheckoutPage {
    private final Page page;
    public final Locator checkoutHeader;
    public final Locator firstNameTextfield;
    public final Locator lastNameTextfield;
    public final Locator countryDropdown;
    public final Locator addressTextfield;
    public final Locator cityTextField;
    public final Locator zipCodeTextField;
    public final Locator saveAndContinueButton;
    public final Locator freeDeliveryOption;
    public final Locator standardDeliveryOption;
    public final Locator premiumDeliveryOption;
    public final Locator nextDayDeliveryOption;
    public final Locator paymentHeader;
    public final Locator cardNumberTextfield;
    public final Locator expirationDateTextfield;
    public final Locator securityCodeTextfield;
    public final Locator payNowButton;
    public final Locator finalSubtotalText;

    public CheckoutPage(Page page) {
        this.page = page;
        checkoutHeader = page.getByText("Cart Address Delivery Payment");
        firstNameTextfield = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("First name"));
        lastNameTextfield = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Last name"));
        addressTextfield = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Street and house number"));
        cityTextField = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("City"));
        zipCodeTextField = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Postal Code"));
        countryDropdown = page.getByLabel("Country", new Page.GetByLabelOptions().setExact(true));
        saveAndContinueButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save and Continue"));
        freeDeliveryOption = page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("test Shipping Delivery in 5-7"));
        standardDeliveryOption = page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("Standard Delivery in 3-5"));
        nextDayDeliveryOption = page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("Next Day Delivery in 1-2"));
        premiumDeliveryOption = page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("Premium Delivery in 2-3"));
        paymentHeader = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Payment"));
        cardNumberTextfield = page.locator("//iframe[contains(@name, \"__privateStripeFrame\")]").first().contentFrame().getByRole(AriaRole.TEXTBOX, new FrameLocator.GetByRoleOptions().setName("Card number"));
        expirationDateTextfield = page.locator("//iframe[contains(@name, \"__privateStripeFrame\")]").first().contentFrame().getByRole(AriaRole.TEXTBOX, new FrameLocator.GetByRoleOptions().setName("Expiration date MM / YY"));
        securityCodeTextfield = page.locator("//iframe[contains(@name, \"__privateStripeFrame\")]").first().contentFrame().getByRole(AriaRole.TEXTBOX, new FrameLocator.GetByRoleOptions().setName("Security code"));
        payNowButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Pay now"));
        finalSubtotalText = page.locator("//span[contains(text(),'Subtotal')]//following-sibling::span");

    }
}
