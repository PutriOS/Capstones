package starter.Outpatient;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.Matchers.equalTo;
import static starter.Autentikasi.RefreshToken.tokens;

public class LogReport {
    protected String baseUrl = "https://go-hospital-server.herokuapp.com/";

    @Step("I set endpoint for logReport outpatient")
    public String iSetEndpointLogReportOutPatient(){
        return baseUrl+"api/outpatient/log";
    }

    @Step("I send logout endpoint")
    public void iSendOutPatientEndpoint(){
        SerenityRest.given()
                .header("Authorization", "Bearer " + tokens)
                .when().get(iSetEndpointLogReportOutPatient());
    }

    @Step("i validate response message")
    public void validateResponseMessage(){
        SerenityRest.then().body("data[0].serial_number", equalTo("RM/421/2022/00001"));
    }
}



