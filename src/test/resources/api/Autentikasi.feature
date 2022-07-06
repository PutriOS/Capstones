@Autentikasi
Feature: Logout User Endpoint

  @Login
  Scenario Outline: POST - As a user i have to be able to login
    Given I set an endpoint for login
    When I request POST for login with "<email>" and "<password>"
    Then I validate the status code <status_code>
    And I validate the "<hasil>" response message
    Examples:
      |email|password|status_code|hasil|
      |rimurutempest@holyhos.co.id|password|200|login|
      |alsyadahmad@holyhos.co.id|password|200|login|
      |priscillahalim@holyhos.co.id|password|200|login|
      |alsyadahmad@holyhos.co.id||400|password null   |
      ||password|400|email null                       |
      ||        |400|email and password null          |
      |alsyadahmad@holyhos.co.id|qwwee|400|invalid length password|
      |putri@holyhos.co.id      |password|404|wrong email         |
      |alsyadahmad@holyhos.co.id|putri123|417|wrong password      |

  @Logout
  Scenario: Logout Existing User
    Given I success login as doctor
    When I send logout endpoint
    Then I received HTTP response 200
    And I validate response message

  @refresh
  Scenario: refresh token
    Given I success login as doctor
    When I send refresh endpoint
    Then I received HTTP response 200
    And I validate response