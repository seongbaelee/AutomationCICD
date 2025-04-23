
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

  #@tag1
  #Scenario: Positive Test of Submitting the order
    #Given Logged in with username
    #And some other precondition
    #When I complete action
    #And some other action
    #And yet another action
    #Then I validate the outcomes
    #And check more outcomes

	Background:
	Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart 
    And Checkout <productName> and submit the order
    Then "Thankyou for the order." message is displayed on ConfirmationPage

    Examples: 
      | name  					 | password | productName     |
      | qwe123@daum.com  | Qwe123!@ | ADIDAS ORIGINAL |
      | qwe1233@daum.com | Qwe123!@ | IPHONE 13 PRO   |