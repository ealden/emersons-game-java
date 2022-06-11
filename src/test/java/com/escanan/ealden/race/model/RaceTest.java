package com.escanan.ealden.race.model;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.escanan.ealden.race.model.Racer.MAX_DAMAGE;
import static com.escanan.ealden.race.model.Racer.NO_DAMAGE;
import static com.escanan.ealden.race.model.SpeedType.NORMAL;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class RaceTest {
    @Test
    void currentRacerMustBeNullIfNoRaceHasNoRacers() {
        Race race = new Race();

        assertThat(race.getCurrentRacer(), nullValue());
    }

    @Test
    void addRacerMustSetCurrentRacerToRacerAdded() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        assertThat(race.getCurrentRacer(), sameInstance(racer));
    }

    @Test
    void addRacerMustSetRaceToSameRace() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        assertThat(racer.getRace(), sameInstance(race));
    }

    @Test
    void addRacerMustSetRankToRacersCount() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        assertThat(racer.getRank(), equalTo(1));
    }

    @Test
    void addRacerMustOnlySetFirstRacerAsCurrentRacer() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        race.addRacer(new Racer());

        assertThat(race.getCurrentRacer(), sameInstance(racer));
    }

    @Test
    void asJson() {
        Map<String, Object> expectedRacer = new HashMap<>();
        expectedRacer.put("id", 1L);
        expectedRacer.put("name", "Alice");
        expectedRacer.put("position", 0);
        expectedRacer.put("damage", 0);
        expectedRacer.put("rank", 1);
        expectedRacer.put("crashed", false);
        expectedRacer.put("damaged", false);
        expectedRacer.put("winner", false);

        Map<String, Object> expectedRace = new HashMap<>();
        expectedRace.put("id", 1L);
        expectedRace.put("racers", singletonList(expectedRacer));
        expectedRace.put("currentRacer", expectedRacer);
        expectedRace.put("finishLine", 10);
        expectedRace.put("over", false);
        expectedRace.put("allCrashed", false);
        expectedRace.put("message", "Time to RACE!  Alice rolls first!");

        Race race = new Race();
        race.setId(1L);
        race.addRacer(new Racer("Alice"));

        assertThat(race.asJson(), equalTo(expectedRace));
    }

    @Test
    void asJsonWhenNoRacersJoined() {
        Map<String, Object> expected = new HashMap<>();
        expected.put("id", 1L);
        expected.put("racers", Collections.emptyList());
        expected.put("currentRacer", null);
        expected.put("finishLine", 10);
        expected.put("over", false);
        expected.put("allCrashed", false);
        expected.put("message", null);

        Race race = new Race();
        race.setId(1L);

        assertThat(race.asJson(), equalTo(expected));
    }

    @Test
    void isOverMustReturnFalseIfNoRacersHaveWon() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        Racer racer2 = new Racer();
        race.addRacer(racer2);

        Racer racer3 = new Racer();
        race.addRacer(racer3);

        assertThat(race.isOver(), equalTo(false));
    }

    @Test
    void isOverMustReturnTrueIfARacerHasWon() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        Racer racer2 = new Racer();
        race.addRacer(racer2);

        Racer racer3 = new Racer();
        racer3.setPosition(race.getFinishLine());
        race.addRacer(racer3);

        assertThat(race.isOver(), equalTo(true));
    }

    @Test
    void rollMustCreateNewRollEntry() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        race.roll(1, NORMAL);

        assertThat(race.getLastRoll(), notNullValue());
        assertThat(race.getLastRoll().getRacer(), sameInstance(racer));
    }

    @Test
    void rollMustSetCurrentRacerToNextRacer() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        Racer racer2 = new Racer();
        race.addRacer(racer2);

        race.roll(1, NORMAL);

        assertThat(race.getCurrentRacer(), sameInstance(racer2));
    }

    @Test
    void rollWithNoNumberParameterMustRandomlyRollCurrentRacer() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        race.roll(NORMAL);

        assertThat(race.getLastRoll().getRacer().getPosition(), greaterThan(0));
    }

    @Test
    void isAllCrashedMustReturnTrueIfAllRacersCrashed() {
        Race race = new Race();

        Racer racer = new Racer();
        racer.setDamage(MAX_DAMAGE);
        race.addRacer(racer);

        Racer racer2 = new Racer();
        racer2.setDamage(MAX_DAMAGE);
        race.addRacer(racer2);

        Racer racer3 = new Racer();
        racer3.setDamage(MAX_DAMAGE);
        race.addRacer(racer3);

        assertThat(race.isAllCrashed(), equalTo(true));
    }

    @Test
    void isAllCrashedMustReturnFalseIfNotAllRacersCrashed() {
        Race race = new Race();

        Racer racer = new Racer();
        racer.setDamage(MAX_DAMAGE);
        race.addRacer(racer);

        Racer racer2 = new Racer();
        racer2.setDamage(MAX_DAMAGE);
        race.addRacer(racer2);

        Racer racer3 = new Racer();
        racer3.setDamage(NO_DAMAGE);
        race.addRacer(racer3);

        assertThat(race.isAllCrashed(), equalTo(false));
    }

    @Test
    void isAllCrashedMustReturnFalseIfNoRacersCrashed() {
        Race race = new Race();

        Racer racer = new Racer();
        racer.setDamage(NO_DAMAGE);
        race.addRacer(racer);

        Racer racer2 = new Racer();
        racer2.setDamage(NO_DAMAGE);
        race.addRacer(racer2);

        Racer racer3 = new Racer();
        racer3.setDamage(NO_DAMAGE);
        race.addRacer(racer3);

        assertThat(race.isAllCrashed(), equalTo(false));
    }

    @Test
    void getMessage() {
        Race race = new Race();

        assertThat(race.getMessage(), nullValue());
    }
}
