package com.SpreeCommerce.Methods;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

public class CustomFunctions {
    private final Page page;

    public CustomFunctions(Page page) {
        this.page = page;
    }

    public void keepClicking(Locator locatorforclick, Locator expectedlocatorafter){
        boolean success = false;
        int MAX_RETRIES = 5;
        int attempt = 0;
        while (attempt < MAX_RETRIES && !success) {
            try {
//                System.out.println("Attempt: " + (attempt + 1) + " to click the element: '" + locatorforclick + "'");
                if(IsElementPresent(locatorforclick)){
                    locatorforclick.click(new Locator.ClickOptions().setTimeout(3000));
                }
                expectedlocatorafter.waitFor(new Locator.WaitForOptions().setTimeout(3000));
                PlaywrightAssertions.assertThat(expectedlocatorafter).isVisible(new LocatorAssertions.IsVisibleOptions().setTimeout(3000));
                success = true; // Operation succeeded, exit loop
                System.out.println("Element '"+locatorforclick+"' is clicked correctly!");
                System.out.println("Element '"+expectedlocatorafter+"' is available!");
                break;
            } catch (Exception e) {
                attempt++;
                System.out.println("Expected Element '" +expectedlocatorafter+ "' not found!");
                if (attempt < MAX_RETRIES) {
                    System.out.println("Retrying...");
                    try {
                        Thread.sleep(1500); // Wait for 1.5 seconds
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    System.out.println("Maximum retries reached. Aborting.");
                    System.out.println(e.getMessage());
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public boolean IsElementPresent(Locator expectedlocatorafter){
        Boolean iselementpresent = false;
        int MAX_RETRIES = 3;
        int attempt = 0;
        while (attempt < MAX_RETRIES && !iselementpresent) {
            try {
//                System.out.println("Attempt: " + (attempt + 1) + " to find the element: '" + expectedlocatorafter + "'");
                expectedlocatorafter.waitFor(new Locator.WaitForOptions().setTimeout(1000));
                PlaywrightAssertions.assertThat(expectedlocatorafter).isVisible(new LocatorAssertions.IsVisibleOptions().setTimeout(500));
                iselementpresent = true; // Operation succeeded, exit loop
//                System.out.println("Element '"+expectedlocatorafter+"' is available!");
                break;
            } catch (Exception e) {
                attempt++;
//                System.out.println("Expected Element '" +expectedlocatorafter+ "' not found!");
                if (attempt < MAX_RETRIES) {
//                    System.out.println("Retrying...");
                    try {
                        Thread.sleep(1000); // Wait for 1 second
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                } else {
//                    System.out.println("Maximum retries reached. Aborting.");
                }
            }
        }
        return iselementpresent;
    }
}
