package starter.User;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.Matchers.equalTo;
import static starter.Outpatient.Create.token;

public class ListByRole {
    protected String baseUrl = "https://go-hospital-server.herokuapp.com/";
    @Step("I set get list by facility endpoint")
    public String iSetUserFacilityEndpoint(){
        return baseUrl+"api/user?role_id="+ 3;
    }

    @Step("I send get id user endpoint")
    public void iSendGetListByFacilityEndpoint(){
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .when().get(iSetUserFacilityEndpoint());
    }

    @Step("i validate user response message")
    public void validateUserResponseMessage(){
        SerenityRest.then().body("data[0].email", equalTo("priscillahalim@holyhos.co.id"));
    }
}
