Feature: Validating place APIs

	Scenario: Add place API validation
	
		Given Payload for add place API is added as "<Name>", "<Address>", "<Language>"
		When User calls the "AddPlaceAPI" through "Post" Http request
		Then User gets 200 as status code for successful response
		And User gets "status" as "OK" in response body 
		And User gets "scope" as "APP" in response body 

	Example:
	|	Name		|	Address		|	Language	|
	|Swati Pattnaik |	Bangalore	|	Odia		|