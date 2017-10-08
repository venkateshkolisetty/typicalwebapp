Feature: TypicalWebAppController 

Scenario: Response valid when with valid parameter 
	Given Initiate TypicalWebApp Controller 
	When Send TestRequestResponse Request with valis parameter 
	Then Verify valid response received for TestRequestResponse 
