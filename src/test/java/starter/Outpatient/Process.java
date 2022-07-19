package starter.Outpatient;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.simple.JSONObject;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static starter.Autentikasi.RefreshToken.tokens;
import static starter.Outpatient.Create.token;

public class Process {
    protected String baseUrl = "https://go-hospital-server.herokuapp.com/";
    protected static String name="";
    protected static String national_id;
    protected static String patient_code;
    protected static Integer id_Outpatient;
    public static String tokenss = "";

    @Step("I create new patient")
    public void iSendEndpointCreateNewPatient(){
        Faker faker = new Faker();
        name = faker.name().username();

        Long id = (long) (Math.random() * 10000000000000000L);
        national_id = String.valueOf(id);
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(setReqCreateNewPatient().toJSONString()).post(baseUrl+"api/patient");
    }

    public JSONObject setReqCreateNewPatient() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("national_id",national_id);
        requestBody.put("full_name",name);
        requestBody.put("address","Megaland");
        requestBody.put("gender","Female");
        requestBody.put("birthdate","2000-02-22");
        requestBody.put("blood_type","O");
        return requestBody;
    }

    public void iSendGetAllPatientEndpoint(){
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .when().get(baseUrl+"api/patient");
        Response resp = SerenityRest.lastResponse();
        List data =  resp.getBody().jsonPath().get("data");
        int lastIndex = data.size() - 1;
        System.out.println("jumlah : " +data.size());
        patient_code = resp.getBody().jsonPath().get("data[" +lastIndex+"].code");
        System.out.println("patient_code: " + patient_code);
    }

    public JSONObject setReqCreateNewOutPatient() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("patient_code",patient_code);
        requestBody.put("doctor_code","DCR00001");
        requestBody.put("facility_id",1);
        requestBody.put("complaint","Sakit Lambung");
        requestBody.put("session_id",1);
        requestBody.put("date_check","2022-07-07");
        return requestBody;
    }

    @Step("I create new Outpatient")
    public void iSendEndpointCreateNewOutPatient(){
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(setReqCreateNewOutPatient().toJSONString()).post(baseUrl+"api/outpatient");
    }

    public void iSendGetAllOutpatientEndpoint(){
        SerenityRest.given()
                .header("Authorization", "Bearer " + tokens)
                .header("Content-Type", "application/json")
                .when().get(baseUrl+"api/outpatient");
        Response resp = SerenityRest.lastResponse();
        List data =  resp.getBody().jsonPath().get("data");
        int lastIndex = data.size() - 1;
        System.out.println("jumlah : " +data.size());
        id_Outpatient = resp.getBody().jsonPath().get("data[" +lastIndex+"].id");
        System.out.println("id_Outpatient: " + id_Outpatient);
    }

    @Step("I send endpoint for create process doctor")
    public void createProcessDoctor(){
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + tokens)
                .body(setReqCreateProcessDoctor().toJSONString()).post(baseUrl+"api/outpatient/"+ id_Outpatient + "/process");
    }

    public JSONObject setReqCreateProcessDoctor() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("diagnose","Mag");
        requestBody.put("prescription","Antasida");
        return requestBody;
    }

    @Step("i validate response message")
    public void validateResponseMessage(){
        SerenityRest.then().body("message", equalTo("Medic Record Submitted"));
    }

    @Step("I send endpoint for create assign nurse")
    public void createAssignNurse(){
        JSONObject requestBody = new JSONObject();
        requestBody.put("nurse_code","NRS00001");
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + tokens)
                .body(requestBody.toJSONString()).post(baseUrl+"api/outpatient/"+ id_Outpatient + "/assign");
    }

    public JSONObject setReqBodyLoginNurse() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("email","priscillahalim@holyhos.co.id");
        requestBody.put("password", "password");
        return requestBody;
    }

    @Step("I success login as Nurse")
    public void iSuccessloginAsNurse(){
        SerenityRest.given().header("Content-Type", "application/json").body(setReqBodyLoginNurse().toJSONString()).post(baseUrl+"api/login");
        Response resp = SerenityRest.lastResponse();
        tokenss =  resp.getBody().jsonPath().get("jwt.access_token");
    }

    @Step("I send endpoint for create process nurse")
    public void createProcessNurse(){
        JSONObject requestBody = new JSONObject();
        requestBody.put("blood_tension",12);
        requestBody.put("height",121);
        requestBody.put("weight",41);
        requestBody.put("body_temp",31);
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + tokenss)
                .body(requestBody.toJSONString()).post(baseUrl+"api/outpatient/"+ id_Outpatient + "/process");
    }
}
