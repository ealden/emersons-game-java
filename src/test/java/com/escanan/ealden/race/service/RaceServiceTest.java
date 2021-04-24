package com.escanan.ealden.race.service;

import com.escanan.ealden.race.model.Race;
import com.escanan.ealden.race.service.impl.RaceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.sameInstance;
import static org.hamcrest.Matchers.is;

public class RaceServiceTest {
    private RaceService service;

    @BeforeEach
    public void setUp() {
        service = new RaceServiceImpl();
    }

    @Test
    public void getCurrentRaceMustReturnTheSameRace() {
        Race race = service.getCurrentRace();
        Race otherRace = service.getCurrentRace();

        assertThat(race, is(sameInstance(otherRace)));
    }
}
