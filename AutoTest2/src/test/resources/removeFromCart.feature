Feature: Remove from Cart

  Scenario: User able to remove product from cart
    Given User is already login and have product in cart
    When User clicks on remove button
    Then User will be able to remove product from cart