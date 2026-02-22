package com.SpreeCommerce.PageLocators.SharedElements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class SlideoverAccount {
    private final Page page;
    public final Locator closeAccountTabButton;
    public final Locator accountTabHeader;
    public final Locator signUpHeader;
    public final Locator forgotPasswordHeader;
    public final Locator loginHeader;
    public final Locator emailTextfield;
    public final Locator passwordTextfield;
    public final Locator passwordConfirmationTextfield;
    public final Locator signUpLink;
    public final Locator loginLink;
    public final Locator forgotPasswordLink;
    public final Locator rememberMeCheckbox;
    public final Locator loginButton;
    public final Locator signUpButton;
    public final Locator resetMyPasswordButton;

    public SlideoverAccount(Page page) {
        this.page = page;

        closeAccountTabButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Close account sidebar"));
        accountTabHeader = page.locator("#slideover-account").getByText("Account");
        signUpHeader = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Sign Up"));
        forgotPasswordHeader = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Forgot password?"));
        loginHeader = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Login"));
        emailTextfield = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email"));
        passwordTextfield = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password").setExact(true));
        passwordConfirmationTextfield = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password Confirmation"));
        signUpLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Sign Up"));
        loginLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Login"));
        forgotPasswordLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Forgot password?"));
        rememberMeCheckbox = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("Remember me"));
        loginButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"));
        signUpButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign Up"));
        resetMyPasswordButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Reset my password"));
    }
}
