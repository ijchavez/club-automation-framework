@Regression @Category
Feature: Category

<<<<<<< HEAD:src/test/resources/features/Category/CategoryUI.feature
  Background:
    Given setUp

#create SOLAMENTE

# CREATE DELETE
=======
>>>>>>> 75aa078182eb6f85123b9640438ca9f259c20498:src/test/resources/features/Category/Category.feature
  @Category
  Scenario: Create and delete category
    Given An "normalAccount" is logged in the system
    And User wipes the old categories
<<<<<<< HEAD:src/test/resources/features/Category/CategoryUI.feature
=======
#    When User adds a new "Root" category
#    And User deletes the current category
#    Then the category was delete correctly
    Given setUp "true"
>>>>>>> 75aa078182eb6f85123b9640438ca9f259c20498:src/test/resources/features/Category/Category.feature
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

# create and UPDATE
