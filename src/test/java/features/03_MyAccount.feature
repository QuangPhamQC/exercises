@MyAccountPage
Feature: My Account page


  Scenario Outline: Check all information in My Account page
    Given Go to Login page
    When Input "<email>" value in "Email" textbox in Login page
    * Input "<password>" value in "Password" textbox in Login page
    * Click on "Log in" button in Login page
    * Click on My Account link
    * Click on "Customer info" link
    Then Verify "<gender>" gender radio button is checked in My Account page
    * Verify "<firstName>" value in "FirstName" textbox in My Account page
    * Verify "<lastName>" value in "LastName" textbox in My Account page
    * Verify "<dayBirth>" value in "Day" dropdown list in My Account page
    * Verify "<monthBirth>" value in "Month" dropdown list in My Account page
    * Verify "<yearBirth>" value in "Year" dropdown list in My Account page
    * Verify "<email>" value in "Email" textbox in My Account page
    * Verify "<companyName>" value in "Company" textbox in My Account page
    * Verify the "Newsletter" checkbox in My Account page

    Examples:
      | email              | password  | gender  | firstName | lastName  | dayBirth  | monthBirth  | yearBirth | companyName |
      | byebye@yopmail.com | 123456    | Male    | FName     | LName     | 1         | January     | 2021      | Ipsum       |


  Scenario Outline: Check all information in My Account page
    Given Go to Login page
    When Input "<email>" value in "Email" textbox in Login page
    * Input "<password>" value in "Password" textbox in Login page
    * Click on "Log in" button in Login page
    * Click on My Account link
    * Click on "Customer info" link
    * Select another "<dayBirth>" value in "Day" dropdown list in My Account page
    * Select another "<monthBirth>" value in "Month" dropdown list in My Account page
    * Select another "<yearBirth>" value in "Year" dropdown list in My Account page
    * Edit "<companyName>" value in "Company" textbox in My Account page
    * Click on "Save" button in My Account page
    Then Verify the "<successfulMessage>" successful message showed in My Account page
    * Verify "<dayBirth>" value in "Day" dropdown list in My Account page
    * Verify "<monthBirth>" value in "Month" dropdown list in My Account page
    * Verify "<yearBirth>" value in "Year" dropdown list in My Account page
    * Verify "<companyName>" value in "Company" textbox in My Account page

    Examples:
      | email              | password | dayBirth | monthBirth | yearBirth | companyName | successfulMessage                                |
      | byebye@yopmail.com | 123456   |  2       | April      | 2024      | Demo        | The customer info has been updated successfully. |


  Scenario Outline: Check all information in My Account page
    Given Go to Login page
    When Input "<email>" value in "Email" textbox in Login page
    * Input "<password>" value in "Password" textbox in Login page
    * Click on "Log in" button in Login page
    * Click on My Account link
    * Click on "Addresses" link
    * Click on "Add new" button in My Account page
    * Input "<firstName>" value in "FirstName" textbox in My Account page
    * Input "<lastName>" value in "LastName" textbox in My Account page
    * Input "<emailValue>" value in "Email" textbox in My Account page
    * Input "<companyName>" value in "Company" textbox in My Account page
    * Select "<countryValue>" value in "Country" dropdown list in My Account page
    * Select "<stateProvinceValue>" value in "StateProvince" dropdown list in My Account page
    * Input "<cityValue>" value in "City" textbox in My Account page
    * Input "<address1Value>" value in "Address1" textbox in My Account page
    * Input "<address2Value>" value in "Address2" textbox in My Account page
    * Input "<zipPostalCodeValue>" value in "ZipPostalCode" textbox in My Account page
    * Input "<phoneNumberValue>" value in "PhoneNumber" textbox in My Account page
    * Input "<faxNumberValue>" value in "FaxNumber" textbox in My Account page
    * Click on "Save" button in My Account page
    Then Verify the "<successfulMessage>" successful message showed in My Account page
    * Verify "<firstName>" and "<lastName>" values in "Name" field in My Account page
    * Verify "<emailValue>" value in "Email" field in My Account page
    * Verify "<phoneNumberValue>" value in "Phone" field in My Account page
    * Verify "<faxNumberValue>" value in "Fax" field in My Account page
    * Verify "<companyName>" value in "Company" field in My Account page
    * Verify "<address1Value>" value in "Address1" field in My Account page
    * Verify "<address2Value>" value in "Address2" field in My Account page
    * Verify "<cityValue>", "<stateProvinceValue>" and "<zipPostalCodeValue>" values in "City-State-Zip" field in My Account page
    * Verify "<countryValue>" value in "Country" field in My Account page

    Examples:
      | email              | password | firstName | lastName  | emailValue        | companyName | countryValue  | stateProvinceValue  | cityValue | address1Value | address2Value | zipPostalCodeValue  | phoneNumberValue  | faxNumberValue  | successfulMessage                            |
      | byebye@yopmail.com | 123456   | Lorem     | Ipsum     | hello@yopmail.com | WOW Limited | United States | California          | Orange    | 69 Street 1   | 99 Street 2   | 123456789           | 0908070605        | 113114115       | The new address has been added successfully. |

  @MyAccount
  Scenario Outline: Check user can login with the new password and add a new review comment successfully in My Account page
    Given Go to Login page
    When Input "<email>" value in "Email" textbox in Login page
    * Input "<password>" value in "Password" textbox in Login page
    * Click on "Log in" button in Login page
    * Click on My Account link
    * Click on "Change password" link
    * Input "<oldPassword>" value in "OldPassword" textbox in My Account page
    * Input "<newPassword>" value in "NewPassword" textbox in My Account page
    * Input "<confirmNewPassword>" value in "ConfirmNewPassword" textbox in My Account page
    * Click on "Change password" button in My Account page
    Then Verify the "<successfulMessage>" successful message showed in My Account page
    When Click on "Close" button to close the successful message in My Account page
    * Click on "Log out" link in My Account page
    * Click on "Log in" link in My Account page
    * Input "<email>" value in "Email" textbox in Login page
    * Input "<newPassword>" value in "Password" textbox in Login page
    * Click on "Log in" button in Login page
    Then Verify user can login successful on page
    When Click on "<headerTab>" header tab in My Account page
    * Click on "<productLink>" a link in My Account page
    * Click on "<aProduct>" a link in My Account page
    * Click on "<reviewLink>" a link in specific product in My Account page
    * Input "<reviewTitle>" text in "Review title" textbox in My Account page
    * Input "<reviewText>" text in "Review text" textbox in My Account page
    * Select "<ratingPoint>" a rating point from One to Five of Rating field in My Account page
    * Click on "Submit review" button in My Account page
    Then Verify "<successfullMessage>" a successfull message displayed in My Account page

    Examples:
      | email              | password   | oldPassword | newPassword | confirmNewPassword | successfulMessage    | headerTab | productLink | aProduct    | reviewLink       | reviewTitle     | reviewText                 | ratingPoint | successfullMessage                    |
      | byebye@yopmail.com | abcdeabcde | abcdeabcde  | abcdeedcba  | abcdeedcba         | Password was changed | Computers | Desktops    | VANQUISH 3  | Add your review  | This is a title | Lorem Ipsum Demo Test XXX  | 4           | Product review is successfully added. |