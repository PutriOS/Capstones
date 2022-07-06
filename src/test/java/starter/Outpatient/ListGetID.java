package starter.Outpatient;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.Matchers.equalTo;
import static starter.Outpatient.Create.token;

public class ListGetID {
    protected String baseUrl = "https://go-hospital-server.herokuapp.com/";

    @Step("I set endpoint for outpatient")
    public String iSetEndpointGetIdOutPatient(){
        return baseUrl+"api/outpatient/" + 1;
    }

    @Step("I send logout endpoint")
    public void iSendOutPatientEndpoint(){
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .when().get(iSetEndpointGetIdOutPatient());
    }

    @Step("i validate response message")
    public void validateResponseMessage(){
        SerenityRest.then().body("data[0].full_name", equalTo("Faizur Ramadhan"));
    }
}
