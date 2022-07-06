package starter.Autentikasi;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.simple.JSONObject;

import static org.hamcrest.Matchers.equalTo;

public class RefreshToken {
    protected String baseUrl = "https://go-hospital-server.herokuapp.com/";
    public static String tokens = "";
    protected static String refreshTokens = "";

    public JSONObject setReqBodyLoginUser() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("email","alsyadahmad@holyhos.co.id");
        requestBody.put("password", "password");
        return requestBody;
    }

    @Step("I success login as doctor")
    public void iSuccessloginAsDoctor(){
        //create token for new user
        SerenityRest.given().header("Content-Type", "application/json").body(setReqBodyLoginUser().toJSONString()).post(baseUrl+"api/login");
        Response resp = SerenityRest.lastResponse();
        tokens =  resp.getBody().jsonPath().get("jwt.access_token");
        refreshTokens = resp.getBody().jsonPath().get("jwt.refresh_token");
    }

    @Step("I set endpoint for refresh user")
    public String iSetEndpointRefreshToken(){
        return baseUrl + "api/refresh_token?token=" + tokens;
    }

    @Step("I send refresh endpoint")
    public void iRefreshTokenEndpoint(){
        iSuccessloginAsDoctor();
        SerenityRest.given()
                .header("Authorization", "Bearer " + refreshTokens)
                .when().post(iSetEndpointRefreshToken());
    }

    @Step("i validate response")
    public void validateResponse(){
        SerenityRest.then().body("message", equalTo("New Token Generated"));
    }
}
