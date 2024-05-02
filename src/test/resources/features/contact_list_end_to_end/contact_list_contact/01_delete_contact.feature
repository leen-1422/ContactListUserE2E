@Contact
Feature: Delete Contact

  Scenario: User can delete the Contact via API
    Given set the url for delete contact
    When send the delete request contact
    Then do assertion for delete contact

