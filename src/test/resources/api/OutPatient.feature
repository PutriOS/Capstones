@Outpatient
Feature: OutPatient Endpoint

  @CreateOutpatient
  Scenario Outline: Create data user
    Given I success login as admin
    When I send "<patientCode>", and "<doctorCode>", and <facilityId>, and "<complaint>", and <sessionId> and "<dateCheck>"
    Then I received HTTP response <code>
    And I validate response "<responses>" message create Outpatient
    Examples:
      |patientCode|doctorCode|facilityId|complaint|sessionId|dateCheck|code|responses|
      |blank      |DCR00001   |1         |Sakit Lambung|1   |2022-07-06|400|patient_code|
#      |RM00009    |blank      |1         |Sakit Lambung|1   |2022-07-06|400|doctor_code |
      |RM00009    |DCR00001   |0         |Sakit Lambung|1   |2022-07-06|400|facility_id |
#      |RM00009    |DCR00001   |1         |blank        |1   |2022-07-06|400|complaint   |
      |RM00009    |DCR00001   |1         |Sakit Lambung|0   |2022-07-06|400|session_id  |
      |RM00009    |DCR00001   |1         |Sakit Lambung|1   |blank     |400|date_check  |

  @GetID
  Scenario: Get ID data OupPatient
    Given I success login as admin
    When I send get ID endpoint
    Then I received HTTP response 200

  @GetReport
  Scenario: Get Report data OutPatient
    Given I success login as admin
    When I send request get report endpoint
    Then I received HTTP response 200

  @GetLogReport
  Scenario: Get Log Report data OutPatient
    Given I success login as doctor
    When I send request get logReport endpoint
    Then I received HTTP response 200

  @ListAll
  Scenario: Get List All data OutPatient
    Given I success login as doctor
    When I send request get list All endpoint
    Then I received HTTP response 200
