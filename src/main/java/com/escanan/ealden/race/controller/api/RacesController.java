package com.escanan.ealden.race.controller.api;

import com.escanan.ealden.race.controller.api.model.Roll;
import com.escanan.ealden.race.model.Race;
import com.escanan.ealden.race.service.RaceService;

public class RacesController {
    private RaceService raceService;

    private boolean testMode;

    public Race index() {
        return raceService.getCurrentRace();
    }

    public Race newRace() {
        return raceService.newRace();
    }

    public Race roll(Roll roll) {
        Race race = raceService.getCurrentRace();

        if (testMode) {
            race.roll(roll.getNumber(), roll.getSpeedType());
        } else {
            race.roll(roll.getSpeedType());
        }

        return race;
    }

    public RaceService getRaceService() {
        return raceService;
    }

    public boolean isTestMode() {
        return testMode;
    }

    public void setRaceService(RaceService raceService) {
        this.raceService = raceService;
    }

    public void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }
}
