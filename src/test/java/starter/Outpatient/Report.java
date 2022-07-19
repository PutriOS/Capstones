package starter.Outpatient;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.Matchers.equalTo;
import static starter.Outpatient.Create.token;

public class Report {
    protected String baseUrl = "https://go-hospital-server.herokuapp.com/";

    @Step("I set endpoint for outpatient")
    public String iSetEndpointReportOutPatient(){
        return baseUrl+"api/outpatient/report";
    }

    @Step("I send logout endpoint")
    public void iSendReportOutPatientEndpoint(){
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .when().get(iSetEndpointReportOutPatient());
    }
}
