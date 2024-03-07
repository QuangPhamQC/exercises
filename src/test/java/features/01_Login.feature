@Login
Feature: Login page

  @Demo
  Scenario Outline: Check mandatory field for Login
    Given Go to Login page
    When Click on "Log in" button in Login page
    Then Verify "<errorMessage>" error message in Login page

    Examples:
      | errorMessage                  |
      | User or Password is not valid |

  Scenario Outline: Check invalid email
    Given Go to Login page
    When Input "<email>" value in "Email" textbox in Login page
    And Click on "Log in" button in Login page
    Then Verify "<errorMessage>" error message in Email field in Login page

    Examples:
      | email             | errorMessage        |
      | loremipsum@@@123  | Wrong email         |

  Scenario Outline: Check error message with incorrect email or password
    Given Go to Login page
    When Input "<email>" value in "Email" textbox in Login page
    * Input "<password>" value in "Password" textbox in Login page
    * Click on "Log in" button in Login page
    Then Verify an error message in Login page

    Examples:
    | email               | password  |
    | loremipsum@123.com  | 123456    |

  Scenario Outline: Check error message with blank password
    Given Go to Login page
    When Input "<email>" value in "Email" textbox in Login page
    * Click on "Log in" button in Login page
    Then Verify an error message in Login page

    Examples:
    | email               |
    | loremipsum@123.com  |

  Scenario Outline: Check error message with correct email and incorrect password
    Given Go to Login page
    When Input "<email>" value in "Email" textbox in Login page
    * Input "<password>" value in "Password" textbox in Login page
    * Click on "Log in" button in Login page
    Then Verify an error message in Login page

    Examples:
      | email               | password  |
      | loremipsum@123.com  | 123456    |

  Scenario Outline: Check user can login successful
    Given Go to Login page
    When Input "<email>" value in "Email" textbox in Login page
    * Input "<password>" value in "Password" textbox in Login page
    * Click on "Log in" button in Login page
    Then Verify user can login successful on page

    Examples:
      | email               | password  |
      | byebye@yopmail.com  | 123456    |
