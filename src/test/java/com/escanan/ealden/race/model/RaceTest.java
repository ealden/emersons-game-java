package com.escanan.ealden.race.model;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.escanan.ealden.race.model.Race.*;
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
    public void asJSON() {
        Race race = new Race();
        race.addRacer(new Racer());

        Map<String, Object> json = race.asJSON();

        assertThat(json, hasEntry(ID, 1L));
        assertThat(json, hasKey(RACERS));
        assertThat(json, hasKey(CURRENT_RACER));
        assertThat(json, hasEntry(FINISH_LINE, 10));
        assertThat(json, hasEntry(OVER, false));
        assertThat(json, hasEntry(ALL_CRASHED, false));
        assertThat(json, hasEntry(MESSAGE, "Time to RACE!  Alice rolls first!"));
    }

    @Test
    public void asJSONMustReturnJSONWhenNoRacers() {
        Race race = new Race();

        Map<String, Object> json = race.asJSON();

        assertThat(json, hasEntry(ID, 1L));
        assertThat(json, hasKey(RACERS));
        assertThat(json, hasEntry(CURRENT_RACER, null));
        assertThat(json, hasEntry(FINISH_LINE, 10));
        assertThat(json, hasEntry(OVER, false));
        assertThat(json, hasEntry(ALL_CRASHED, false));
        assertThat(json, hasEntry(MESSAGE, "Time to RACE!  Alice rolls first!"));
    }

    @Test
    public void isOverMustReturnFalseIfNoRacersHaveWon() {
        Racer racer = new Racer();
        Racer racer2 = new Racer();
        Racer racer3 = new Racer();

        Race race = new Race();
        race.addRacer(racer);
        race.addRacer(racer2);
        race.addRacer(racer3);

        assertThat(race.isOver(), is(false));
    }

    @Test
    public void isOverMustReturnTrueIfARacerHasWon() {
        Racer racer = new Racer();
        Racer racer2 = new Racer();
        Racer racer3 = new Racer();

        Race race = new Race();
        race.addRacer(racer);
        race.addRacer(racer2);
        race.addRacer(racer3);

        racer3.setPosition(Racer.FINISH_LINE);

        assertThat(race.isOver(), is(true));
    }
}
