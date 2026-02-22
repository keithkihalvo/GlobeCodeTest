package com.SpreeCommerce.PageLocators;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ToastMessage {
    private final Page page;
    public final Locator newSignUp;
    public final Locator successfulSignIn;
    public final Locator successfulSignOut;

    public ToastMessage(Page page){

        this.page = page;

        newSignUp = page.getByText("Welcome! You have signed up");
        successfulSignIn = page.getByText("Signed in successfully.");
        successfulSignOut = page.getByText("Signed out successfully.");
    }
}
