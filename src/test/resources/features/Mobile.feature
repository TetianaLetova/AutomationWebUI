Feature: ApiDemos application UI tests
  As a mobile user
  I want to interact with the Views section
  So that I can verify its functionality

  Background:
    Given the ApiDemos application is opened

  Scenario: Verify number of elements in Views screen
    When I navigate to "Views"
    Then I should see 42 navigation buttons on the screen

  Scenario: Set date and time in Data Widgets dialog
    When I navigate to "Views"
    And I navigate to "Data Widgets"
    And I navigate to "1. Dialog"
    And I set the date to tomorrow
    And I set the time to "11:11 PM"
    Then the selected date should be tomorrow
    And the selected time should be "11:11 PM"

  Scenario: Verify TextSwitcher Next button functionality
    When I navigate to "Views"
    And I navigate to "TextSwitcher"
    And I press the "Next" button 5 times
    Then the text field should display "5"
