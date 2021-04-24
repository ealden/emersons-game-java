package com.escanan.ealden.race.controller.api;

import com.escanan.ealden.race.service.RaceService;
import com.escanan.ealden.race.service.impl.RaceServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Races", urlPatterns = "/api/races")
public class RacesController extends ApiController {
    private RaceService raceService = RaceServiceImpl.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        jsonResponse(raceService.getCurrentRace(), response);
    }

    public void setRaceService(RaceService raceService) {
        this.raceService = raceService;
    }
}
