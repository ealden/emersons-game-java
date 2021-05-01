package com.escanan.ealden.race.controller;

import com.escanan.ealden.race.Configurations;
import com.escanan.ealden.race.controller.api.RacesController;
import com.escanan.ealden.race.controller.api.SettingsController;
import com.escanan.ealden.race.controller.api.model.Roll;
import com.escanan.ealden.race.model.Race;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

            renderJson(race.asJson(), response);
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
            Roll roll = fromRequest(request);

            Race race = racesController.roll(roll);

            renderJson(race.asJson(), response);
        }

        if (NEW_RACE_URL.equals(request.getPathInfo())) {
            Race race = racesController.newRace();

            renderJson(race.asJson(), response);
        }
    }

    private Roll fromRequest(HttpServletRequest request) {
        try {
            return Roll.fromRequest(request);
        } catch (IOException e) {
            throw new JsonException(e);
        }
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

    static class JsonException extends RuntimeException {
        public JsonException(Throwable cause) {
            super(cause);
        }
    }
}
