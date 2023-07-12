@Regression @Login
Feature: Login

  Background:
    Given setUp

  @Login
  Scenario: A Role Advisor Validations
    Given An "advEnterpriseSecurity11FromFirm1" is logged in the system as "Advisor"


