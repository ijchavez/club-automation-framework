@Regression @Login
Feature: Login

  Background:
    Given setUp

  @Login
  Scenario: A Role Advisor Validations
    Given An "normalAccount" is logged in the system

#login successful
#login failed assertions on message
