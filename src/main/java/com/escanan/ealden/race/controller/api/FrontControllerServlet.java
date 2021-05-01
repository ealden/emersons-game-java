package com.escanan.ealden.race.controller.api;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FrontController", urlPatterns = "/api/*")
public class FrontControllerServlet extends ApiController {
    private static final String RACES_PATH = "/races";
    private static final String ROLL_RACE_PATH = "/races/roll";
    private static final String NEW_RACE_PATH = "/races/new";
    private static final String SETTINGS_PATH = "/settings";

    private static final RacesController racesController = new RacesController();
    private static final SettingsController settingsController = new SettingsController();

    private static final RollController rollController = new RollController();
    private static final NewRaceController newRaceController = new NewRaceController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        switch (request.getPathInfo()) {
            case RACES_PATH:
                racesController.doGet(request, response);

                break;
            case SETTINGS_PATH:
                settingsController.doGet(request, response);

                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        switch (request.getPathInfo()) {
            case ROLL_RACE_PATH:
                rollController.doPost(request, response);

                break;
            case NEW_RACE_PATH:
                newRaceController.doPost(request, response);

                break;
        }
    }
}
