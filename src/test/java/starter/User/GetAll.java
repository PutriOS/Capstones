package starter.User;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.Matchers.equalTo;
import static starter.Outpatient.Create.token;

public class GetAll {
    protected String baseUrl = "https://go-hospital-server.herokuapp.com/";

    @Step("I set endpoint for user")
    public String iSetUserEndpoint(){
        return baseUrl+"api/user";
    }

    @Step("I send user endpoint")
    public void iSendUserEndpoint(){
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .when().get(iSetUserEndpoint());
    }

    @Step("i validate user response message")
    public void validateUserResponseMessage(){
        SerenityRest.then().body("data[0].email", equalTo("rimurutempest@holyhos.co.id"));
        SerenityRest.then().body("data[0].full_name", equalTo("Rimuru Tempest"));
    }
}
