@Contact
Feature: Read Contact

  Scenario: User can Read the contact via API
    Given set the url for Read
    And set the expected data for read
    When send the get request for read
    Then do assertion for read