package com.escanan.ealden.race.controller.api;

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
    private static final String RACES_URL = "/races";
    private static final String ROLL_RACE_URL = "/races/roll";
    private static final String NEW_RACE_URL = "/races/new";
    private static final String SETTINGS_URL = "/settings";

    private static final RacesController racesController = new RacesController();
    private static final SettingsController settingsController = new SettingsController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        if (RACES_URL.equals(request.getPathInfo())) {
            Race race = racesController.index();

            renderJson(race.asJson(), response);
        } else if (SETTINGS_URL.equals(request.getPathInfo())) {
            Map<String, Boolean> settings = settingsController.index();

            renderJson(settings, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        if (ROLL_RACE_URL.equals(request.getPathInfo())) {
            Roll roll = fromRequest(request);

            Race race = racesController.roll(roll);

            renderJson(race.asJson(), response);
        } else if (NEW_RACE_URL.equals(request.getPathInfo())) {
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
