package com.escanan.ealden.race.controller.api;

import com.escanan.ealden.race.Configurations;
import com.escanan.ealden.race.controller.api.model.Roll;
import com.escanan.ealden.race.model.Race;
import com.escanan.ealden.race.service.RaceService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.escanan.ealden.race.controller.api.model.RequestParameters.fromRequest;

@WebServlet(name = "Roll", urlPatterns = "/api/races/roll")
public class RollController extends ApiController {
    private boolean testMode = Configurations.isTestMode();

    private RaceService raceService = Configurations.raceService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Roll roll = Roll.fromParameters(fromRequest(request));

        Race race = raceService.getCurrentRace();

        if (testMode) {
            race.roll(roll.getNumber(), roll.getSpeedType());
        } else {
            race.roll(roll.getSpeedType());
        }

        renderJson(race.asJson(), response);
    }

    void setRaceService(RaceService raceService) {
        this.raceService = raceService;
    }

    void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }
}
