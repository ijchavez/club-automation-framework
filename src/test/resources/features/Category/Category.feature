@Regression @Category
Feature: Category

  Background:
    Given setUp


  @Category
  Scenario: Create and delete category
    Given An "normalAccount" is logged in the system
    And User wipes the categories
    When User adds a new "Root" category
    And User deletes the current category
    Then the category was delete correctly
