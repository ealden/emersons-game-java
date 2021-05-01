package com.escanan.ealden.race.controller.api;

import com.escanan.ealden.race.model.Race;
import com.escanan.ealden.race.model.Racer;
import com.escanan.ealden.race.service.RaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.sameInstance;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RacesControllerTest {
    private RacesController controller;

    @Mock
    private RaceService raceService;

    @BeforeEach
    void setUp() {
        controller = new RacesController();
        controller.setRaceService(raceService);
    }

    @Test
    void indexMustReturnCurrentRace() {
        Race currentRace = new Race();
        currentRace.setId(1L);
        currentRace.addRacer(new Racer("Alice"));

        when(raceService.getCurrentRace()).thenReturn(currentRace);

        Race race = controller.index();

        assertThat(race, sameInstance(currentRace));
    }
}
