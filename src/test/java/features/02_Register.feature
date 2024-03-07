@Register
Feature: Register page


  Scenario Outline: Check the error messages showed on all required fields
    Given Go to Login page
    When  Click on "Register" button in Login page
    * Click on "Register" button in Register page
    Then Verify "<errorMessageFirstName>" error message in First name field in Register page
    * Verify "<errorMessageLastName>" error message in Last name field in Register page
    * Verify "<errorMessageEmail>" error message in Email field in Register page
    * Verify "<errorMessagePassword>" error message in Password field in Register page
    * Verify "<errorMessageConfirmPassword>" error message in Confirm password field in Register page
    When Input "<emailValue>" value in "Email" textbox in Register page
    * Click on "Register" button in Register page
    Then Verify "<errorMessage>" error message in Email field in Register page

    Examples:
    | errorMessageFirstName    | errorMessageLastName    | errorMessageEmail  | errorMessagePassword  | errorMessageConfirmPassword  |  emailValue        | errorMessage  |
    | First name is required.  | Last name is required.  | Email is required. | Password is required. | Password is required.        | testmail@@@abc.com | Wrong email   |


  Scenario Outline: Check user can register account successfully
    Given Go to Register page
    When Click on "<gender>" value in Register page
    * Input "<firstName>" value in "FirstName" textbox in Register page
    * Input "<lastName>" value in "LastName" textbox in Register page
    * Select "<dayBirth>" value in "Day" dropdown list in Register page
    * Select "<monthBirth>" value in "Month" dropdown list in Register page
    * Select "<yearBirth>" value in "Year" dropdown list in Register page
    * Input "<emailValue>" value in "Email" textbox in Register page
    * Input "<companyName>" value in "Company" textbox in Register page
    * Check the "Newsletter" checkbox in Register page
    * Input "<password>" value in "Password" textbox in Register page
    * Input "<confirmPassword>" value in "ConfirmPassword" textbox in Register page
    * Click on "Register" button in Register page
    Then Verify "<successfullMessage>" successfully message in Register page

    Examples:
    | gender  | firstName  | lastName  | dayBirth | monthBirth | yearBirth | emailValue         | companyName | password  | confirmPassword | successfullMessage          |
    | male    | FName      | LName     | 31       | December   | 1999      | yahoo@yopmail.com  | Lorem       | 123456    | 123456          | Your registration completed |


  Scenario Outline: Check user can register account successfully
    Given Go to Register page
    When Click on "<gender>" value in Register page
    * Input "<firstName>" value in "FirstName" textbox in Register page
    * Input "<lastName>" value in "LastName" textbox in Register page
    * Select "<dayBirth>" value in "Day" dropdown list in Register page
    * Select "<monthBirth>" value in "Month" dropdown list in Register page
    * Select "<yearBirth>" value in "Year" dropdown list in Register page
    * Input "<emailValue>" value in "Email" textbox in Register page
    * Input "<companyName>" value in "Company" textbox in Register page
    * Check the "Newsletter" checkbox in Register page
    * Input "<password>" value in "Password" textbox in Register page
    * Input "<confirmPassword>" value in "ConfirmPassword" textbox in Register page
    * Click on "Register" button in Register page
    Then Verify "<errorMessage>" error message for exist email case in Register page

    Examples:
      | gender  | firstName  | lastName  | dayBirth | monthBirth | yearBirth | emailValue         | companyName | password  | confirmPassword | errorMessage                       |
      | male    | FName      | LName     | 31       | December   | 1999      | yahoo@yopmail.com  | Lorem       | 123456    | 123456          | The specified email already exists |

  @RegisterPage
  Scenario Outline: Check the error message showed on password field if password have less than 6 characters
    Given Go to Register page
    When Click on "<gender>" value in Register page
    * Input "<firstName>" value in "FirstName" textbox in Register page
    * Input "<lastName>" value in "LastName" textbox in Register page
    * Select "<dayBirth>" value in "Day" dropdown list in Register page
    * Select "<monthBirth>" value in "Month" dropdown list in Register page
    * Select "<yearBirth>" value in "Year" dropdown list in Register page
    * Input "<emailValue>" value in "Email" textbox in Register page
    * Input "<companyName>" value in "Company" textbox in Register page
    * Check the "Newsletter" checkbox in Register page
    * Input "<password>" value in "Password" textbox in Register page
    * Click on "Register" button in Register page
    Then Verify "<errorMessage>" error message for incorrect password in Register page

    Examples:
      | gender  | firstName  | lastName  | dayBirth | monthBirth | yearBirth | emailValue         | companyName | password  | errorMessage                                                            |
      | male    | FName      | LName     | 31       | December   | 1999      | yahoo@yopmail.com  | Lorem       | 12345     | Password must meet the following rules: must have at least 6 characters |

  @RegisterPage
  Scenario Outline: Check the error message showed on confirm password field when password is difference with confirm password
    Given Go to Register page
    When Click on "<gender>" value in Register page
    * Input "<firstName>" value in "FirstName" textbox in Register page
    * Input "<lastName>" value in "LastName" textbox in Register page
    * Select "<dayBirth>" value in "Day" dropdown list in Register page
    * Select "<monthBirth>" value in "Month" dropdown list in Register page
    * Select "<yearBirth>" value in "Year" dropdown list in Register page
    * Input "<emailValue>" value in "Email" textbox in Register page
    * Input "<companyName>" value in "Company" textbox in Register page
    * Check the "Newsletter" checkbox in Register page
    * Input "<password>" value in "Password" textbox in Register page
    * Input "<confirmPassword>" value in "ConfirmPassword" textbox in Register page
    * Click on "Register" button in Register page
    Then Verify "<errorMessage>" error message for incorrect password in Register page

    Examples:
      | gender  | firstName  | lastName  | dayBirth | monthBirth | yearBirth | emailValue         | companyName | password  | confirmPassword | errorMessage                                         |
      | male    | FName      | LName     | 31       | December   | 1999      | yahoo@yopmail.com  | Lorem       | 123456    | 12345           | The password and confirmation password do not match. |