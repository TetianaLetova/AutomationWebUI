package com.andersen.lecture18.utils;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "invalidLoginData")
    public Object[][] getInvalidLoginData() {
        return new Object[][] {
                {"testuser@mail.com", "wrongPassword", "Invalid email or password"},
                {"invalid-email", "Pass123!", "Invalid email format"},
                {"", "", "Email is required"}
        };
    }

    @DataProvider(name = "invalidRegistrationData")
    public Object[][] getInvalidRegistrationData() {
        return new Object[][] {
                {"", "Smith", "01/01/1995", "test@mail.com", "Pass123!", "Pass123!", "First Name is required"},
                {"John", "Smith", "32/13/3000", "test@mail.com", "Pass123!", "Pass123!", "Invalid date"},
                {"John", "Smith", "01/01/1995", "invalid-email", "Pass123!", "Pass123!", "Invalid email format"},
                {"John", "Smith", "01/01/1995", "test@mail.com", "123", "123", "Password must be at least 6 characters"},
                {"John", "Smith", "01/01/1995", "test@mail.com", "Pass123!", "Different123", "Passwords do not match"}
        };
    }
}