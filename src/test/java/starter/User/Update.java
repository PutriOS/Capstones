package starter.User;

import com.github.javafaker.Faker;
import net.serenitybdd.rest.SerenityRest;
import org.json.simple.JSONObject;

import static org.hamcrest.Matchers.equalTo;
import static starter.Outpatient.Create.token;

public class Update {
    protected String baseUrl = "https://go-hospital-server.herokuapp.com";
    protected static String name="";
    protected static String email="";

    public void CreatedUser(){
        Faker faker = new Faker();
        name = faker.name().username();
        email = name + "@holyhos.co.id";
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(setCreatedDataUser().toJSONString()).post(baseUrl+"/api/user/");
    }

    public JSONObject setCreatedDataUser() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", email);
        requestBody.put("facility_id", 2);
        requestBody.put("full_name", name);
        requestBody.put("gender","Male");
        requestBody.put("role_id", 2);
        return requestBody;
    }

    public void UpdateUser(){
        Faker faker = new Faker();
        name = faker.name().username();
        email = name + "@holyhos.co.id";
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(setUpdateDataUser().toJSONString()).put(baseUrl+"/api/user/" + 4 + "/update");
    }

    public JSONObject setUpdateDataUser() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", email);
        requestBody.put("facility_id", 2);
        requestBody.put("full_name", name);
        requestBody.put("gender","Male");
        requestBody.put("role_id", 2);
        return requestBody;
    }

    public void validateUpdateResponseMessage(){
        SerenityRest.then().body("message", equalTo("User #4 Updated"));
    }
}
