package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.Outpatient.Process;

public class ProcessSteps {

    @Steps
    Process process;

    @And("I create new patient")
    public void iCreateNewPatient() {
        process.iSendEndpointCreateNewPatient();
    }

    @And("I Get List All patient")
    public void iGetListAllPatient() {
        process.iSendGetAllPatientEndpoint();
    }

    @And("I create new outpatient")
    public void iCreateNewOutpatient() {
        process.iSendEndpointCreateNewOutPatient();
    }

    @And("I Get List All Outpatient")
    public void iGetListAllOutpatient() {
        process.iSendGetAllOutpatientEndpoint();
    }

    @When("I Create Process Doctor")
    public void iCreateProcessDoctor() {
        process.createProcessDoctor();
    }

    @Then("I see response message process doctor")
    public void iSeeResponseMessageProcessDoctor() {
        process.validateResponseMessage();
    }


    @When("I assign nurse")
    public void iAssignNurse() {
        process.createAssignNurse();
    }

    @And("I success login as nurse")
    public void iSuccessLoginAsNurse() {
        process.iSuccessloginAsNurse();
    }

    @And("I Create Process nurse")
    public void iCreateProcessNurse() {
        process.createProcessNurse();
    }
}
