@Outpatient
Feature: OutPatient Endpoint

  @Create
  Scenario: Create data OutPatient
    Given I success login as admin
    When I send request create data outpatient endpoint
    Then I received HTTP response 201
    And I validate response message create Outpatient

#  @CreateUser
#  Scenario Outline: Create data user
#    Given I success login as admin
#    And I create new patient
#    When I send "<patient_code>", and "<doctor_code>", and <facility_id>, and "<complaint>", and <session_id> and "<date_check>"
#    Then I received HTTP response <code>
#    And I validate response "<response>" message create user
#    Examples:
#      |email|facility_id|full_name|gender|role_id|code|response|
#      |emails|2         |fullnames|Male  |2      |201 |created |
#      |blank|2          |Carvey Chennico|Male|2  |400 |email null|
#      |carveychen@holyhos.co.id|0|Carvey Chennico|Male|2  |400 |facility_id null|
#      |carveychen@holyhos.co.id|2|blank|Male|2  |400 |full_name null|
#      |carveychen@holyhos.co.id|2|Carvey Chennico|blank|2  |400 |gender null|
#      |carveychen@holyhos.co.id|2|Carvey Chennico|Male|0|400 |role_id null|


  @GetID
  Scenario: Get ID data OupPatient
    Given I success login as admin
    When I send get ID endpoint
    Then I received HTTP response 200
    And I validate data OutPatient

  @GetReport
  Scenario: Get Report data OutPatient
    Given I success login as admin
    When I send request get report endpoint
    Then I received HTTP response 200
    And I validate report OutPatient data

  @GetLogReport
  Scenario: Get LogReport data OutPatient
    Given I success login as doctor
    When I send request get logReport endpoint
    Then I received HTTP response 200
    And I validate logreport OutPatient data