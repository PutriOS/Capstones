package starter.Autentikasi;//package starter.autentikasi;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import static org.hamcrest.Matchers.equalTo;
import static starter.Autentikasi.RefreshToken.tokens;

public class Logout {
    protected String baseUrl = "https://go-hospital-server.herokuapp.com/";

    @Step("I set endpoint for logout user")
    public String iSetEndpointLogoutUser(){
        return baseUrl+"api/logout";
    }

    @Step("I send logout endpoint")
    public void iSendLogoutEndpoint(){
        SerenityRest.given()
                .header("Authorization", "Bearer " + tokens)
                .when().post(iSetEndpointLogoutUser());
    }

    @Step("i received HTTP response {int}")
    public void validateResponseCode(int statusCode){
        Response response = SerenityRest.lastResponse();
        int actual = response.statusCode();
        Assert.assertEquals(statusCode, actual);
    }

    @Step("i validate response message")
    public void validateResponseMessage(){
        SerenityRest.then().body("message", equalTo("User Logged Out"));
    }
}
