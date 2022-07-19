@Outpatientt
Feature: OutPatientt Endpoint

  @CreateProcessDoctor
  Scenario: Process data outpatient
    Given I success login as admin
    And I create new patient
    And I Get List All patient
    And I create new outpatient
    And I success login as doctor
    And I Get List All Outpatient
    When I Create Process Doctor
    Then I see response message process doctor

  @CreateProcessNurse
  Scenario: Process data outpatient
    Given I success login as admin
    And I create new patient
    And I Get List All patient
    And I create new outpatient
    And I success login as doctor
    And I Get List All Outpatient
    When I assign nurse
    And I success login as nurse
    And I Create Process nurse
    And I success login as doctor
    And I Create Process Doctor
    Then I see response message process doctor