package com.escanan.ealden.race.controller;

import com.escanan.ealden.race.Configurations;
import com.escanan.ealden.race.controller.api.JsonException;
import com.escanan.ealden.race.controller.api.RacesController;
import com.escanan.ealden.race.controller.api.SettingsController;
import com.escanan.ealden.race.controller.api.model.Roll;
import com.escanan.ealden.race.model.JsonAware;
import com.escanan.ealden.race.model.Race;
import com.google.gson.Gson;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "FrontController", urlPatterns = "/api/*")
public class FrontControllerServlet extends HttpServlet {
    static final String RACES_URL = "/races";
    static final String ROLL_RACE_URL = "/races/roll";
    static final String NEW_RACE_URL = "/races/new";
    static final String SETTINGS_URL = "/settings";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        RacesController racesController = Configurations.racesController();
        SettingsController settingsController = Configurations.settingsController();

        if (RACES_URL.equals(request.getPathInfo())) {
            Race race = racesController.index();

            renderJson(race, response);
        }

        if (SETTINGS_URL.equals(request.getPathInfo())) {
            Map<String, Boolean> settings = settingsController.index();

            renderJson(settings, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        RacesController racesController = Configurations.racesController();

        if (ROLL_RACE_URL.equals(request.getPathInfo())) {
            Roll roll = Roll.fromRequest(request);

            Race race = racesController.roll(roll);

            renderJson(race, response);
        }

        if (NEW_RACE_URL.equals(request.getPathInfo())) {
            Race race = racesController.newRace();

            renderJson(race, response);
        }
    }

    private void renderJson(JsonAware object, HttpServletResponse response) {
        renderJson(object.asJson(), response);
    }

    private void renderJson(Object responseObject, HttpServletResponse response) {
        String responseBody = new Gson().toJson(responseObject);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            PrintWriter out = response.getWriter();
            out.print(responseBody);
            out.flush();
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

}
