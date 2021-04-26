package com.escanan.ealden.race.controller.api;

import com.escanan.ealden.race.Configurations;
import com.escanan.ealden.race.model.Race;
import com.escanan.ealden.race.service.RaceService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "NewRace", urlPatterns = "/api/races/new")
public class NewRaceController extends ApiController {
    private RaceService raceService = Configurations.raceService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Race race = raceService.newRace();

        jsonResponse(race.asJson(), response);
    }

    void setRaceService(RaceService raceService) {
        this.raceService = raceService;
    }
}
