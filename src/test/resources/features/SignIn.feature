Feature: Sign In functionality
  As a registered user
  I want to be able to sign in
  So that I can access my account

  Background:
    Given I open the Sign In page

  Scenario: Sign in with valid credentials
    When I enter a valid email
    And I enter a valid password
    And I click "Sign In"
    Then I should be logged in successfully

  Scenario: Sign in with valid email and wrong password
    When I enter a valid email
    And I enter an invalid password
    And I click "Sign In"
    Then I should see an error message about incorrect credentials

  Scenario: Sign in with empty email and password
    When I leave the email field empty
    And I leave the password field empty
    And I click "Sign In"
    Then I should see an error message about required fields