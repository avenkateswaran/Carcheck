Feature:login

  Scenario: Login into google
    Given I navigate to the car tax check website
    When I enter the registration numbers and click car free check button and extract the details
    Then I proceed furthur