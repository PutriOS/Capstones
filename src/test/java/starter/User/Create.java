package starter.User;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.json.simple.JSONObject;
import org.junit.Assert;

import static net.serenitybdd.rest.SerenityRest.then;
import static org.hamcrest.Matchers.equalTo;
import static starter.Outpatient.Create.token;

public class Create {
    String base_url = "https://go-hospital-server.herokuapp.com/";

    public String endpointUser() {
        return base_url + "api/user";
    }

    public void requestPostCreateUser(String email, int facility_id, String full_name, String gender, int role_id) {

        if (email.equalsIgnoreCase("emails") && full_name.equalsIgnoreCase("fullnames")) {
            Faker faker = new Faker();
            String name = faker.name().username();
            full_name = name;
            email = name + "@holyhos.co.id";
        }

        if (email.equalsIgnoreCase("blank")) {
            email = "";
        }
        if (full_name.equalsIgnoreCase("blank")) {
            full_name = "";
        }
        if (gender.equalsIgnoreCase("blank")) {
            gender = "";
        }
        JSONObject requestData = new JSONObject();
        requestData.put("email", email);
        requestData.put("facility_id", facility_id);
        requestData.put("full_name", full_name);
        requestData.put("gender", gender);
        requestData.put("role_id", role_id);

        SerenityRest.given().header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestData.toJSONString());
        SerenityRest.when().post(endpointUser());
    }

    public void verifyStatusCode(int status_code) {
        Response response = SerenityRest.lastResponse();
        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode, status_code);
    }

    public void validateMessageCreateUser(String response) {
        if (response.equals("email null")) {
            then().body("error.email", equalTo("cannot be blank"));
        } else if (response.equals("facility_id null")) {
            then().body("error.facility_id", equalTo("cannot be blank"));
        } else if (response.equals("full_name null")) {
            then().body("error.full_name", equalTo("cannot be blank"));
        } else if (response.equals("gender null")) {
            then().body("error.gender", equalTo("cannot be blank"));
        } else if (response.equals("role_id null")) {
            then().body("error.role_id", equalTo("cannot be blank"));
        } else if (response.equals("created")) {
            then().body("message", equalTo("User Created"));
        }
    }
}
