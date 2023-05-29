
@tag
Feature: Purchase the product from ECommerce Website
	
	Background:
	Given I landed on Ecommerce Page
	
	@Regression
	Scenario Outline: Positive test of submitting the order
	Given Logged in with username <name> and password <password>
	When I add product <productName> into Cart
	And CheckOut <productName> and submit the order
	Then "THANKYOU FOR THE ORDER" message is displayed on Confirmation page
	
	
	
	Examples:
		|	name			   |	password		|	productName   |
		|	saikumar@gmail.com |		validone	|	ZARA COAT 3   |
