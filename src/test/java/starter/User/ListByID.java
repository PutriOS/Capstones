package starter.User;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.Matchers.equalTo;
import static starter.Outpatient.Create.token;

public class ListByID {
    protected String baseUrl = "https://go-hospital-server.herokuapp.com/";
    @Step("I set get id user endpoint for user")
    public String iSetUserEndpoint(){
        return baseUrl+"api/user/" + 1;
    }

    @Step("I send get id user endpoint")
    public void iSendGetIDUserEndpoint(){
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .when().get(iSetUserEndpoint());
    }

    @Step("i validate user response message")
    public void validateUserResponseMessage(){
        SerenityRest.then().body("data.email", equalTo("rimurutempest@holyhos.co.id"));
        SerenityRest.then().body("data.full_name", equalTo("Rimuru Tempest"));
    }
}
