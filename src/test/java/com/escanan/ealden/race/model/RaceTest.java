package com.escanan.ealden.race.model;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.escanan.ealden.race.model.Race.*;
import static com.escanan.ealden.race.model.Racer.MAX_DAMAGE;
import static com.escanan.ealden.race.model.Racer.NO_DAMAGE;
import static com.escanan.ealden.race.model.SpeedType.NORMAL;
import static com.escanan.ealden.race.model.SpeedType.SUPER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RaceTest {
    @Test
    public void currentRacerMustBeNullIfNoRaceHasNoRacers() {
        Race race = new Race();

        assertThat(race.getCurrentRacer(), is(nullValue()));
    }

    @Test
    public void addRacerMustSetCurrentRacerToRacerAdded() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        assertThat(race.getCurrentRacer(), is(sameInstance(racer)));
    }

    @Test
    public void addRacerMustSetRaceToSameRace() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        assertThat(racer.getRace(), is(sameInstance(race)));
    }

    @Test
    public void addRacerMustSetRankToRacersCount() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        assertThat(racer.getRank(), is(equalTo(1)));
    }

    @Test
    public void addRacerMustOnlySetFirstRacerAsCurrentRacer() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        race.addRacer(new Racer());

        assertThat(race.getCurrentRacer(), is(sameInstance(racer)));
    }

    @Test
    public void asJson() {
        Race race = new Race();
        race.setId(1L);
        race.addRacer(new Racer("Alice"));

        Map<String, Object> json = race.asJson();

        assertThat(json, hasEntry(ID, 1L));
        assertThat(json, hasKey(RACERS));
        assertThat(json, hasKey(CURRENT_RACER));
        assertThat(json, hasEntry(FINISH_LINE, 10));
        assertThat(json, hasEntry(OVER, false));
        assertThat(json, hasEntry(ALL_CRASHED, false));
        assertThat(json, hasEntry(MESSAGE, "Time to RACE!  Alice rolls first!"));
    }

    @Test
    public void asJsonMustReturnJSONWhenNoRacers() {
        Race race = new Race();
        race.setId(1L);

        Map<String, Object> json = race.asJson();

        assertThat(json, hasEntry(ID, 1L));
        assertThat(json, hasKey(RACERS));
        assertThat(json, hasEntry(CURRENT_RACER, null));
        assertThat(json, hasEntry(FINISH_LINE, 10));
        assertThat(json, hasEntry(OVER, false));
        assertThat(json, hasEntry(ALL_CRASHED, false));
        assertThat(json, hasEntry(MESSAGE, null));
    }

    @Test
    public void isOverMustReturnFalseIfNoRacersHaveWon() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        Racer racer2 = new Racer();
        race.addRacer(racer2);

        Racer racer3 = new Racer();
        race.addRacer(racer3);

        assertThat(race.isOver(), is(false));
    }

    @Test
    public void isOverMustReturnTrueIfARacerHasWon() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        Racer racer2 = new Racer();
        race.addRacer(racer2);

        Racer racer3 = new Racer();
        racer3.setPosition(race.getFinishLine());
        race.addRacer(racer3);

        assertThat(race.isOver(), is(true));
    }

    @Test
    public void rollMustCreateNewRollEntry() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        race.roll(1, NORMAL);

        assertThat(race.getLastRoll(), is(notNullValue()));
        assertThat(race.getLastRoll().getRacer(), is(sameInstance(racer)));
    }

    @Test
    public void rollMustSetCurrentRacerToNextRacer() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        Racer racer2 = new Racer();
        race.addRacer(racer2);

        race.roll(1, NORMAL);

        assertThat(race.getCurrentRacer(), is(sameInstance(racer2)));
    }

    @Test
    public void rollWithNoNumberParameterMustRandomlyRollCurrentRacer() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        race.roll(NORMAL);

        assertThat(race.getLastRoll().getRacer().getPosition(), is(greaterThan(0)));
    }

    @Test
    public void isAllCrashedMustReturnTrueIfAllRacersCrashed() {
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

        assertThat(race.isAllCrashed(), is(true));
    }

    @Test
    public void isAllCrashedMustReturnFalseIfNotAllRacersCrashed() {
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

        assertThat(race.isAllCrashed(), is(false));
    }

    @Test
    public void isAllCrashedMustReturnFalseIfNoRacersCrashed() {
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

        assertThat(race.isAllCrashed(), is(false));
    }

    @Test
    public void getMessage() {
        Race race = new Race();

        assertThat(race.getMessage(), is(nullValue()));
    }
}
