@Category
Feature: CategoryUI

  @CategoryUI
  Scenario: Create category
    Given An "normalAccount" is logged in the system
    And User wipes the old categories
    Given setUp "true"
    When Login page is displayed
    And User completes Email field with "normalAccount" credentials
    And User completes Password field with "normalAccount" credentials
    And User clicks on Login button
    Then the Home page is displayed
    When User hits category option
    Then the category page is displayed
    When User clicks Add category button
    Then The category modal is displayed
    When User enters the category information
    Then the category was created correctly

  @CategoryUI
  Scenario: Create and delete category
    Given An "normalAccount" is logged in the system
    And User wipes the old categories
    Given setUp "true"
    When Login page is displayed
    And User completes Email field with "normalAccount" credentials
    And User completes Password field with "normalAccount" credentials
    And User clicks on Login button
    Then the Home page is displayed
    When User hits category option
    Then the category page is displayed
    When User clicks Add category button
    Then The category modal is displayed
    When User enters the category information
    Then the category was created correctly
    When User deletes the category "true"
    Then the category was delete UI

  @CategoryUI
  Scenario: Create and update category
    Given An "normalAccount" is logged in the system
    And User wipes the old categories
    Given setUp "true"
    When Login page is displayed
    And User completes Email field with "normalAccount" credentials
    And User completes Password field with "normalAccount" credentials
    And User clicks on Login button
    Then the Home page is displayed
    When User hits category option
    Then the category page is displayed
    When User clicks Add category button
    Then The category modal is displayed
    When User enters the category information
    Then the category was created correctly
    When User updates the category "true"
    Then the category is correctly updated