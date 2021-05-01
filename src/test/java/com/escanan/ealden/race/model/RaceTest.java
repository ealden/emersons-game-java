package com.escanan.ealden.race.model;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.escanan.ealden.race.model.Race.*;
import static com.escanan.ealden.race.model.Racer.MAX_DAMAGE;
import static com.escanan.ealden.race.model.Racer.NO_DAMAGE;
import static com.escanan.ealden.race.model.SpeedType.NORMAL;
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
        Race race = new Race();
        race.setId(1L);
        race.addRacer(new Racer("Alice"));

        Map<String, Object> json = race.asJson();

        assertThat(json, hasEntry(ID_PARAM, 1L));
        assertThat(json, hasKey(RACERS_PARAM));
        assertThat(json, hasKey(CURRENT_RACER_PARAM));
        assertThat(json, hasEntry(FINISH_LINE_PARAM, 10));
        assertThat(json, hasEntry(OVER_PARAM, false));
        assertThat(json, hasEntry(ALL_CRASHED_PARAM, false));
        assertThat(json, hasEntry(MESSAGE_PARAM, "Time to RACE!  Alice rolls first!"));
    }

    @Test
    void asJsonWhenNoRacersJoined() {
        Race race = new Race();
        race.setId(1L);

        Map<String, Object> json = race.asJson();

        assertThat(json, hasEntry(ID_PARAM, 1L));
        assertThat(json, hasKey(RACERS_PARAM));
        assertThat(json, hasEntry(CURRENT_RACER_PARAM, null));
        assertThat(json, hasEntry(FINISH_LINE_PARAM, 10));
        assertThat(json, hasEntry(OVER_PARAM, false));
        assertThat(json, hasEntry(ALL_CRASHED_PARAM, false));
        assertThat(json, hasEntry(MESSAGE_PARAM, null));
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
