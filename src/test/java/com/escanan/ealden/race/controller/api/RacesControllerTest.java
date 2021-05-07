package com.escanan.ealden.race.controller.api;

import com.escanan.ealden.race.controller.api.model.Roll;
import com.escanan.ealden.race.model.Race;
import com.escanan.ealden.race.model.Racer;
import com.escanan.ealden.race.service.RaceService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.escanan.ealden.race.model.SpeedType.NORMAL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.sameInstance;
import static org.mockito.Mockito.*;

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

    @Test
    void newRaceMustReturnNewRaceInstance() {
        Race newRace = new Race();
        newRace.setId(1L);
        newRace.addRacer(new Racer("Alice"));

        when(raceService.newRace()).thenReturn(newRace);

        Race race = controller.newRace();

        assertThat(race, Matchers.sameInstance(newRace));
    }

    @Test
    void rollMustRandomizeRoll() {
        Race currentRace = mock(Race.class);

        when(raceService.getCurrentRace()).thenReturn(currentRace);

        Roll roll = new Roll();
        roll.setNumber(1);
        roll.setSpeedType(NORMAL);

        Race race = controller.roll(roll);

        verify(currentRace).roll(NORMAL);

        assertThat(race, sameInstance(currentRace));
    }

    @Test
    void rollMustRollBasedOnParameterIfTestMode() {
        Race currentRace = new Race();
        currentRace.setId(1L);
        currentRace.addRacer(new Racer("Alice"));
        currentRace.addRacer(new Racer("Bob"));

        when(raceService.getCurrentRace()).thenReturn(currentRace);

        controller.setTestMode(true);

        Roll roll = new Roll();
        roll.setNumber(1);
        roll.setSpeedType(NORMAL);

        Race race = controller.roll(roll);

        assertThat(race.getLastRoll().getNumber(), equalTo(1));
        assertThat(race.getLastRoll().getSpeedType(), equalTo(NORMAL));
    }
}
