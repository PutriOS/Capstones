package starter.User;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.Matchers.equalTo;
import static starter.Autentikasi.RefreshToken.tokens;
import static starter.Outpatient.Create.token;

public class ListByFacility {
    protected String baseUrl = "https://go-hospital-server.herokuapp.com/";


    @Step("I send get id user endpoint")
    public void iSendGetListByFacilityEndpoint(){
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .when().get(baseUrl+"api/user?facility_id=" + 1);
    }

    @Step("i validate user response message")
    public void validateUserResponseMessage(){
        SerenityRest.then().body("data[0].email", equalTo("alsyadahmad@holyhos.co.id"));
    }
}
