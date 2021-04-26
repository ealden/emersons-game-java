package com.escanan.ealden.race.controller.api;

import com.escanan.ealden.race.Configurations;
import com.escanan.ealden.race.service.RaceService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Races", urlPatterns = "/api/races")
public class RacesController extends ApiController {
    private RaceService raceService = Configurations.raceService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        jsonResponse(raceService.getCurrentRace().asJson(), response);
    }

    public void setRaceService(RaceService raceService) {
        this.raceService = raceService;
    }
}
