@Regression @Category
Feature: Cate

  Background:
    Given setUp

  @Category
  Scenario: A Flat Adv can access to whole Adv the firm
    Given An "advFlatSecurity11FromFirm1" is logged in the system as "Advisor"
