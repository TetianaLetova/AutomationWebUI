Feature: Mobile tests for ApiDemos app

  Scenario: Verify date, time and text switcher functionality
    Given the ApiDemos application is opened
    When I navigate to "Views"
    And I navigate to "Date Widgets"
    And I navigate to "1. Dialog"
    And I set the date to tomorrow
    And I set the time to "11:11 PM"
    And I return to "Views"
    And I navigate to "TextSwitcher"
    And I press the "Next" button 5 times
    Then the text field should display "5"