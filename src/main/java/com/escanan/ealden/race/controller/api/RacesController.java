package com.escanan.ealden.race.controller.api;

import com.escanan.ealden.race.Configurations;
import com.escanan.ealden.race.model.Race;
import com.escanan.ealden.race.service.RaceService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Races", urlPatterns = "/api/races")
public class RacesController extends ApiController {
    private RaceService raceService = Configurations.raceService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        Race race = raceService.getCurrentRace();

        renderJson(race.asJson(), response);
    }

    void setRaceService(RaceService raceService) {
        this.raceService = raceService;
    }
}
