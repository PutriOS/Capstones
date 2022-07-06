package starter.Autentikasi;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.simple.JSONObject;
import org.junit.Assert;

import static net.serenitybdd.rest.SerenityRest.then;
import static org.hamcrest.Matchers.equalTo;

public class Login {
    String base_url = "https://go-hospital-server.herokuapp.com/";

    public String endpointLogin() {
        return base_url + "api/login";
    }

    public void requestPostLogin(String email, String password) {
        JSONObject requestData = new JSONObject();
        requestData.put("email", email);
        requestData.put("password", password);

        SerenityRest.given().header("Content-Type", "application/json")
                .body(requestData.toJSONString());
        SerenityRest.when().post(endpointLogin());
    }

    public void verifyStatusCode(int status_code) {
        Response response = SerenityRest.lastResponse();
        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode, status_code);
    }

    public void validateErrorMessageLogin(String hasil) {
        if (hasil.equals("password null")) {
            then().body("error.password", equalTo("cannot be blank"));
        } else if (hasil.equals("email null")) {
            then().body("error.email", equalTo("cannot be blank"));
        } else if (hasil.equals("email and password null")) {
            then().body("error.email", equalTo("cannot be blank"));
            then().body("error.password", equalTo("cannot be blank"));
        } else if (hasil.equals("invalid length password")) {
            then().body("error.password", equalTo("the length must be no less than 8"));
        } else if (hasil.equals("wrong email")) {
            then().body("error", equalTo("No Record Found"));
            then().body("message", equalTo("Failed to User Log In"));
        } else if (hasil.equals("wrong password")) {
            then().body("error", equalTo("Wrong Password"));
            then().body("message", equalTo("Failed to User Log In"));
        }
    }

    @Step("i validate response message")
    public void validateResponseMessage(){
        SerenityRest.then().body("message", equalTo("User Logged In"));
    }
}
