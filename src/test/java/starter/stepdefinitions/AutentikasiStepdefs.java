package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.Autentikasi.Login;
import starter.Autentikasi.Logout;
import starter.Autentikasi.RefreshToken;

public class AutentikasiStepdefs {
    @Steps
    Login login;

    @Steps
    Logout logout;

    @Steps
    RefreshToken refreshToken;

    @Given("I set an endpoint for login")
    public void iSetAnEndpointForLogin() {
        login.endpointLogin();
    }

    @When("I request POST for login with {string} and {string}")
    public void iRequestPOSTForLoginWithAnd(String email, String password) {
        login.requestPostLogin(email,password);
    }

    @Then("I validate the status code {int}")
    public void iValidateTheStatusCodeStatus_code(int status_code) {
        login.verifyStatusCode(status_code);
    }

    @And("I validate the {string} response message")
    public void iValidateTheResponseMessage(String hasil) {
        if (hasil.equals("login")){
            login.validateResponseMessage();
        }else {
            login.validateErrorMessageLogin(hasil);
        }
    }

    @When("I send logout endpoint")
    public void adminSendLogoutEndpoint() {
        logout.iSendLogoutEndpoint();
    }

    @Then("I received HTTP response {int}")
    public void adminReceivedHTTPResponse(int statusCode) {
        logout.validateResponseCode(statusCode);
    }

    @And("I validate response message")
    public void adminValidateMessage() {
        logout.validateResponseMessage();
    }

    @Given("I success login as doctor")
    public void iSuccessLoginAsDoctor() {
        refreshToken.iSuccessloginAsDoctor();
    }

    @When("I send refresh endpoint")
    public void iSendRefreshEndpoint() {
        refreshToken.iRefreshTokenEndpoint();
    }

    @And("I validate response")
    public void iValidateResponse() {
        refreshToken.validateResponse();
    }
}
