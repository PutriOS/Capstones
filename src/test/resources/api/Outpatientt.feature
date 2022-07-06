@Outpatientt
Feature: OutPatientt Endpoint

  @Create
  Scenario: Process data outpatient
    Given I success login as doctor
    And I create new patient
    And I Get List Al
    When I create new outpatient
    And I Create Process Doctor
    Then I Get report log