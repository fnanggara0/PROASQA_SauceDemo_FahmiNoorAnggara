Feature: Checkout Success

  Scenario: User able to checkout
  Given User is already have product in cart
  And User tap on checkout button
  And User input John in first name
  And User input Doe in last name
  And User input 112233 in postal code
  And User tap on the continue button
  When User tap on the finish button on the Checkout Overview
  Then User will be able to checkout