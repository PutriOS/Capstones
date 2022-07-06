package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.User.Create;
import starter.User.GetAll;
import starter.User.ListByID;
import starter.User.Update;

public class UserStepdefs {
    @Steps
    Create create;

    @Steps
    GetAll getAll;

    @Steps
    ListByID listByID;

    @Steps
    Update update;


    //CREATE
    @When("I send {string}, and {int}, and {string}, and {string}, and {int}")
    public void iSendAndFacility_idAndAndAndRole_id(String email, int facility_id, String full_name, String gender, int role_id) {
        create.requestPostCreateUser(email,facility_id,full_name,gender,role_id);
    }

    @Then("I received HTTP response <int>")
    public void iReceivedHTTPResponseCode(int Code) {
        create.verifyStatusCode(Code);
    }

    @And("I validate response {string} message create user")
    public void iValidateResponseMessageCreateUser(String response) {
        create.validateMessageCreateUser(response);
    }

    //GetAll
    @When("I send request get user endpoint")
    public void iSendRequestGetUserEndpoint() {
        getAll.iSendUserEndpoint();
    }

    @And("I validate user data")
    public void iValidateUserData() {
        getAll.validateUserResponseMessage();
    }

    //GetUserID
    @When("I send request get id user endpoint")
    public void iSendRequestGetIdUserEndpoint() {
        listByID.iSendGetIDUserEndpoint();
    }

    @And("I validate get id user data")
    public void iValidateGetIdUserData() {
        listByID.validateUserResponseMessage();
    }

    @And("I success create user")
    public void iSuccessCreateUser() {
        update.CreatedUser();
    }

    @When("I send request update create data user")
    public void iSendRequestUpdateCreateDataUser() {
        update.UpdateUser();
    }

    @And("I validate response message update user")
    public void iValidateResponseMessageUpdateUser() {
        update.validateUpdateResponseMessage();
    }

//    //Update
//    @When("I send request update create data user")
//    public void iSendRequestUpdateCreateDataUser() {
//        updated.UpdateUser();
//    }
//
//    @And("I validate response message update user")
//    public void iValidateResponseMessageUpdateUser() {
//        updated.validateUpdateResponseMessage();
//    }

//    //Delete
//    @When("I send request delete data user")
//    public void iSendRequestDeleteDataUser() {
//        delete.iSendDeleteUserEndpoint();
//    }
//
//    @And("I validate response message delete user")
//    public void iValidateResponseMessageDeleteUser() {
//        delete.validateUserResponseMessage();
//    }

}
