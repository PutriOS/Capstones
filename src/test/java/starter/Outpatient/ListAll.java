package starter.Outpatient;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.Matchers.equalTo;
import static starter.Autentikasi.RefreshToken.tokens;

public class ListAll {
    protected String baseUrl = "https://go-hospital-server.herokuapp.com/";

    @Step("I set endpoint for list all outpatient")
    public String iSetEndpointListAllOutPatient(){
        return baseUrl+"api/outpatient";
    }

    @Step("I send list all out patient endpoint")

    public void iSendListAllOutPatientEndpoint(){
        SerenityRest.given()
                .header("Authorization", "Bearer " + tokens)
                .header("Content-Type", "application/json")
                .when().get(iSetEndpointListAllOutPatient());
    }
}
