Feature: Homepage activity
Scenario Outline: User searches for flights
Given User clicks on Flights button in the home page
When User enters from location as <Departure> to location as <Arrival> for any date 
And with <Adult> and <Child>
Then User will be navigated to flight options page

Examples:

| Departure | Arrival |  Adult  | Child |
|    BLR    |   CCU   |   2     |   2   |


