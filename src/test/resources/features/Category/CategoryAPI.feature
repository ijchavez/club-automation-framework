@Regression @Category
Feature: CategoryAPI

  Background:
    Given An "normalAccount" is logged in the system
    And User wipes the old categories

  @CategoryAPI
  Scenario: Create category
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
    When User deletes the current category
    Then the category was delete correctly

  @CategoryAPI
  Scenario: Delete category
    Given User adds a new "Root" category
    When setUp "true"
    Then Login page is displayed
    When User completes Email field with "normalAccount" credentials
    And User completes Password field with "normalAccount" credentials
    And User clicks on Login button
    Then the Home page is displayed
    When User hits category option
    Then the category page is displayed
    When User deletes the category "true"
    Then the category was delete UI

  @CategoryAPI
  Scenario: Create update and delete category
    Given User adds a new "Root" category
    When setUp "true"
    Then Login page is displayed
    When User completes Email field with "normalAccount" credentials
    And User completes Password field with "normalAccount" credentials
    And User clicks on Login button
    Then the Home page is displayed
    When User hits category option
    Then the category page is displayed
    When User updates the category "true"
    Then the category is correctly updated
    When User deletes the current category
    Then the category was delete correctly