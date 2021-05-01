package com.escanan.ealden.race.controller.api;

import com.escanan.ealden.race.Configurations;
import com.escanan.ealden.race.model.Race;
import com.escanan.ealden.race.service.RaceService;

public class NewRaceController {
    private RaceService raceService = Configurations.raceService();

    public Race newRace() {
        return raceService.newRace();
    }

    void setRaceService(RaceService raceService) {
        this.raceService = raceService;
    }
}
