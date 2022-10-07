@firstTest
Feature: Test API
  Scenario: Test API
    Given get quest token
    Then register player
    And authorize as created player
    And get details of player
    And get details of other player