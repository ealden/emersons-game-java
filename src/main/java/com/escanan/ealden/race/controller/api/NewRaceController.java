package com.escanan.ealden.race.controller.api;

import com.escanan.ealden.race.model.Race;
import com.escanan.ealden.race.service.RaceService;
import com.escanan.ealden.race.service.impl.RaceServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "NewRace", urlPatterns = "/api/races/new")
public class NewRaceController extends ApiController {
    private RaceService raceService = RaceServiceImpl.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Race race = raceService.newRace();

        jsonResponse(race.asJSON(), response);
    }

    public void setRaceService(RaceService raceService) {
        this.raceService = raceService;
    }
}
