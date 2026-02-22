package com.SpreeCommerce.Methods;

import java.util.Random;

public class AccountDetails {

    private static final String ALLOWED_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
    private static String Username;
    private static String Password;
    private static String FirstName;
    private static String LastName;
    private static String Address;

    public static void generateNewAccount(){
        StringBuilder username = new StringBuilder();
        StringBuilder password = new StringBuilder();
        StringBuilder fname = new StringBuilder();
        StringBuilder lname = new StringBuilder();
        StringBuilder address = new StringBuilder();
        Random random = new Random();

        while (username.length() < 15) {
            int index = (int) (random.nextFloat() * ALLOWED_CHARS.length());
            username.append(ALLOWED_CHARS.charAt(index));
        }
        Username = username + "@mailinator.com";

        while (password.length() < 25) {
            int index = (int) (random.nextFloat() * ALLOWED_CHARS.length());
            password.append(ALLOWED_CHARS.charAt(index));
        }
        Password = password.toString();

        while (fname.length() < 10) {
            int index = (int) (random.nextFloat() * ALLOWED_CHARS.length());
            fname.append(ALLOWED_CHARS.charAt(index));
        }
        FirstName = fname.toString();

        while (lname.length() < 10) {
            int index = (int) (random.nextFloat() * ALLOWED_CHARS.length());
            lname.append(ALLOWED_CHARS.charAt(index));
        }
        LastName = lname.toString();

        while (address.length() < 10) {
            int index = (int) (random.nextFloat() * ALLOWED_CHARS.length());
            address.append(ALLOWED_CHARS.charAt(index));
        }
        Address = lname.toString();
    }

    public static String getUsername(){return Username;}

    public static String getPassword(){return Password;}

    public static String getFirstName(){return FirstName;}

    public static String getLastName(){return LastName;}

    public static String getAddress(){return Address;}
}
