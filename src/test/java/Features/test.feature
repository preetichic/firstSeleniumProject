Feature: search  a product
  As a user i want to search a product so that i can find the results

  Scenario: search an item using product name
    Given I am on the home page "https://www.next.co.uk"
    When I search an item using product name "Jeans"
    And I click search button
    Then I should only be seen related results to Jeans

  Scenario: search an item using brand name
    Given I am on the home page "https://www.next.co.uk"
    When I search an item using brand name "Nike"
    And I click search button
    Then I should only be seen related results to Nike