@Regression @Login
Feature: Login

  Background:
    Given setUp

  @Logins
  Scenario: Login successful with correct credentials
    Given An "normalAccount" is logged in the system
    When Login page is displayed
    And User completes Email field with "normalAccount" credentials
    And User completes Password field with "normalAccount" credentials
    And User clicks on Login button
    Then the Home page is displayed

  @Login1
  Scenario Outline: Login failed with empty credentials
    When Login page is displayed
    And User completes Email field with any credentials <user>
    And User completes Password field with any credentials <password>
    And the Login button is "Disabled"

    Examples:
      | user           | password   |
      | ""             | ""         |
      | ""             | "12345678" |
      | "graviel.sosa" | ""         |
