package com.andersen.lecture18.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FunctionalTests extends BaseTest {

    @Test(description = "Actions, Alerts & Iframes Test - Refactored with POM")
    public void testActionsAlertsIframes() {
        loginAsValidUser();

        dashboardPage.clickExpandIcon();
        dashboardPage.clickActionsAlertsIframes();

        wait.until(ExpectedConditions.urlContains("/actions"));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        actionsAlertsIframesPage.switchToIframe();
        actionsAlertsIframesPage.clickAlertButton();
        actionsAlertsIframesPage.handleAlert();
        actionsAlertsIframesPage.doubleClickDiscountButton();
        actionsAlertsIframesPage.handleAlert();
        actionsAlertsIframesPage.rightClickCancelCourseButton();
        actionsAlertsIframesPage.handlePromptAlert("Test");

        Assert.assertTrue(actionsAlertsIframesPage.isSuccessMessageDisplayed(),
                "The cancellation message with Test reason should be visible");

        String successText = actionsAlertsIframesPage.getSuccessMessageText();
        Assert.assertTrue(successText.contains("Test"),
                "Success message should contain the entered reason 'Test'");
    }

    @Test(description = "Drag and Drop Test - Refactored with POM")
    public void testDragAndDrop() {
        loginAsValidUser();

        dashboardPage.clickExpandIcon();
        dashboardPage.clickDragAndDrop();

        wait.until(ExpectedConditions.urlContains("/dragndrop"));

        dragAndDropPage.performAllDragAndDropActions();

        Assert.assertTrue(dragAndDropPage.isSuccessMessageDisplayed(),
                "Success message 'Congratulations! Let's test for the best!' should be displayed");
    }

    @Test(description = "Select Dropdowns Test - Refactored with POM")
    public void testSelectDropdowns() {
        loginAsValidUser();

        dashboardPage.clickExpandIcon();
        dashboardPage.clickSelect();

        wait.until(ExpectedConditions.urlContains("/select"));

        selectPage.selectCountry("USA");
        selectPage.selectLanguage("English");
        selectPage.selectType("Testing");
        selectPage.setStartDate("04-08-2025");
        selectPage.setEndDate("18-08-2025");
        selectPage.selectMultipleCourses("AQA Java", "AQA Python");
        selectPage.clickSearchButton();

        wait.until(ExpectedConditions.urlContains("/search_results"));

        String expectedText = "Unfortunately, we did not find any courses matching your chosen criteria.";
        String actualText = searchResultsPage.getResultMessage();

        Assert.assertTrue(actualText.contains(expectedText),
                "Expected result: " + expectedText + ", actual result: " + actualText);
    }
}
