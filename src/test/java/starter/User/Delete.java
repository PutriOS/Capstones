package starter.User;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.Matchers.equalTo;
import static starter.Outpatient.Create.token;

public class Delete {
    protected String baseUrl = "https://go-hospital-server.herokuapp.com/";

    @Step("I set delete endpoint for outpatient")
    public String iSetDeleteUserEndpoint(){
        return baseUrl+"api/user/"+5+"/delete";
    }

    @Step("I send user endpoint")
    public void iSendDeleteUserEndpoint(){
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .when().delete(iSetDeleteUserEndpoint());
    }

    public void validateUserErrorResponseMessage(){
        SerenityRest.then().body("error", equalTo("No Record Found"));
    }
}
