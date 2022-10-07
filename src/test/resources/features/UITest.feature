@firstTest
Feature: Test Order Players list

  Background:login to the system
    Given login with given credentials as admin
    Then admin should see admin-index page

  Scenario:Order Players list
    When admin clicks on players button
    Then admin should see Player Management page
    When admin click to order button
   Then order by column and check the sorting
