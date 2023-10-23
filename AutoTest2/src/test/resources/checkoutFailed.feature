Feature: Checkout Failed

  Scenario: User failed to checkout
    Given User is already in checkout page
    When User try to tap on the continue button
    Then User wont be able to checkout