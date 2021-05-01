package com.escanan.ealden.race.controller.api;

import com.escanan.ealden.race.Configurations;
import com.escanan.ealden.race.controller.api.model.Roll;
import com.escanan.ealden.race.model.Race;
import com.escanan.ealden.race.service.RaceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RollController extends ApiController {
    private boolean testMode = Configurations.isTestMode();

    private RollController shim = this;
    private RaceService raceService = Configurations.raceService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        Roll roll = shim.fromRequest(request);

        Race race = raceService.getCurrentRace();

        if (testMode) {
            race.roll(roll.getNumber(), roll.getSpeedType());
        } else {
            race.roll(roll.getSpeedType());
        }

        renderJson(race.asJson(), response);
    }

    Roll fromRequest(HttpServletRequest request) {
        try {
            return Roll.fromRequest(request);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    void setShim(RollController shim) {
        this.shim = shim;
    }

    void setRaceService(RaceService raceService) {
        this.raceService = raceService;
    }

    void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }
}
