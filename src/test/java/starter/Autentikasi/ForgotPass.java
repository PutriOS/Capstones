package starter.Autentikasi;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.simple.JSONObject;

import static org.hamcrest.Matchers.equalTo;
import static starter.Outpatient.Create.token;

public class ForgotPass {
    protected String baseUrl = "https://go-hospital-server.herokuapp.com/";
    protected static String accessTokens = "";

    public JSONObject setReqFindEmail() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "alsyadahmad@holyhos.co.id");
        return requestBody;
    }

    @Step("I get find email")
    public void iGetFindEmail(){
        SerenityRest.given().header("Content-Type", "application/json")
                .body(setReqFindEmail()
                .toJSONString()).post(baseUrl+"api/find_email");
        Response resp = SerenityRest.lastResponse();
        accessTokens =  resp.getBody().jsonPath().get("jwt.access_token");
    }

    public JSONObject setReqchangePassword() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("password", "password");
        return requestBody;
    }


    @Step("I send forgot email endpoint")
    public void iSendForgotPasswordEndpoint(){
        SerenityRest.given().header("Content-Type", "application/json")
                .body(setReqchangePassword()
                .toJSONString()).post(baseUrl+"api/forgot_password?token="+ accessTokens);
    }

    @Step("i validate response")
    public void validateResponse(){
        SerenityRest.then().body("message", equalTo("Password Changed"));
    }
}
