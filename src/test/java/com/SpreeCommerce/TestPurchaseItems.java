package com.SpreeCommerce;

import com.SpreeCommerce.Methods.CustomFunctions;
import com.SpreeCommerce.Methods.NavigatesTo;
import com.SpreeCommerce.Methods.Shopping;
import com.SpreeCommerce.PageLocators.*;
import com.SpreeCommerce.PageLocators.SharedElements.SlideoverAccount;
import com.SpreeCommerce.PageLocators.SharedElements.TopbarOptions;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.SpreeCommerce.Methods.AccountDetails.*;


public class TestPurchaseItems {

    Playwright playwright;
    Browser browser;
    Page page;
    String spreeCommerceHomePageLink = "https://demo.spreecommerce.org/";
    HomePage homePage;
    TopbarOptions topbarOptions;
    ToastMessage toastMessage;
    CustomFunctions customFunctions;
    NavigatesTo navigatesTo;
    SlideoverAccount slideoverAccount;
    AccountPage accountPage;
    ProductsPage productsPage;
    Shopping shopping;
    CheckoutPage checkoutPage;
    OrderConfirmationPage orderConfirmationPage;

    @BeforeEach
    void setup(){
        playwright = Playwright.create();
        browser = playwright.chromium()
                .launch(new BrowserType
                        .LaunchOptions()
                        .setHeadless(false));
        page = browser.newPage();
        generateNewAccount();
        homePage = new HomePage(page);
        topbarOptions = new TopbarOptions(page);
        toastMessage = new ToastMessage(page);
        customFunctions = new CustomFunctions(page);
        navigatesTo = new NavigatesTo(page);
        slideoverAccount = new SlideoverAccount(page);
        accountPage = new AccountPage(page);
        productsPage = new ProductsPage(page);
        shopping = new Shopping(page);
        checkoutPage = new CheckoutPage(page);
        orderConfirmationPage = new OrderConfirmationPage(page);
    }

    @AfterEach
    void teardown(){
        browser.close();
        playwright.close();
    }

//    @RepeatedTest(10)
    @Test
    void purchaseItemsFromStore() throws InterruptedException {

        //Open the site and navigate to the Sign Up modal page
        page.navigate(spreeCommerceHomePageLink);
        topbarOptions.siteLogo.waitFor();
        navigatesTo.SlideoverAccountSignUp();

        //Creates a new user with the generated user account details
        slideoverAccount.emailTextfield.fill(getUsername());
        slideoverAccount.passwordTextfield.fill(getPassword());
        slideoverAccount.passwordConfirmationTextfield.fill(getPassword());
        customFunctions.keepClicking(slideoverAccount.signUpButton, toastMessage.newSignUp);

        //Logs out of the newly created user
        navigatesTo.AccountPage();
        customFunctions.keepClicking(accountPage.logOutButton, toastMessage.successfulSignOut);

        //Logs in with the newly created user
        navigatesTo.SlideoverAccountLogin();
        slideoverAccount.emailTextfield.fill(getUsername());
        slideoverAccount.passwordTextfield.fill(getPassword());
        customFunctions.keepClicking(slideoverAccount.loginButton, toastMessage.successfulSignIn);

        //Browses the products, adds all items from the OrderList.json to the cart and checks out
        navigatesTo.ProductsPage();
        shopping.AddOrderListToCart();

        //Adds the delivery details and payment methods
        shopping.FillsOutDeliveryDetails();
        shopping.FillOutPaymentMethod();

        //Pays the total and asserts the final price
        customFunctions.keepClicking(checkoutPage.payNowButton,orderConfirmationPage.confirmationHeader);
        shopping.AssertFinalSubtotal();

    }

}
