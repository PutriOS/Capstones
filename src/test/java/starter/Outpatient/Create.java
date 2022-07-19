package starter.Outpatient;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.simple.JSONObject;
import org.junit.Assert;

import static net.serenitybdd.rest.SerenityRest.then;
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

    public void requestPostCreateOutpatient(String patientCode, String doctorCode, int facilityId, String complaint, int sessionId, String dateCheck) {
        if (patientCode.equalsIgnoreCase("blank")) {
            patientCode = "";
        }

        if (doctorCode.equalsIgnoreCase("blank")) {
            doctorCode = "";
        }

        if (complaint.equalsIgnoreCase("blank")) {
            complaint = "";
        }

        if (dateCheck.equalsIgnoreCase("blank")) {
            dateCheck = "";
        }

        JSONObject requestData = new JSONObject();
        requestData.put("patient_code", patientCode);
        requestData.put("doctor_code", doctorCode);
        requestData.put("facility_id", facilityId);
        requestData.put("complaint", complaint);
        requestData.put("session_id", sessionId);
        requestData.put("date_check", dateCheck);

        SerenityRest.given().header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestData.toJSONString());
        SerenityRest.when().post(baseUrl+"api/outpatient");
    }

    public void validateMessageCreateOutpatient(String response) {
        if (response.equals("patient_code")) {
            then().body("error.patient_code", equalTo("cannot be blank"));
        } else if (response.equals("doctor_code")) {
            then().body("error.patient_code", equalTo("patient RM00009 have 1 unfilled medic record."));
        } else if (response.equals("facility_id")) {
            then().body("error.facility_id", equalTo("cannot be blank"));
        } else if (response.equals("complaint")) {
            then().body("error.patient_code", equalTo("patient RM00009 have 1 unfilled medic record."));
        } else if (response.equals("session_id")) {
            then().body("error.session_id", equalTo("cannot be blank"));
        } else if (response.equals("date_check")) {
            then().body("error.date_check", equalTo("cannot be blank"));
        }
    }
}
