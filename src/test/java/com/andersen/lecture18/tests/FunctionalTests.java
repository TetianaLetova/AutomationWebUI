package com.andersen.lecture18.tests;

import io.qameta.allure.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Functional Tests")
public class FunctionalTests extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(FunctionalTests.class);

    @Feature("Actions, Alerts & Iframes")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify the behavior of actions, alerts and iframes in the web app")
    @Test(description = "Actions, Alerts & Iframes Test - Refactored with POM")
    public void testActionsAlertsIframes() {
        logger.info("Logging in with valid user");
        loginAsValidUser();
        logger.info("User logged in successfully");

        dashboardPage.clickExpandIcon();
        logger.info("Clicked dashboard expand icon");

        dashboardPage.clickActionsAlertsIframes();
        logger.info("Navigated to Actions, Alerts & Iframes page");

        wait.until(ExpectedConditions.urlContains("/actions"));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.warn("Interrupted during sleep", e);
        }

        actionsAlertsIframesPage.switchToIframe();
        logger.info("Switched to iframe");

        actionsAlertsIframesPage.clickAlertButton();
        actionsAlertsIframesPage.handleAlert();
        logger.info("Handled alert after clickAlertButton");

        actionsAlertsIframesPage.doubleClickDiscountButton();
        actionsAlertsIframesPage.handleAlert();
        logger.info("Handled alert after doubleClickDiscountButton");

        actionsAlertsIframesPage.rightClickCancelCourseButton();
        actionsAlertsIframesPage.handlePromptAlert("Test");
        logger.info("Handled prompt alert with message 'Test'");

        Assert.assertTrue(actionsAlertsIframesPage.isSuccessMessageDisplayed(),
                "The cancellation message with Test reason should be visible");

        String successText = actionsAlertsIframesPage.getSuccessMessageText();
        Assert.assertTrue(successText.contains("Test"),
                "Success message should contain the entered reason 'Test'");
        logger.info("Success message verified: {}", successText);
    }

    @Feature("Drag and Drop")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify drag and drop functionality")
    @Test(description = "Drag and Drop Test - Refactored with POM")
    public void testDragAndDrop() {
        logger.info("Logging in with valid user");
        loginAsValidUser();

        dashboardPage.clickExpandIcon();
        logger.info("Clicked dashboard expand icon");

        dashboardPage.clickDragAndDrop();
        logger.info("Navigated to Drag and Drop page");

        wait.until(ExpectedConditions.urlContains("/dragndrop"));

        dragAndDropPage.performAllDragAndDropActions();
        logger.info("Performed drag and drop actions");

        Assert.assertTrue(dragAndDropPage.isSuccessMessageDisplayed(),
                "Success message 'Congratulations! Let's test for the best!' should be displayed");
        logger.info("Drag and drop success message verified");
    }

    @Feature("Select Dropdowns")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify dropdown selection functionality")
    @Test(description = "Select Dropdowns Test - Refactored with POM")
    public void testSelectDropdowns() {
        logger.info("Logging in with valid user");
        loginAsValidUser();

        dashboardPage.clickExpandIcon();
        logger.info("Clicked dashboard expand icon");

        dashboardPage.clickSelect();
        logger.info("Navigated to Select Dropdowns page");

        wait.until(ExpectedConditions.urlContains("/select"));

        selectPage.selectCountry("USA");
        selectPage.selectLanguage("English");
        selectPage.selectType("Testing");
        selectPage.setStartDate("11-08-2025");
        selectPage.setEndDate("25-08-2025");
        selectPage.selectMultipleCourses("AQA Java", "AQA Python");
        selectPage.clickSearchButton();
        logger.info("Filled select dropdowns and clicked search");

        wait.until(ExpectedConditions.urlContains("/search_results"));

        String expectedText = "Unfortunately, we did not find any courses matching your chosen criteria.";
        String actualText = searchResultsPage.getResultMessage();

        Assert.assertTrue(actualText.contains(expectedText),
                "Expected result: " + expectedText + ", actual result: " + actualText);
        logger.info("Search results verified with expected message");
    }
}
