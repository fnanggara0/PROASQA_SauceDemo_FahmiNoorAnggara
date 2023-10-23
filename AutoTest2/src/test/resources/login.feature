Feature: Login

  Scenario: User able to login
    Given User is in the login page
    And User entered standard_user in username
    And User entered secret_sauce in password
    When User click the login button
    Then User will be able to login




