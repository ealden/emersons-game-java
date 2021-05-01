package com.escanan.ealden.race.controller.api;

import com.escanan.ealden.race.model.Race;
import com.escanan.ealden.race.model.Racer;
import com.escanan.ealden.race.service.RaceService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NewRaceControllerTest {
    private NewRaceController controller;

    @Mock
    private RaceService raceService;

    @BeforeEach
    void setUp() {
        controller = new NewRaceController();
        controller.setRaceService(raceService);
    }

    @Test
    void newRaceMustReturnNewRaceInstance() {
        Race newRace = new Race();
        newRace.setId(1L);
        newRace.addRacer(new Racer("Alice"));

        when(raceService.newRace()).thenReturn(newRace);

        Race race = controller.newRace();

        assertThat(race, Matchers.sameInstance(newRace));
    }
}
