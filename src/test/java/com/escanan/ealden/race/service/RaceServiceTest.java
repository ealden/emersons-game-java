package com.escanan.ealden.race.service;

import com.escanan.ealden.race.model.Race;
import com.escanan.ealden.race.service.fake.FakeRaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;

public class RaceServiceTest {
    private RaceService service;

    @BeforeEach
    public void setUp() {
        service = new FakeRaceService();
    }

    @Test
    public void getCurrentRaceMustReturnTheSameRace() {
        Race race = service.getCurrentRace();
        Race otherRace = service.getCurrentRace();

        assertThat(race, sameInstance(otherRace));
    }

    @Test
    public void newRaceMustReplaceCurrentRace() {
        Race oldRace = service.getCurrentRace();
        Race newRace = service.newRace();
        Race race = service.getCurrentRace();

        assertThat(oldRace, not(sameInstance(newRace)));
        assertThat(newRace, sameInstance(race));
    }
}
