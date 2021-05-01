package com.escanan.ealden.race.controller;

import com.escanan.ealden.race.controller.api.RacesController;
import com.escanan.ealden.race.controller.api.SettingsController;
import com.escanan.ealden.race.model.Race;
import com.escanan.ealden.race.service.RaceService;
import org.junit.jupiter.api.Test;

import static com.escanan.ealden.race.Configurations.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.sameInstance;

class ConfigurationsTest {
    @Test
    void defaultRaceService() {
        setRaceService(null);

        assertThat(raceService(), notNullValue());
    }

    @Test
    void defaultRaceController() {
        setRacesController(null);

        assertThat(racesController(), notNullValue());
    }

    @Test
    void defaultSettingsController() {
        setSettingsController(null);

        assertThat(settingsController(), notNullValue());
    }

    @Test
    void providedRaceService() {
        RaceService raceService = new RaceService() {
            @Override
            public Race getCurrentRace() {
                return null;
            }

            @Override
            public Race newRace() {
                return null;
            }

            @Override
            public Race save(Race race) {
                return null;
            }
        };

        setRaceService(raceService);

        assertThat(raceService(), sameInstance(raceService));
    }

    @Test
    void providedRaceController() {
        RacesController racesController = new RacesController();

        setRacesController(racesController);

        assertThat(racesController(), sameInstance(racesController));
    }

    @Test
    void providedSettingsController() {
        SettingsController settingsController = new SettingsController();

        setSettingsController(settingsController);

        assertThat(settingsController(), sameInstance(settingsController));
    }
}
