@MyAccountPage
Feature: My Account page

  @MyAccount
  Scenario Outline: Check all information in My Account page
    Given Go to Login page
    When Input "<email>" value in "Email" textbox in Login page
    * Input "<password>" value in "Password" textbox in Login page
    * Click on "Log in" button in Login page
    * Go to My Account page
    * Click on "My Account"