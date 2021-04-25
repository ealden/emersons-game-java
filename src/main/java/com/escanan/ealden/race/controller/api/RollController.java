package com.escanan.ealden.race.controller.api;

import com.escanan.ealden.race.model.Race;
import com.escanan.ealden.race.model.SpeedType;
import com.escanan.ealden.race.service.RaceService;
import com.escanan.ealden.race.service.impl.RaceServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "Roll", urlPatterns = "/api/races/roll")
public class RollController extends ApiController {
    private RaceService raceService = RaceServiceImpl.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String> parameters = jsonRequest(request);

        int roll = Integer.parseInt(parameters.get("roll"));
        SpeedType speedType = SpeedType.valueOf(parameters.get("speedType"));

        Race race = raceService.getCurrentRace();
        race.roll(roll, speedType);

        jsonResponse(race, response);
    }

    public void setRaceService(RaceService raceService) {
        this.raceService = raceService;
    }
}
