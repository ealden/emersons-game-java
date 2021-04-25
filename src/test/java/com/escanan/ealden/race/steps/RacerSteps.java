package com.escanan.ealden.race.steps;

import com.escanan.ealden.race.EmersonsGame;
import com.escanan.ealden.race.model.Race;
import com.escanan.ealden.race.model.Racer;
import com.escanan.ealden.race.model.SpeedType;
import com.escanan.ealden.race.page.RacePage;
import com.escanan.ealden.race.service.RaceService;
import com.escanan.ealden.race.service.impl.RaceServiceImpl;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.escanan.ealden.race.model.Racer.MAX_DAMAGE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class RacerSteps {
    private static final String YES = "YES";

    private static EmersonsGame application;

    private RacePage page;
    private RaceService raceService;

    private Race currentRace;

    private SpeedType speedType;

    @Before
    public void setUp() {
        if (application == null) {
            application = new EmersonsGame();
            application.start();
        }
    }

    @After
    public void tearDown() {
        page.close();
    }

    @Given("I am in a race")
    public void newRace() {
        raceService = RaceServiceImpl.INSTANCE;
        currentRace = raceService.newRace();

        page = new RacePage(true);
        page.load();
    }

    @Given("I am at position {int}")
    public void setPosition(int position) {
        currentRace.getCurrentRacer().setPosition(position);
    }

    @Given("I have damage of {int}")
    public void setDamage(int damage) {
        currentRace.getCurrentRacer().setDamage(damage);
    }

    @Given("I see the finish line at position {int}")
    public void setFinishLine(int finishLine) {

    }

    @When("I choose {string} speed")
    public void speed(String speed) {
        speedType = SpeedType.valueOf(speed);
    }

    @When("I roll a {int}")
    public void roll(int roll) {
        page.roll(roll, speedType);
    }

    @When("I choose to start over in a new race")
    public void createNewRace() {
        page.newRace();

        currentRace = raceService.getCurrentRace();
    }

    @When("all racers have crashed!")
    public void racersCrashed() {
        page.roll(1, SpeedType.NORMAL);

        crashAllRacers();

        page.load();
    }

    private void crashAllRacers() {
        Race race = raceService.getCurrentRace();

        for (Racer racer : race.getRacers()) {
            racer.setDamage(MAX_DAMAGE);
        }

        raceService.save(race);
    }

    @Then("I must now be at position {int}")
    public void assertNewPosition(int newPosition) {
        assertThat(page.getPositionOf(currentRace.getCurrentRacer()), is(equalTo(newPosition)));
    }

    @Then("I must now have damage of {int}")
    public void assertNewDamage(int newDamage) {
        assertThat(page.getDamageOf(currentRace.getCurrentRacer()), is(equalTo(newDamage)));
    }

    @Then("I must see the race result: WIN")
    public void assertRacerWins() {
        assertThat(page.isOver(), is(true));
    }

    @Then("I must see the race result: CRASHED")
    public void assertRacerCrashed() {
        throw new PendingException();
    }

    @Then("I must now have a log entry with the following:")
    public void assertRollWithRacerLogged() {
        assertThat(currentRace.getLastRoll().getRacer(), is(equalTo(currentRace.getCurrentRacer())));
    }

    @Then("Position: {int}")
    public void assertRollWithPositionLogged(int position) {
        assertThat(currentRace.getLastRoll().getPosition(), is(equalTo(position)));
    }

    @Then("Damage: {int}")
    public void assertRollWithDamageLogged(int damage) {
        assertThat(currentRace.getLastRoll().getDamage(), is(equalTo(damage)));
    }

    @Then("Speed: {string}")
    public void assertRollWithSpeedTypeLogged(String speedType) {
        assertThat(currentRace.getLastRoll().getSpeedType(), is(equalTo(SpeedType.valueOf(speedType))));
    }

    @Then("Roll: {int}")
    public void assertRollWithRollLogged(int roll) {
        assertThat(currentRace.getLastRoll().getNumber(), is(equalTo(roll)));
    }

    @Then("Move: {int}")
    public void assertRollWithMoveLogged(int move) {
        assertThat(currentRace.getLastRoll().getMove(), is(equalTo(move)));
    }

    @Then("New Position: {int}")
    public void assertRollWithNewPositionLogged(int newPosition) {
        assertThat(currentRace.getLastRoll().getNewPosition(), is(equalTo(newPosition)));
    }

    @Then("New Damage: {int}")
    public void assertRollWithNewDamageLogged(int newDamage) {
        assertThat(currentRace.getLastRoll().getNewDamage(), is(equalTo(newDamage)));
    }

    @Then("Crashed: {string}")
    public void assertRollWithCrashedLogged(String crashed) {
        boolean didCrash = YES.equals(crashed);

        assertThat(currentRace.getLastRoll().isCrashed(), is(equalTo(didCrash)));
    }

    @Then("Win: {string}")
    public void assertRollWithWinLogged(String win) {
        boolean didWin = YES.equals(win);

        assertThat(currentRace.getLastRoll().isWin(), is(equalTo(didWin)));
    }

    @Then("I must see the message: {string}")
    public void assertMessage(String message) {
        assertThat(page.getMessage(), is(equalTo(message)));
    }

    @Then("our race must be over!")
    public void assertRacersCrashedAndBurned() {
        assertThat(page.isOver(), is(true));
    }

    @Then("I must see the race result: --")
    public void assertNoRaceResult() {
        assertThat(page.isOver(), is(false));
    }
}
