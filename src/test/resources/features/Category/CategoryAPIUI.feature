@Regression @Category
Feature: Category

  @Category
  Scenario: Create category with API
    Given An "normalAccount" is logged in the system
    And User wipes the old categories
    When User adds a new "Root" category
    Given setUp "true"
    When Login page is displayed
    And User completes Email field with "normalAccount" credentials
    And User completes Password field with "normalAccount" credentials
    And User clicks on Login button
    Then the Home page is displayed
    Then the category was created correctly

  @Category
  Scenario: Create category with API and delete category
    Given An "normalAccount" is logged in the system
    And User wipes the old categories
    When User adds a new "Root" category
    Given setUp "true"
    When Login page is displayed
    And User completes Email field with "normalAccount" credentials
    And User completes Password field with "normalAccount" credentials
    And User clicks on Login button
    Then the Home page is displayed
    When User hits category option
    Then the category page is displayed
    When User deletes the category "true"
    Then the category was delete UI
# delete still not working?
  @Category
  Scenario: Create category with API and update category
    Given An "normalAccount" is logged in the system
    And User wipes the old categories
    When User adds a new "Root" category
    Given setUp "true"
    When Login page is displayed
    And User completes Email field with "normalAccount" credentials
    And User completes Password field with "normalAccount" credentials
    And User clicks on Login button
    Then the Home page is displayed
    When User hits category option
    Then the category page is displayed
    When the user updates the category "true"
    Then the category is correctly updated
# update not updating