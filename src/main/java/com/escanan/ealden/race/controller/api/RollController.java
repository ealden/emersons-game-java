package com.escanan.ealden.race.controller.api;

import com.escanan.ealden.race.model.Race;
import com.escanan.ealden.race.model.SpeedType;
import com.escanan.ealden.race.service.RaceService;
import com.escanan.ealden.race.service.impl.RaceServiceImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
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

    private Map<String, String> jsonRequest(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();

        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        Type collectionType = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> parameters = new Gson().fromJson(sb.toString(), collectionType);

        return parameters;
    }

    public void setRaceService(RaceService raceService) {
        this.raceService = raceService;
    }
}
