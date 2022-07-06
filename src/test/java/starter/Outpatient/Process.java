package starter.Outpatient;

import com.github.javafaker.DateAndTime;
import com.github.javafaker.Faker;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.simple.JSONObject;

import static starter.Autentikasi.RefreshToken.tokens;

public class Process {
    protected String baseUrl = "https://go-hospital-server.herokuapp.com/";
    protected static String name="";
    protected static Integer national_id;
    protected static DateAndTime date;
    protected static String id_patient;


    public JSONObject setReqCreateNewPatient() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("national_id",national_id);
        requestBody.put("full_name",name);
        requestBody.put("address","Megaland");
        requestBody.put("gender","Sakit Kepala");
        requestBody.put("birthdate",date);
        requestBody.put("blood_type","O");
        return requestBody;
    }

    @Step("I create new patient")
    public void iSendEndpointCreateNewPatient(){
        Faker faker = new Faker();
        name = faker.name().username();
        long national_id = (long) (Math.random() * 10000000000000000L);
        date = faker.date();
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + tokens)
                .body(setReqCreateNewPatient().toJSONString()).post(baseUrl+"api/patient");
    }

    public void iSendGetAllEndpoint(){
        SerenityRest.given()
                .header("Authorization", "Bearer " + tokens)
                .when().get(baseUrl+"api/patient");
    }

    public JSONObject setReqCreateNewOutPatient() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("patient_code",id_patient);
        requestBody.put("doctor_code","DCR00001");
        requestBody.put("facility_id",1);
        requestBody.put("complaint","Sakit Lambung");
        requestBody.put("session_id",1);
        requestBody.put("date_check",date);
        return requestBody;
    }
    @Step("I send endpoint for create new outpatient")
    public void iSendEndpointCreateNewOutPatient(){
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + tokens)
                .body(setReqCreateNewOutPatient().toJSONString()).post(baseUrl+"api/outpatient");
    }
}
