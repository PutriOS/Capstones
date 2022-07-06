package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.Outpatient.Create;
import starter.Outpatient.ListGetID;
import starter.Outpatient.LogReport;
import starter.Outpatient.Report;

public class OutPatientStepdefs {
    @Steps
    Create create;

    @Steps
    ListGetID getID;

    @Steps
    Report report;

    @Steps
    LogReport logReport;

    @Given("I success login as admin")
    public void iSuccessLoginAsAdmin() {
        create.iSuccessloginAsAdmin();
    }

    @When("I send request create data outpatient endpoint")
    public void iSendRequestCreateDataOutPatientEndpoint() {
        create.iSendEndpointCreateOutPatient();
    }

    @And("I validate response message create Outpatient")
    public void iValidateResponseMessageCreateOutpatient() {
        create.validateResponseMessageCreateOutpatient();
    }

    @When("I send get ID endpoint")
    public void iSendGetIDEndpoint() {
        getID.iSendOutPatientEndpoint();
    }

    @And("I validate data OutPatient")
    public void iValidateDataOutPatient() {
        getID.validateResponseMessage();
    }

    //Get Report
    @When("I send request get report endpoint")
    public void iSendRequestGetReportEndpoint() {
        report.iSendReportOutPatientEndpoint();
    }

    @And("I validate report OutPatient data")
    public void iValidateReportOutPatientData() {
        report.validateResponseMessage();
    }

    //Get LogReport
    @When("I send request get logReport endpoint")
    public void iSendRequestGetLogReportEndpoint() {
        logReport.iSendOutPatientEndpoint();
    }

    @And("I validate logreport OutPatient data")
    public void iValidateLogreportOutPatientData() {
        logReport.validateResponseMessage();
    }

//    @When("I send request {int} and {int} and {int} and {int} and {string}")
//    public void iSendRequestPatient_idAndMedical_staffAndMedical_facility_idAndSession_idAnd( int patient_id, int medical_staff, int medical_facility_id, int session_id, String date) {
//    }

}
