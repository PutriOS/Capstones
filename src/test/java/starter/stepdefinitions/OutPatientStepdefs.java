package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.Outpatient.*;

public class OutPatientStepdefs {
    @Steps
    Create create;

    @Steps
    ListGetID getID;

    @Steps
    Report report;

    @Steps
    LogReport logReport;

    @Steps
    ListAll listAll;

    //Create
    @Given("I success login as admin")
    public void iSuccessLoginAsAdmin() {
        create.iSuccessloginAsAdmin();
    }

    @When("I send {string}, and {string}, and {int}, and {string}, and {int} and {string}")
    public void iSendAndAndFacility_idAndAndSession_idAnd(String patientCode, String doctorCode, int facilityId, String complaint, int sessionId, String dateCheck) {
        create.requestPostCreateOutpatient(patientCode,doctorCode,facilityId,complaint,sessionId,dateCheck);
    }

    @And("I validate response {string} message create Outpatient")
    public void iValidateResponseMessageCreateOutpatient(String responses) {
        create.validateMessageCreateOutpatient(responses);
    }

    @When("I send get ID endpoint")
    public void iSendGetIDEndpoint() {
        getID.iSendOutPatientEndpoint();
    }

    //Get Report
    @When("I send request get report endpoint")
    public void iSendRequestGetReportEndpoint() {
        report.iSendReportOutPatientEndpoint();
    }

    //Get LogReport
    @When("I send request get logReport endpoint")
    public void iSendRequestGetLogReportEndpoint() {
        logReport.iSendOutPatientEndpoint();
    }

    @When("I send request get list All endpoint")
    public void iSendRequestGetListAllEndpoint() {
        listAll.iSendListAllOutPatientEndpoint();
    }
}
