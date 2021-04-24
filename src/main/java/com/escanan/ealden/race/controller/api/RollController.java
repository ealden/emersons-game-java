package com.escanan.ealden.race.controller.api;

import com.escanan.ealden.race.model.Race;
import com.escanan.ealden.race.model.SpeedType;
import com.escanan.ealden.race.service.RaceService;
import com.escanan.ealden.race.service.impl.RaceServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Roll", urlPatterns = "/api/races/roll")
public class RollController extends ApiController {
    private final RaceService raceService = RaceServiceImpl.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Race race = raceService.getCurrentRace();
        race.roll(1, SpeedType.NORMAL);

        jsonResponse(race, response);
    }
}
