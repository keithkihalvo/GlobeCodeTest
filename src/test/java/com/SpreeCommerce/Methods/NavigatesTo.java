package com.SpreeCommerce.Methods;

import com.SpreeCommerce.PageLocators.AccountPage;
import com.SpreeCommerce.PageLocators.HomePage;
import com.SpreeCommerce.PageLocators.ProductsPage;
import com.SpreeCommerce.PageLocators.SharedElements.SlideoverAccount;
import com.SpreeCommerce.PageLocators.SharedElements.TopbarOptions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class NavigatesTo {

    private final Page page;
    HomePage homePage;
    TopbarOptions topbarOptions;
    SlideoverAccount slideoverAccount;
    AccountPage accountPage;
    CustomFunctions customFunctions;
    ProductsPage productsPage;

    public NavigatesTo(Page page) {
        this.page = page;
        homePage = new HomePage(page);
        topbarOptions = new TopbarOptions(page);
        customFunctions = new CustomFunctions(page);
        slideoverAccount = new SlideoverAccount(page);
        accountPage = new AccountPage(page);
        productsPage = new ProductsPage(page);
    }

    public void closeOverlay(){
        try{
            slideoverAccount.closeAccountTabButton.click(new Locator.ClickOptions().setTimeout(1000));
//            customFunctions.keepClicking(slideoverAccount.closeAccountTabButton, topbarOptions.siteLogo);
        }catch(Exception e){

        }
    }


    public void HomePage(){
        closeOverlay();
        customFunctions.keepClicking(topbarOptions.siteLogo, homePage.welcomeTitle);
    }

    public void SlideoverAccountSignUp(){
        closeOverlay();
        customFunctions.keepClicking(topbarOptions.accountButton, slideoverAccount.accountTabHeader);
        if(customFunctions.IsElementPresent(slideoverAccount.signUpLink)){
            customFunctions.keepClicking(slideoverAccount.signUpLink, slideoverAccount.signUpHeader);
        }
    }

    public void SlideoverAccountLogin(){
        closeOverlay();
        customFunctions.keepClicking(topbarOptions.accountButton, slideoverAccount.accountTabHeader);
        if(customFunctions.IsElementPresent(slideoverAccount.loginLink)){
            customFunctions.keepClicking(slideoverAccount.loginLink, slideoverAccount.loginHeader);
        }
    }

    public void SlideoverForgotPassword(){
        closeOverlay();
        customFunctions.keepClicking(topbarOptions.accountButton, slideoverAccount.accountTabHeader);
        if(customFunctions.IsElementPresent(slideoverAccount.signUpHeader)){
            customFunctions.keepClicking(slideoverAccount.loginLink, slideoverAccount.loginHeader);
        }
        if(customFunctions.IsElementPresent(slideoverAccount.forgotPasswordLink)){
            customFunctions.keepClicking(slideoverAccount.forgotPasswordLink, slideoverAccount.forgotPasswordHeader);
        }
    }

    public void AccountPage(){
        closeOverlay();
        customFunctions.keepClicking(topbarOptions.accountLink, accountPage.myAccountHeader);
    }

    public void ProductsPage() throws InterruptedException {
        closeOverlay();
        customFunctions.keepClicking(topbarOptions.shopAllLink, productsPage.shopAllHeader);
        Thread.sleep(2000);
    }



}
