Feature: Banking Workflows Features
  Verify if client is able to register, open an account, make a transfer and log out
  @Register
  Scenario: Register as bank customer
    Given user is on homepage
    When user navigates to Register Page
    And user enters the data required for registration
    Then success message is deplayed
    And take a screenshot
    Then  valid if the user is the one who has registered.


  @Login
  Scenario: Login in the bank
    Given user is on homepage
    When  user enters his login credentials
    And take a screenshot
    Then  user enters the main dashboard


  @NewSavingsAccount
  Scenario: Open new savings account
    Given user is on homepage
    When user enters his login credentials
    When user clicks on the new account link
    Then message indicates open account


  @TransferAndLogout
  Scenario: transfer to the savings account and logout
    Given user is on homepage
    When user enters his login credentials
    When user clicks on the transfer link
    When user enters transfer details
    And user click transfer button
    Then message indicates Transer complete
    And take a screenshot
    When click logout link



