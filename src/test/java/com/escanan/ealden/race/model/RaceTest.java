package com.escanan.ealden.race.model;

import org.junit.jupiter.api.Test;

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
}
