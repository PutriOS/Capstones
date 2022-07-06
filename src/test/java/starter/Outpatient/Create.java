package starter.Outpatient;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.simple.JSONObject;

import static org.hamcrest.Matchers.equalTo;

public class Create {
    protected String baseUrl = "https://go-hospital-server.herokuapp.com/";
    public static String token = "";

    public JSONObject setReqBodyLoginUser() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("email","rimurutempest@holyhos.co.id");
        requestBody.put("password", "password");
        return requestBody;
    }

    @Step("I success login as admin")
    public void iSuccessloginAsAdmin(){
        SerenityRest.given().header("Content-Type", "application/json").body(setReqBodyLoginUser().toJSONString()).post(baseUrl+"api/login");
        Response resp = SerenityRest.lastResponse();
        token =  resp.getBody().jsonPath().get("jwt.access_token");
    }

    public JSONObject setReqCreateOutPatient() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("patient_id",1);
        requestBody.put("medical_staff_id",1);
        requestBody.put("medical_facility_id",1);
        requestBody.put("complaint","Sakit Kepala");
        requestBody.put("session_id",1);
        requestBody.put("date_check","2022-06-24");
        return requestBody;
    }
    @Step("I send endpoint for create outpatient")
    public void iSendEndpointCreateOutPatient(){
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(setReqCreateOutPatient().toJSONString()).post(baseUrl+"api/outpatient");
    }

    @Step("validateResponseMessageCreateOutpatient")
    public void validateResponseMessageCreateOutpatient(){
        SerenityRest.then().body("message", equalTo("Medic Record Created"));
    }
}
