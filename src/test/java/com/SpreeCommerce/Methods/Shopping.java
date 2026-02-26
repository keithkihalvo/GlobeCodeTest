package com.SpreeCommerce.Methods;

import com.SpreeCommerce.PageLocators.CheckoutPage;
import com.SpreeCommerce.PageLocators.ItemPage;
import com.SpreeCommerce.PageLocators.ProductsPage;
import com.SpreeCommerce.PageLocators.SharedElements.TopbarOptions;
import com.SpreeCommerce.PageLocators.SlideoverCart;
import com.microsoft.playwright.Page;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static com.SpreeCommerce.Methods.AccountDetails.*;

public class Shopping {

    Properties prop = new Properties();
    float totalPrice = 0;
    String itemTitle;
    String itemColor;
    String itemSize;
    String itemQuantity;
    private final Page page;
    ItemPage itemPage;
    ProductsPage productsPage;
    CustomFunctions customFunctions;
    SlideoverCart slideoverCart;
    CheckoutPage checkoutPage;
    TopbarOptions topbarOptions;
    NavigatesTo navigatesTo;
    AccountDetails accountDetails;

    public Shopping(Page page) {
        this.page = page;
        itemPage = new ItemPage(page);
        productsPage = new ProductsPage(page);
        customFunctions = new CustomFunctions(page);
        slideoverCart = new SlideoverCart(page);
        checkoutPage = new CheckoutPage(page);
        topbarOptions = new TopbarOptions(page);
        navigatesTo = new NavigatesTo(page);
        accountDetails = new AccountDetails();
    }

    public void AddOrderListToCart() {
        try (FileReader reader = new FileReader("src/main/resources/OrderList.json")) {
            JSONParser jsonParser = new JSONParser();
            Object orderList = jsonParser.parse(reader);
            JSONObject itemsObj = (JSONObject) orderList;

            for(int itemsCounter = 1 ; itemsCounter <= itemsObj.size() ; itemsCounter++){
                JSONObject itemSpecsObj = (JSONObject) itemsObj.get("item" + itemsCounter);
                itemTitle = itemSpecsObj.get("title").toString();
                itemColor = itemSpecsObj.get("color").toString();
                itemSize = itemSpecsObj.get("size").toString();
                itemQuantity = itemSpecsObj.get("quantity").toString();

                //Searches for the item title
                SearchForItem(itemTitle);

                //Chooses a color based on the order details
                if(customFunctions.IsElementPresent(itemPage.colorOption(itemColor))){
                    customFunctions.keepClicking(itemPage.colorOption(itemColor),itemPage.colorHeader(itemColor));
                }

                //Chooses a size based on the order details
                if(customFunctions.IsElementPresent(itemPage.chooseSizeButton)){
                    customFunctions.keepClicking(itemPage.chooseSizeButton,itemPage.sizeOption(itemSize));
                    customFunctions.keepClicking(itemPage.sizeOption(itemSize),itemPage.selectedSize(itemSize));
                }

                //Places the quantity for the item order and adds to cart
                itemPage.quantityTextfield.fill(itemQuantity);
                AddTotalPayment();

                customFunctions.keepClicking(itemPage.addToCartButton,slideoverCart.checkoutButton);

                if(itemsCounter==itemsObj.size()){
                    Thread.sleep(3000);
                    customFunctions.keepClicking(slideoverCart.checkoutButton,checkoutPage.checkoutHeader);
                }else{
                    Thread.sleep(3000);
                    customFunctions.keepClicking(slideoverCart.closeButton,itemPage.itemHeader(itemTitle));
                    navigatesTo.ProductsPage();
                }
            }

        } catch (IOException | ParseException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void ApplySelectedFilters(){
    }

    public void SearchForItem(String itemTitle) throws InterruptedException {
        customFunctions.keepClicking(topbarOptions.searchButton,topbarOptions.searchTextfield);
        System.out.println("Searching for item: " + itemTitle);
        topbarOptions.searchTextfield.fill(itemTitle);
        if(customFunctions.IsElementPresent(productsPage.ItemName(itemTitle))){
            customFunctions.keepClicking(productsPage.ItemName(itemTitle),itemPage.itemHeader(itemTitle));
        }
      }

    public void AddTotalPayment(){
        float itemprice= 0;
        if(customFunctions.IsElementPresent(itemPage.saleIndicator)){
            itemprice = SetPriceToFloat(itemPage.itemSalePrice.innerText()) * SetPriceToFloat(itemPage.quantityTextfield.inputValue());
            totalPrice = totalPrice + itemprice;
            System.out.println("Total price so far: " + totalPrice + " ("+SetPriceToFloat(itemPage.itemSalePrice.innerText())+
                    " * " +SetPriceToFloat(itemPage.quantityTextfield.inputValue()) + ")");
        }else{
            itemprice = SetPriceToFloat(itemPage.itemRegularPrice.innerText()) * SetPriceToFloat(itemPage.quantityTextfield.inputValue());
            totalPrice = totalPrice + itemprice;
            System.out.println("Total price so far: " + totalPrice + " ("+SetPriceToFloat(itemPage.itemRegularPrice.innerText())+
                    " * " +SetPriceToFloat(itemPage.quantityTextfield.inputValue()) + ")");
        }

    }

    public float getSubtotalPayment(){
        return totalPrice;
    }

    public void AssertFinalSubtotal(){
        float sitesubtotal = SetPriceToFloat(checkoutPage.finalSubtotalText.innerText());
        System.out.println(sitesubtotal + " vs " + totalPrice);
        if(sitesubtotal==totalPrice){
            System.out.println("Checkout subtotal matches the total price calculated");
        }else{
            System.out.println("Checkout subtotal does not matches the total price calculated");
        }
    }

    public void FillsOutDeliveryDetails() throws InterruptedException {
        checkoutPage.firstNameTextfield.fill(getFirstName());
        checkoutPage.lastNameTextfield.fill(getLastName());
        checkoutPage.addressTextfield.fill(getAddress());
        checkoutPage.countryDropdown.selectOption("2581");
        checkoutPage.cityTextField.fill("Taguig");
        checkoutPage.zipCodeTextField.fill("1635");
        customFunctions.keepClicking(checkoutPage.saveAndContinueButton,checkoutPage.premiumDeliveryOption);
        checkoutPage.standardDeliveryOption.check();
    }

    public void FillOutPaymentMethod() throws InterruptedException {
        customFunctions.keepClicking(checkoutPage.saveAndContinueButton,checkoutPage.paymentHeader);
        checkoutPage.cardNumberTextfield.fill("4242 4242 4242 4242");
        checkoutPage.expirationDateTextfield.fill("12 / 30");
        checkoutPage.securityCodeTextfield.fill("123");
        checkoutPage.payNowButton.click();
    }

    public float SetPriceToFloat(String stringprice){
        float floatprice = 0;
        String dollarremoved = stringprice.replace("$", "");
        floatprice = Float.parseFloat(dollarremoved);
        return floatprice;
    }

}
