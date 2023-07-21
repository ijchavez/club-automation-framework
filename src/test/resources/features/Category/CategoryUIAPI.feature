@Regression @Category
Feature: Category

  Background:
    Given setUp

# CREATE POR UI

  @Category
  Scenario: Create and delete category
    Given An "normalAccount" is logged in the system
    And User wipes the old categories
    When User adds a new "Root" category
    And User deletes the current category
    Then the category was delete correctly

#    Then the category was delete UI

# CREATE ON API AND UPDATE
# CREATE ON API AND DELETE