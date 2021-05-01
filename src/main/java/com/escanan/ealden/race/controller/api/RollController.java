package com.escanan.ealden.race.controller.api;

import com.escanan.ealden.race.Configurations;
import com.escanan.ealden.race.controller.api.model.Roll;
import com.escanan.ealden.race.model.Race;
import com.escanan.ealden.race.service.RaceService;

public class RollController {
    private boolean testMode = Configurations.isTestMode();

    private RaceService raceService = Configurations.raceService();

    public Race roll(Roll roll) {
        Race race = raceService.getCurrentRace();

        if (testMode) {
            race.roll(roll.getNumber(), roll.getSpeedType());
        } else {
            race.roll(roll.getSpeedType());
        }

        return race;
    }

    void setRaceService(RaceService raceService) {
        this.raceService = raceService;
    }

    void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }
}
