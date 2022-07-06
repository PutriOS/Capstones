@User
Feature: User Endpoint

  @CreateUser
  Scenario Outline: Create data user
    Given I success login as admin
    When I send "<email>", and <facility_id>, and "<full_name>", and "<gender>", and <role_id>
    Then I received HTTP response <code>
    And I validate response "<response>" message create user
    Examples:
    |email|facility_id|full_name|gender|role_id|code|response|
    |emails|2         |fullnames|Male  |2      |201 |created |
    |blank|2          |Carvey Chennico|Male|2  |400 |email null|
    |carveychen@holyhos.co.id|0|Carvey Chennico|Male|2  |400 |facility_id null|
    |carveychen@holyhos.co.id|2|blank|Male|2  |400 |full_name null|
    |carveychen@holyhos.co.id|2|Carvey Chennico|blank|2  |400 |gender null|
    |carveychen@holyhos.co.id|2|Carvey Chennico|Male|0|400 |role_id null|


  @GetAllUser
  Scenario: Get Report data user
    Given I success login as admin
    When I send request get user endpoint
    Then I received HTTP response 200
    And I validate user data

  @GetIDUser
  Scenario: Get Id data user
    Given I success login as admin
    When I send request get id user endpoint
    Then I received HTTP response 200
    And I validate get id user data

  @UpdateUser
  Scenario: Create data user
    Given I success login as admin
    And I success create user
    When I send request update create data user
    Then I received HTTP response 200
    And I validate response message update user
