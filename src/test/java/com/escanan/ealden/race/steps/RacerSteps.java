package com.escanan.ealden.race.steps;

import com.escanan.ealden.race.page.RacePage;
import io.cucumber.java.After;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class RacerSteps {
    private RacePage page;

    @After
    public void tearDown() {
        page.close();
    }

    @Given("I am in a race")
    public void newRace() {
        page = new RacePage(true);
        page.load();
    }

    @Given("I am at position {int}")
    public void setPosition(int position) {
        throw new PendingException();
    }

    @Given("I have damage of {int}")
    public void setDamage(int damage) {
        throw new PendingException();
    }

    @Given("I see the finish line at position {int}")
    public void setFinishLine(int finishLine) {
        throw new PendingException();
    }

    @When("I choose {string} speed")
    public void speed(String speed) {
        throw new PendingException();
    }

    @When("I roll a {int}")
    public void roll(int roll) {
        throw new PendingException();
    }

    @When("I choose to start over in a new race")
    public void createNewRace() {
        throw new PendingException();
    }

    @When("all racers have crashed!")
    public void racersCrashed() {
        throw new PendingException();
    }

    @Then("I must now be at position {int}")
    public void assertNewPosition(int newPosition) {
        throw new PendingException();
    }

    @Then("I must now have damage of {int}")
    public void assertNewDamage(int newDamage) {
        throw new PendingException();
    }

    @Then("I must see the race result: WIN")
    public void assertRacerWins() {
        throw new PendingException();
    }

    @Then("I must see the race result: CRASHED")
    public void assertRacerCrashed() {
        throw new PendingException();
    }

    @Then("I must now have a log entry with the following:")
    public void assertRollWithRacerLogged() {
        throw new PendingException();
    }

    @Then("Position: {int}")
    public void assertRollWithPositionLogged(int position) {
        throw new PendingException();
    }

    @Then("Damage: {int}")
    public void assertRollWithDamageLogged(int damage) {
        throw new PendingException();
    }

    @Then("Speed: {string}")
    public void assertRollWithSpeedTypeLogged(String speedType) {
        throw new PendingException();
    }

    @Then("Roll: {int}")
    public void assertRollWithRollLogged(int roll) {
        throw new PendingException();
    }

    @Then("Move: {int}")
    public void assertRollWithMoveLogged(int move) {
        throw new PendingException();
    }

    @Then("New Position: {int}")
    public void assertRollWithNewPositionLogged(int newPosition) {
        throw new PendingException();
    }

    @Then("New Damage: {int}")
    public void assertRollWithNewDamageLogged(int newDamage) {
        throw new PendingException();
    }

    @Then("Crashed: {string}")
    public void assertRollWithCrashedLogged(String crashed) {
        throw new PendingException();
    }

    @Then("Win: {string}")
    public void assertRollWithWinLogged(String win) {
        throw new PendingException();
    }

    @Then("I must see the message: {string}")
    public void assertMessage(String message) {
        assertThat(page.getMessage(), is(equalTo(message)));
    }

    @Then("our race must be over!")
    public void assertRacersCrashedAndBurned() {
        throw new PendingException();
    }

    @Then("I must see the race result: --")
    public void assertNoRaceResult() {
        throw new PendingException();
    }
}
