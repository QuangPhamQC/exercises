@SearchPage
Feature: Search page

  @Search
  Scenario Outline: Check all actions in Search page
    Given Go to Search page
    When Click on Search button in Search page
    Then Verify "<warningMessage>" error message when searching in Search page
    When Input "<incorrectValue>" value in Search textbox in Search page
    * Click on Search button in Search page
    Then Verify "<errorMessage>" error message when searching in Search page
    When Input "<fullNameValue>" value in Search textbox in Search page
    * Click on Search button in Search page
    Then Verify "<fullNameResult>" all data results displayed in Search page
    When Input "<commonValue>" value in Search textbox in Search page
    * Click on Search button in Search page
    Then Verify "<commonResult>" all data results displayed in Search page

    Examples:
      | warningMessage                             | incorrectValue | errorMessage                                      | fullNameValue           | fullNameResult | commonValue | commonResult |
      | Search term minimum length is 3 characters | abcxyz         | No products were found that matched your criteria.| Build your own computer | 1              | Desktop     | 3            |