package com.escanan.ealden.race;

import com.escanan.ealden.race.controller.api.RacesController;
import com.escanan.ealden.race.controller.api.SettingsController;
import com.escanan.ealden.race.model.Race;
import com.escanan.ealden.race.service.RaceService;
import org.junit.jupiter.api.Test;

import static com.escanan.ealden.race.Configurations.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ConfigurationsTest {
    @Test
    void defaultRaceService() {
        setRaceService(null);

        assertThat(raceService(), notNullValue());
    }

    @Test
    void defaultRaceController() {
        setRacesController(null);

        RacesController controller = racesController();

        assertThat(controller, notNullValue());
        assertThat(controller.getRaceService(), notNullValue());
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

    @Test
    void setTestModeMustOverrideExistingControllerInstances() {
        assertThat(Configurations.racesController().isTestMode(), equalTo(false));
        assertThat(Configurations.settingsController().isTestMode(), equalTo(false));

        Configurations.setTestMode(true);

        assertThat(Configurations.racesController().isTestMode(), equalTo(true));
        assertThat(Configurations.settingsController().isTestMode(), equalTo(true));
    }
}
